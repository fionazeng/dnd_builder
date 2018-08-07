package android.powerword.siegfried.com.dnd_builder.network;

/**
 * Created by 欧阳夏昱 on 2016/4/27.
 */

public interface OnHttp<T> {
    /**
     *
     * @param requestCode 请求code
     * @param type 返回成功或者失败
     * @param resultData 返回值
     */
    void onHttpResult(int requestCode, ReturnType type, ResultData<T> resultData);
}
