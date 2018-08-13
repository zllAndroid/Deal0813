package com.xm6leefun.zlldeal.about_retrofit;


import com.xm6leefun.zlldeal.about_retrofit_model.DataLogins;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/2/24.
 */

public interface ApiService {

//    @FormUrlEncoded
//    @FormUrlEncoded
//    @Headers({"Content-Type: application/json","Accept: application/json"})
//    @Headers({"Content-Type: application/x-www-form-urlencoded","Accept: application/json"})
    @POST("/api/user/login")
//    Observable<ResponseParent<DataLogins.UserBean>> post_login(@Query("mobile") String username, @Query("pwd") String password);
//    Observable<ResponseParent<String>> post_login(@FieldMap Map<String,String> queryMap);
//    Observable<ResponseParent<String>> post_login(@Body RequestBody httpClient);
    Observable<ResponseParent<Object>> post_login(@Body ResquestParent httpClient);
    @POST("/api/user/register")
//    Observable<ResponseParent<DataLogins.UserBean>> post_login(@Query("mobile") String username, @Query("pwd") String password);
//    Observable<ResponseParent<String>> post_login(@FieldMap Map<String,String> queryMap);
//    Observable<ResponseParent<String>> post_login(@Body RequestBody httpClient);
    Observable<ResponseParent<Object>> test(@Body ResquestParent httpClient);
//    Observable<ResponseParent<String>> post_login(@Body ResquestParent httpClient);


    @GET("/api/user/login")
    Observable<ResponseParent<DataLogins.UserBean>> get_Login(@Query("mobile") String username,
                                                              @Query("pwd") String password);
//    @POST("userLogin")
//    Observable<ResponseParent<DataLogin>> getLogin(@Query("username") String username,
//                                                   @Query("password") String password);
}
