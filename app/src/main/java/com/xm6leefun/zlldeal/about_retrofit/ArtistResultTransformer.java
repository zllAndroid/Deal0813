package com.xm6leefun.zlldeal.about_retrofit;

import rx.functions.Func1;

/**
 * Created by zhoujiang on 2017/6/28 16:20.
 */

public class ArtistResultTransformer<T> implements Func1<ResponseParent<T>,T> {
    @Override
    public T call(ResponseParent<T> tArtistResult) {
        if (tArtistResult.getSuccess().equals("0")) {
            throw new RuntimeException();
        }
        return tArtistResult.getUser();
    }
}
