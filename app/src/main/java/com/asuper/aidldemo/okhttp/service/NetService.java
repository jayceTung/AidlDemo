package com.asuper.aidldemo.okhttp.service;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Super on 2017/9/14.
 */

public interface NetService {

    @GET("/query?")
    Observable<KDBean> getCountt(@Query("type") String type, @Query("postid") String postid);

}
