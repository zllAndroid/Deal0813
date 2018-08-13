package com.xm6leefun.zlldeal.about_retrofit;

import android.content.Context;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zll .
 * 创建时间：2018/3/29 0029.
 * 作用：主要是将RXJava和Retrofit和okhttp结合
 */

public abstract class RetrofitClient {
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    //Retrofit
    protected Retrofit mRetrofit;
    //okhttp  CookieJar
    //okhttp
    protected Request mLoginRequest;

    protected String authToken;
    //RXjava
    protected Map<String, CompositeSubscription> mSubscriptionsMap = new HashMap<>();
    public RetrofitClient(Context context) {
        //初始化retrofit  添加拦截器 进行配置 缓存
        //该拦截器用于记录应用中的网络请求的信息。
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);


//        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
//                .addHeaderParams("paltform", "android")
//                .addHeaderParams("userToken", "1234343434dfdfd3434")
//                .addHeaderParams("userId", "123445")
//                .build();
//        builder.addInterceptor(commonInterceptor);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                //.addNetworkInterceptor(new StethoInterceptor())
//                //.addInterceptor(new ReloginInterceptor())
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        if (TextUtils.isEmpty(authToken)) {
//                            return chain.proceed(chain.request());
//                        }
//                        //判断是否登录  如果登陆了就添加logintoken的头参数
//                        Request request = chain.request();
//                        return chain.proceed(
//                                request.newBuilder().url(request.url().newBuilder().addQueryParameter("logintoken", authToken).build()).build());
//                    }
//                })
//                .retryOnConnectionFailure(true)
//                .connectTimeout(30, TimeUnit.SECONDS)
//                .build();

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                //添加返回值为observable<T>的支持 添加之后ArtistService中返回值就变成了Observable
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //增加返回值为Gson的支持
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(getBaseUrl())
                .build();
    }


    public void unsubscribe(String tag) {
        if (mSubscriptionsMap.containsKey(tag)) {
            CompositeSubscription subscriptions = mSubscriptionsMap.get(tag);
            subscriptions.unsubscribe();
            mSubscriptionsMap.remove(tag);
        }
    }

    protected void addSubscription(String tag, Subscription subscription) {
        if (TextUtils.isEmpty(tag)) {
            return;
        }
        CompositeSubscription subscriptions;
        if (mSubscriptionsMap.containsKey(tag)) {
            subscriptions = mSubscriptionsMap.get(tag);
        } else {
            subscriptions = new CompositeSubscription();
        }
        subscriptions.add(subscription);
        mSubscriptionsMap.put(tag, subscriptions);
    }

    private static class FakeResult {
        public int code;
        public String reason;
    }

    protected abstract String getBaseUrl();



}
