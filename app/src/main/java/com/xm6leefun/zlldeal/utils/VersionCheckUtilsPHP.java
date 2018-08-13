package com.xm6leefun.zlldeal.utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.xm6leefun.model.DataUpVersion;
import com.xm6leefun.model.DataUpVersionPHP;
import com.xm6leefun.zlldeal.base.AppManager;
import com.xm6leefun.zlldeal.base.CommonParameter;
import com.xm6leefun.zlldeal.base.URL_GET;
import com.xm6leefun.zlldeal.utils.about_volley.VolleyInterface;
import com.xm6leefun.zlldeal.utils.about_volley.VolleyRequest;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class VersionCheckUtilsPHP {
    static DataUpVersionPHP.RecordBean record = null;
    public static  void initUpdata(final  boolean isReturn) {
        final int localVersion = HelpUtils.getLocalVersion();
        VolleyRequest.RequestGet(AppManager.getAppManager().currentActivity(), URL_GET.appUpdate(), new VolleyInterface(VolleyInterface.listener, VolleyInterface.errorListener) {
            @Override
            public void onSuccess(final String result) {
                MyLog.e("result", "请求结果result----------==" + result);
                final String sucess = HelpUtils.HttpIsSucess(result);
                if (sucess.equals(CommonParameter.CODE_SUCCESS)) {
//                    是否主动请求，无版本更新会弹出提示
                    if (!isReturn)
                    {
                        initJson(result);
                    }else
                    {
                        initJsonReturn(result);
                    }

                } else if (sucess.equals(CommonParameter.CODE_TIMEOUT)) {
                    //超时
                } else {
                    ToastUtil.show(sucess);
                }
            }
            @Override
            public void onError(VolleyError result) {
                ToastUtil.show(CommonParameter.ERROR);
//                Tip.getError(CommonParameter.ERROR);
            }
        });
    }
    public static void initJson(String msg) {
        if (!StrUtils.isEmpty(msg))
        {
            try {
                DataUpVersionPHP dataUpVersion = JSON.parseObject(msg, DataUpVersionPHP.class);
                record = dataUpVersion.getRecord();
                if (record != null) {
                    String is_update = record.getIs_update();
                    if (!StrUtils.isEmpty(is_update))
                    if (is_update.equals("1"))
                    {
                        if (record.getForced_update().equals("1"))
                        {
                            try {
                                new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getDown_url(), false, record.getMsg());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else
                        {
                            try {
                                new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getDown_url(), true, record.getMsg());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }else
                    {
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void initJsonReturn(String msg) {
        if (!StrUtils.isEmpty(msg))
        {
            try {
                DataUpVersionPHP dataUpVersion = JSON.parseObject(msg, DataUpVersionPHP.class);
                record = dataUpVersion.getRecord();
                if (record != null) {
                    String is_update = record.getIs_update();
                    if (!StrUtils.isEmpty(is_update))
                    if (is_update.equals("1"))
                    {
                        if (record.getForced_update().equals("1"))
                        {
                            try {
                                new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getDown_url(), false, record.getMsg());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else
                        {
                            try {
                                new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getDown_url(), true, record.getMsg());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }else
                    {
                       Tip.getDialog(AppManager.getAppManager().currentActivity(),"已经是最新版本");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
