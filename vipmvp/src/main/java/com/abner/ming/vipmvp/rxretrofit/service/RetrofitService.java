package com.abner.ming.vipmvp.rxretrofit.service;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by AbnerMing on 2018/1/2.
 */

public interface RetrofitService {
    @GET
    Observable<ResponseBody> getRxRequest(@Url String url, @QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postRxRequest(@Url String url, @FieldMap Map<String, String> postMap);
}
