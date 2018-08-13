package com.xm6leefun.zlldeal.main_code.mains;


import android.content.SyncStatusObserver;

import com.xm6leefun.zlldeal.utils.MyLog;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/6/21 0021.
 */

public class tast {

    public static  void main(String[] args)
    {
      Observable mObser = rx.Observable.create(new Observable.OnSubscribe<String>() {
          @Override
          public void call(Subscriber<? super String> subscriber) {
             subscriber.onNext("hello world");
             subscriber.onCompleted();
          }
      });
      Subscriber subscriber  = new Subscriber<String>() {
          @Override
          public void onCompleted() {
              MyLog.e("Observable","onCompleted");
          }

          @Override
          public void onError(Throwable throwable) {
              MyLog.e("Observable","onError");
              System.out.println("onError");
          }

          @Override
          public void onNext(String s) {
//              System.out.println("onNext"+s);
              MyLog.e("Observable","onNext"+s);
          }

      };
        mObser.subscribe(subscriber);
    }
}
