package com.xm6leefun.zlldeal.about_retrofit;


import android.util.Log;

import com.google.gson.JsonSyntaxException;
import com.xm6leefun.zlldeal.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/8.
 */

public class PostSubscriber<T> {


    public Subscriber getSubscriber(final ResponseResultExtendListener<T> listener){
        Subscriber subscriber = new Subscriber<ResponseParent<T>>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                try {
                    checkHttpException(e);
                    listener.fialed("",e.getMessage());
                }catch (Exception ex){
                    listener.fialed("", "");
                }
            }
            @Override
            public void onNext(ResponseParent<T> httpResult) {
//                if (httpResult.getReturnCode().equals("0000")){
//                    //成功
//                    listener.success(httpResult.getReturnData(), httpResult.getReturnSize(), "", "");
//                }else if (httpResult.getReturnCode().equals("4004")){
//                    listener.fialed(httpResult.getReturnCode(), httpResult.getReturnMsg());
//                    ToastUtil.showToast(httpResult.getReturnMsg());
//                }else if (httpResult.getReturnCode().equals("1002")){
//                    //token过期，跳到登录界面
//                    ToastUtil.showToast("您已太久未登录了，请重新登录");
//                    listener.fialed(httpResult.getReturnCode(), httpResult.getReturnMsg());
//                }else{
//                    listener.fialed(httpResult.getReturnCode(), httpResult.getReturnMsg());
//                }
                Log.e("httpResult",httpResult.toString());
                if (httpResult!=null){
//                    listener.success(httpResult.getUser());
                    listener.success((T)httpResult);
                }else{
                    Log.e("httpResult",httpResult.toString());
                    listener.fialed(httpResult.getSuccess().toString(), httpResult.getInfo().toString());
                }
            }
        };
        return subscriber;
    }


    public static void checkHttpException(Throwable mThrowable) {
//        if (NetUtil.getNetWorkState(MyApplication.getInstance()) == -1) {
//            ToastUtil.showToast("没有网络");
//            return;
//        }
        if ((mThrowable instanceof UnknownHostException)) {
            ToastUtil.show("网络异常");
        } else if (mThrowable instanceof JsonSyntaxException) {
            ToastUtil.show("数据异常");
        } else if (mThrowable instanceof SocketTimeoutException) {
            ToastUtil.show("连接超时");
        } else if (mThrowable instanceof ConnectException) {
            ToastUtil.show("连接服务器失败");
        } else {
            ToastUtil.show("操作失败");
//        }/* else if (mThrowable instanceof RuntimeException) {
//            ToastUtil.showToast(mContext,"程序出错");
//        } else {
//            ToastUtil.showToast(mContext,"网络异常");
//        }*/
        }
    }
}
