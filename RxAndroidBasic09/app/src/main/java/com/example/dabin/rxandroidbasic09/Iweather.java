package com.example.dabin.rxandroidbasic09;

import com.example.dabin.rxandroidbasic09.domain.Data;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Dabin on 2016-11-04.
 */

public interface Iweather {
    //http://api.openweathermap.org
    // /data/2.5/weather?q=Seoul&APPID=16abf893051abf87955994a46972d74d
    @GET("/data/2.5/weather")
    Observable<Data> getData(@Query("q") String citiName, @Query("APPID") String key);
}
