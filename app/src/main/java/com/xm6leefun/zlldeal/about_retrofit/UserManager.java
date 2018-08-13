package com.xm6leefun.zlldeal.about_retrofit;


import com.xm6leefun.zlldeal.about_retrofit.model_request.RequesTest;
import com.xm6leefun.zlldeal.about_retrofit.model_request.RequestData;
import com.xm6leefun.zlldeal.about_retrofit_model.DataLogins;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2018/2/24.
 */

public class UserManager {
    /**
     * 登录接口
     */
    public static void postLogin(String mobile, String pwd, ResponseResultExtendListener<Object> callback){

//        RequestData requestData = new RequestData(mobile,pwd);
        RequestData requestData = new RequestData();
        requestData.setMobile(mobile);
        requestData.setPwd(pwd);
        ResquestParent httpClient = new ResquestParent(requestData);
//        Gson gson = new Gson();
//        String postInfoStr = gson.toJson(requestData);
//        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-from-urlencoded; charset=utf-8"),postInfoStr);
//        RequestBody body = RequestBody.create(MediaType.parse"application/json; charset=utf-8"),postInfoStr);
        Subscriber<ResponseParent<Object>> subscriber =
                new PostSubscriber<Object>().getSubscriber(callback);
        ApiClient.getApiService()
//                .post_login(mobile,pwd)
                .post_login(httpClient)
//                .post_login(queryBody)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public static void postTest(ResponseResultExtendListener<Object> callback){

//        RequestData requestData = new RequestData(mobile,pwd);
        RequesTest requestData = new RequesTest();
        requestData.setUuid("123");
        ResquestParent httpClient = new ResquestParent(requestData);
//        Gson gson = new Gson();
//        String postInfoStr = gson.toJson(requestData);
//        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-from-urlencoded; charset=utf-8"),postInfoStr);
//        RequestBody body = RequestBody.create(MediaType.parse"application/json; charset=utf-8"),postInfoStr);
        Subscriber<ResponseParent<Object>> subscriber =
                new PostSubscriber<Object>().getSubscriber(callback);
        ApiClient.getApiService()
//                .post_login(mobile,pwd)
                .test(httpClient)
//                .post_login(queryBody)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
//    public static void normal(String mobile, String pwd, Subscriber<ResponseParent<String>> subscriber){
//
//        RequestData requestData = new RequestData(mobile,pwd);
////        RequestData requestData = new RequestData();
////        requestData.setMobile(mobile);
////        requestData.setPwd(pwd);
//        ResquestParent httpClient = new ResquestParent(requestData);
//
////        Subscriber<ResponseParent<String>> subscriber =
////                new PostSubscriber<String>().getSubscriber(callback);
//
//        Subscription subscription = ApiClient.getApiService()
////                .post_login(mobile,pwd)
//                .post_login(httpClient)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//        Log.e("httpClient",requestData.toString()+"\n------httpClient---"+httpClient.toString());
//    }
    public static void getLoginGet(String mobile, String pwd, ResponseResultExtendListener<DataLogins.UserBean> callback){
        Subscriber<ResponseParent<DataLogins.UserBean>> subscriber =
                new PostSubscriber<DataLogins.UserBean>().getSubscriber(callback);
        ApiClient.getApiService()
                .get_Login(mobile,pwd)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
//        getLogin
//        RequestData requestData = new RequestData();
//        requestData.setMobile(mobile);
//        requestData.setPwd(pwd);
//        ResquestParent httpClient = new ResquestParent(requestData);
//
//        Subscriber<ResponseParent<DataLogins>> subscriber =
//                new PostSubscriber<DataLogins>().getSubscriber(callback);
//
//        ApiClient.getApiService()
//                .api_login(httpClient)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//        Log.e("httpClient",requestData.toString()+"");
    }
}
