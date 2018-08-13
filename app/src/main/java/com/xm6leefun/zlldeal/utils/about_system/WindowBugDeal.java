package com.xm6leefun.zlldeal.utils.about_system;

import android.app.Activity;
import android.content.res.Resources;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/9/25 0025.
 */

public class WindowBugDeal {
    public static void checkDeviceHasNavigationBar(Activity context){
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar","bool","android");
        if (id > 0)
        {
			context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//B//
        }
    }
    public static void SetTop(Activity context){
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar","bool","android");
        if (id > 0)
        {
			context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        }
    }
}
