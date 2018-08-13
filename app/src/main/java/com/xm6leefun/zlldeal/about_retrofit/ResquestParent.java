package com.xm6leefun.zlldeal.about_retrofit;

/**
 * 提交接口统一参数设置
 * 创建作者： tushicong
 * 创建时间： 2017/4/10 13:37
 * 这个类主要用于封装服务端接口验证参数，例如时间戳什么的
 **/
public class ResquestParent<T> {

    public  ResquestParent(T reqData) {
        getResquestParent(reqData);
    }

    public T getResquestParent( T reqData){
        return reqData;
    }

//    private T reqData;
//    public ResquestParent(T reqData) {
////                    Request.Builder builder = chain.request().newBuilder();
////            Request requst = builder.addHeader("Content-type", "application/x-www-from-urlencoded").build();
//////            Request requst = builder.addHeader("Content-type", "application/json").build();
////            return chain.proceed(requst);
////        this.reqData = reqData;
////        return "application/x-www-from-urlencoded";
//    }
//    @Override
//    public String toString() {
//        return "ResquestParent{" +
//                "reqData=" + reqData +
//                '}';
//    }
}
