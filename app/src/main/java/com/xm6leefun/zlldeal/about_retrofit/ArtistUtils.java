package com.xm6leefun.zlldeal.about_retrofit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhoujiang on 2017/6/28 16:24.
 */

public class ArtistUtils {

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

//    public static String formatImageUrl(String url) {
//        if (TextUtils.isEmpty(url)) {
//            return "";
//        }
//
//        if (url.startsWith("http://") || url.startsWith("https://")) {
//            return url;
//        }
//
//        if (url.startsWith("/")) {
//            return Constant.IMG_SERVER_URL + url;
//        } else {
//            return Constant.IMG_SERVER_URL + "/" + url;
//        }
//    }

}
