package android.powerword.siegfried.com.dnd_builder.network;


import android.powerword.siegfried.com.dnd_builder.model.Status;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 欧阳夏昱 on 2016/4/27.
 */

public class Request {

    public RequestBuilder service = null;
    public static final int UPDATE_PLAN_DETAIL = 15;

    private Request() {
        init();
    }

    private void init() {
        this.service = RetrofitHelper.getInstance().getRetrofit().create(RequestBuilder.class);
    }

    public static Request getApi() {
        return FansApiInstance.INSTANCE;
    }

    public static void request() {

    }


    private static class FansApiInstance {
        private static Request INSTANCE = new Request();
    }

    public void login(String userName, String password, OnHttp onHttp) {
        Observable<Status> statusObservable = service.getAppUpdate(userName, password);
        onExecuted(statusObservable,onHttp, 123 );
    }

    public void onExecuted(Observable observable, final OnHttp onHttp, final int requestCode) {
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {
            @Override
            public void onCompleted() {
                onHttp.onHttpResult(requestCode, ReturnType.COMPLETED, null);
            }

            @Override
            public void onError(Throwable e) {
                ResultData<Throwable> ex = new ResultData<>();
                ex.set(e);
                onHttp.onHttpResult(requestCode, ReturnType.Fail, ex);
            }

            @Override
            public void onNext(Object o) {
                ResultData result = new ResultData();
                result.set(o);
                onHttp.onHttpResult(requestCode, ReturnType.Success,result);
            }
        });
    }

}
