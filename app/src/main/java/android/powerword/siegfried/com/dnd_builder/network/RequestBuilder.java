package android.powerword.siegfried.com.dnd_builder.network;



import android.powerword.siegfried.com.dnd_builder.model.Status;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryName;
import rx.Observable;

/**
 * Created by 欧阳夏昱 on 2016/4/26.
 */

public interface RequestBuilder {
    @GET("mock")
    Observable<Status> getAppUpdate(@Query("userName") String userName, @Query("password") String password);
}
