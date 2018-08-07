package android.powerword.siegfried.com.dnd_builder.network;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.powerword.siegfried.com.dnd_builder.MyApplication;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.WebView;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 欧阳夏昱 on 2016/4/26.
 * <p>
 * Restrofit 下载逻辑基础
 * <p>
 * 通过 init() 获得一个 retrofit 对象
 */

public class RetrofitHelper {

    private Retrofit retrofit;
    private final String api = "";


    public static String motherUrl = "http://10.222.56.111:3000/";
    //没有网络连接
    public static final String NETWORN_NONE = null;
    //wifi连接
    public static final String NETWORN_WIFI = "WiFi";
    //手机网络数据连接类型
    public static final String NETWORN_2G = "2G";
    public static final String NETWORN_3G = "3G";
    public static final String NETWORN_4G = "4G";

    private RetrofitHelper() {
        init();
    }

    public static RetrofitHelper getInstance() {
        return HelperInstance.INSTANCE;
    }

    public Retrofit getRetrofit() {
        return this.retrofit;
    }



    private static class HelperInstance {
        private static RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    private void init() {


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Interceptor customerIdInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl originalHttpUrl = originalRequest.url();

//                HttpUrl url = originalHttpUrl.newBuilder()
//                        .addQueryParameter("customer_id", ConfigUtils.getUid())
//                        .build();
                Request request = originalRequest.newBuilder()
//                        .url(url)
                        .method(originalRequest.method(), originalRequest.body())
                        .build();

                return chain.proceed(request);
            }
        };


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // header
//        httpClient
//                .addInterceptor(customerIdInterceptor)
//                .addInterceptor(loggingInterceptor)
//                .addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//
//                Request original = chain.request();
//
//                String appVersion = "";
//                try {
//                    appVersion = AdsApplication.getContext().getPackageManager()
//                            .getPackageInfo(AdsApplication.getContext().getPackageName(), 0).versionCode + "";
//                } catch (PackageManager.NameNotFoundException e) {
//                    e.printStackTrace();
//                }
//                OAuth2 oAuth2 = OAuth2Utils.get();
//
//                Request request = original.newBuilder()
//                        .header("deviceId", AdsApplication.getDeviceId())
//                        .header("Authorization", "OAuth2 " + oAuth2.accessToken)
//                        .header("uid", oAuth2.uid)
//                        .header("appName", AdsApplication.getContext().getPackageName())
//                        .header("appVersion", appVersion)
//                        .header("os", "Android")
//                        .header("networkType", getNetworkState())
//                        .header("platform", android.os.Build.MODEL)
//                        .header("Host", "mapi.biz.weibo.com")
//                        .method(original.method(), original.body())
//                        .build();
//
//                return chain.proceed(request);
//            }
//        });
        OkHttpClient client = httpClient.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(motherUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

    }



//    public void getHeader(String message, String callBackName, WebView webView){
//        AdvHeader advHeader = new AdvHeader();
//        advHeader.deviceId = AdsApplication.getDeviceId();
//        advHeader.Authorization = "OAuth2 " + OAuth2Utils.get().accessToken;
//        advHeader.uid = OAuth2Utils.get().uid;
//        advHeader.appName = AdsApplication.getContext().getPackageName();
//        advHeader.appVersion = "";
//        advHeader.os = "Android";
//        advHeader.networkType = getNetworkState();
//        advHeader.platform = android.os.Build.MODEL;
//        advHeader.Host = "mapi.biz.weibo.com";
//        Logger.getLogger(this.getClass().getSimpleName()).info(ParseManager.getManager().bean2Json(advHeader));
//        finalCall(webView, ParseManager.getManager().bean2Json(advHeader), callBackName);
////        finalCall();
////        return advHeader;
////                .header("deviceId", )
////                .header("Authorization", )
////                .header("uid", oAuth2.uid)
////                .header("appName", )
////                .header("appVersion", appVersion)
////                .header("os", "Android")
////                .header("networkType", getNetworkState())
////                .header("platform", android.os.Build.MODEL)
////                .header("Host", "mapi.biz.weibo.com")
//    }



    /**
     * 获取当前网络连接类型
     *
     * @param
     * @return
     */
    public String getNetworkState() {
        //获取系统的网络服务
        ConnectivityManager connManager = (ConnectivityManager) MyApplication.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        //如果当前没有网络
        if (null == connManager)
            return NETWORN_NONE;

        //获取当前网络类型，如果为空，返回无网络
        NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
            return NETWORN_NONE;
        }

        // 判断是不是连接的是不是wifi
        NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (null != wifiInfo) {
            NetworkInfo.State state = wifiInfo.getState();
            if (null != state)
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    return NETWORN_WIFI;
                }
        }

        // 如果不是wifi，则判断当前连接的是运营商的哪种网络2g、3g、4g等
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (null != networkInfo) {
            NetworkInfo.State state = networkInfo.getState();
            String strSubTypeName = networkInfo.getSubtypeName();
            if (null != state)
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    switch (activeNetInfo.getSubtype()) {
                        //如果是2g类型
                        case TelephonyManager.NETWORK_TYPE_GPRS: // 联通2g
                        case TelephonyManager.NETWORK_TYPE_CDMA: // 电信2g
                        case TelephonyManager.NETWORK_TYPE_EDGE: // 移动2g
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager.NETWORK_TYPE_IDEN:
                            return NETWORN_2G;
                        //如果是3g类型
                        case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        case TelephonyManager.NETWORK_TYPE_EHRPD:
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
                            return NETWORN_3G;
                        //如果是4g类型
                        case TelephonyManager.NETWORK_TYPE_LTE:
                            return NETWORN_4G;
                        default:
                            //中国移动 联通 电信 三种3G制式
                            if (strSubTypeName.equalsIgnoreCase("TD-SCDMA") || strSubTypeName.equalsIgnoreCase("WCDMA") || strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                                return NETWORN_3G;
                            } else {
                                return null;
                            }
                    }
                }
        }
        return NETWORN_NONE;
    }
}
