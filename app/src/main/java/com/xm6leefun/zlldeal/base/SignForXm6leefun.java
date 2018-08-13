package com.xm6leefun.zlldeal.base;


import com.xm6leefun.zlldeal.utils.MD5Utils;
import com.xm6leefun.zlldeal.utils.StringUnicode;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Administrator on 2017/11/6 0006.
 */

public class SignForXm6leefun {
    static String key = "3ddadae197ff45df49b3cfc6d0067fc0";// 秘钥
    static String api_key = "20180305";// api秘钥
//    static String device = "android";// 机型
//    static String versionName = HelpUtils.getLocalVersionName();
    // SystemTool.getDataTime()

    public static String getSing(ArrayList<String> mList) {
        // 时间戳
        long time = System.currentTimeMillis() / 1000;
         time=1525924554;
        // 头部URL
        String headTemp = "";
        // 签名字段Sign
        String nameTemp = "";
        Collections.sort(mList);
        try {
            for (int i = 0; i < mList.size(); i++) {
                headTemp = headTemp + "&" + ("data[" + enUrlCode(mList.get(i).split("=")[0]) + "]" + "="
                        + enUrlCode(mList.get(i).split("=")[1]));
                String temp = mList.get(i).split("=")[0] + mList.get(i).split("=")[1];
                nameTemp = nameTemp + temp;
            }
        } catch (Exception e) {
        }
        if (URL_GET.USER_TOKEN.equals("")) {
            return StringUnicode.decode(headTemp) + "&sign="
                    + MD5Utils.encryptMD5(MD5Utils.encryptMD5(StringUnicode.decode(nameTemp) + time) + key).toUpperCase()
                    + "&timestamp=" + time + "&api_key=" + api_key;
        } else {
            return StringUnicode.decode(headTemp) + "&sign="
                    + MD5Utils.encryptMD5(MD5Utils.encryptMD5(StringUnicode.decode(nameTemp) + time) + key).toUpperCase()
                    + "&timestamp=" + time + "&api_key=" + api_key + "&user_token=" + URL_GET.USER_TOKEN;
        }
    }

    public static String enUrlCode(String text) throws UnsupportedEncodingException {
        return java.net.URLEncoder.encode(text, "utf-8");
    }
}
