package com.androidweblottery.httputil.http;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lifan on 2017/9/5.
 */

public interface ServiceAPI {

    @GET("/api/columns/{user} ")
    Observable<Object> getAuthor(@Path("user") String user);
}
