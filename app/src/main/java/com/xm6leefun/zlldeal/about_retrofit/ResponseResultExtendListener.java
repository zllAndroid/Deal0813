package com.xm6leefun.zlldeal.about_retrofit;

/**
 * 描述：
 * 创建作者： tushicong
 * 创建时间： 2018/2/24 14:01
 **/
public interface ResponseResultExtendListener<T> {
     void success(T returnMsg);
     void fialed(String resCode, String message);
}
