package android.powerword.siegfried.com.dnd_builder.network;

/**
 * Created by 欧阳夏昱 on 2016/4/27.
 */

public class ResultData<T> {
    private T object;

    public T get() {
        return object;
    }

    public void set(T object) {
        this.object = object;
    }
}
