package com.xm6leefun.zlldeal.main_code.about_login;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.about_retrofit.ResponseResultExtendListener;
import com.xm6leefun.zlldeal.about_retrofit.UserManager;
import com.xm6leefun.zlldeal.base.AppAllKey;
import com.xm6leefun.zlldeal.base.AppManager;
import com.xm6leefun.zlldeal.base.MyBaseActivity;
import com.xm6leefun.zlldeal.base.URL_GET;
import com.xm6leefun.zlldeal.utils.ACache;
import com.xm6leefun.zlldeal.utils.NoDoubleClickUtils;
import com.xm6leefun.zlldeal.utils.SPUtils;
import com.xm6leefun.zlldeal.utils.StrUtils;
import com.xm6leefun.zlldeal.utils.ToastUtil;
import com.xm6leefun.zlldeal.utils.about_volley.VolleyInterface;
import com.xm6leefun.zlldeal.utils.about_volley.VolleyRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class LoginActivity extends MyBaseActivity {
    public static int screenWidth;
    public static int screenHeight;
    @BindView(R.id.login_ed_user)
    EditText loginEdUser;
    @BindView(R.id.login_ed_psw)
    EditText loginEdPsw;
    @BindView(R.id.login_tv_forget_psw)
    TextView loginTvForgetPsw;
    @BindView(R.id.login_btn_ok)
    Button loginBtnOk;
    private ACache mCache =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
//        initCaChe();
//        BtnToEditListenerUtils.getInstance()
//                .setBtn(loginBtnOk)
//                .addEditView(loginEdUser)
//                .addEditView(loginEdPsw)
//                .build();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
    private void initCaChe() {
        mCache = ACache.get(this);
        URL_GET.USER_TOKEN ="";
        if (mCache!=null){
            String asString = mCache.getAsString(AppAllKey.TOKEN_KEY);
            if (!StrUtils.isEmpty(asString))
            {
//                Log.e("result",asString.toString()+"token信息");
//                DataLogin.RecordBean.ShippingClerkBean dataLogin = JSON.parseObject(asString, DataLogin.RecordBean.ShippingClerkBean.class);
//                URL_GET.USER_TOKEN = dataLogin.getToken();
////               自动登录
//                IntentUtils.JumpFinishTo(MainActivity.class);
            }
        }
    }
    public static final String TAG = "LoginActivity";
    private void initHttp() {
//        final String phone = loginEdUser.getText().toString().trim();
//        final  String psw = loginEdPsw.getText().toString().trim();
//        if (StrUtils.isEmpty(phone)) {
//            Tip.getDialog(LoginActivity.this,getResources().getString(R.string.phone_is_null));
//            return;
//        }
//        if (!EditCheckUtils.isMobileNO(phone)) {
//            Tip.getDialog(LoginActivity.this,getResources().getString(R.string.phone_is_error));
//            return;
//        }
//        if (StrUtils.isEmpty(psw)) {
//            Tip.getDialog(LoginActivity.this,"密码不得为空");
//            return;
//        }
//        UserManager.getLoginGet(phone, psw, new ResponseResultExtendListener<DataLogins.UserBean>() {
//            @Override
//            public void success(DataLogins.UserBean returnMsg) {
//                ToastUtil.show("成功"+returnMsg.getMobile());
//            }
//
//            @Override
//            public void fialed(String resCode, String message) {
//                ToastUtil.show("失败"+message);
//            }
//        });
//        UserManager.normal(phone, psw, new PostSubscriber<String>().getSubscriber(new ResponseResultExtendListener<String>() {
//            @Override
//            public void success(String returnMsg) {
//                ToastUtil.show("成功"+returnMsg);
//            }
//
//            @Override
//            public void fialed(String resCode, String message) {
//                ToastUtil.show("失败"+message);
//            }
//        }));
        UserManager.postTest( new ResponseResultExtendListener<Object>() {
            @Override
            public void success(Object returnMsg) {
                ToastUtil.show("成功"+returnMsg);
            }
            @Override
            public void fialed(String resCode, String message) {
                ToastUtil.show("失败"+message);
            }
        });
//        UserManager.postLogin("18065283783", "123456", new ResponseResultExtendListener<Object>() {
//            @Override
//            public void success(Object returnMsg) {
//                ToastUtil.show("成功"+returnMsg);
//            }
//            @Override
//            public void fialed(String resCode, String message) {
//                ToastUtil.show("失败"+message);
//            }
//        });
//        ApiClient.getInstance().getLogin(TAG,phone,psw, new PostSubscriber<DataLogin>().getSubscriber(callback));
//        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
////        Log.e("URL_GET",URL_GET.login(phone, psw));
//        netWorkUtlis.setOnNetWork(URL_GET.login(phone, psw), new NetWorkUtlis.OnNetWork() {
//            @Override
//            public void onNetSuccess(String msg) {
//                if (!StrUtils.isEmpty(msg)&& HelpUtils.IsSucessRecord(msg)) {
//                    Log.e("msg",msg);
//                    try {
//                        SPUtils.put(LoginActivity.this, AppAllKey.SP_LOGIN_ACCOUNT, phone);
//                        SPUtils.put(LoginActivity.this, AppAllKey.SP_LOGIN_PSW, "");
//                        DataLogins dataLogin = JSON.parseObject(msg, DataLogins.class);
//                        DataLogins.RecordBean record = dataLogin.getRecord();
//                        if (record!=null) {
//                            DataLogins.RecordBean.ShippingClerkBean shippingClerk = record.getShippingClerk();
//                            if (shippingClerk != null) {
//                                SaveLoginResultData(shippingClerk);
//                                IntentUtils.JumpFinishTo(MainActivity.class);
//                            }
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });

    }
//    public ResponseResultExtendListener<DataLogin> callback = new ResponseResultExtendListener<DataLogin>() {
//        @Override
//        public void success(DataLogin returnMsg) {
//            ToastUtil.show("成功");
//        }
//
//        @Override
//        public void fialed(String resCode, String message) {
//            ToastUtil.show("失败");
//        }
//    };

//    private void SaveLoginResultData( DataLogin.RecordBean.ShippingClerkBean dataBean) {
//        URL_GET.USER_TOKEN = dataBean.getToken();
//        SPUtils.put(LoginActivity.this,AppAllKey.User_REAL_NAME,dataBean.getRealName());
//        SPUtils.put(LoginActivity.this,AppAllKey.User_ID,dataBean.getId());
//        String json = JSON.toJSON(dataBean).toString();
//        mCache.clear();
//        mCache.put(AppAllKey.TOKEN_KEY, json);
//    }
    @Override
    protected void onResume() {
        super.onResume();
        try {
            String account = (String) SPUtils.get(LoginActivity.this,AppAllKey.SP_LOGIN_ACCOUNT, "");
            loginEdUser.setText(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String psw = (String)SPUtils.get(LoginActivity.this,AppAllKey.SP_LOGIN_PSW, "");
            loginEdPsw.setText(psw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //    拦截右滑退出
    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected boolean isTopBack() {
        return false;
    }
int code =0;
    @OnClick({R.id.login_tv_forget_psw, R.id.login_btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_tv_forget_psw:


//                code++;
//                DialogUtils.showDialog("123"+code);
////                Tip.getDialog(LoginActivity.this,"123"+code);
//                NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//////                String string  = "http://192.168.1.20:8080/api/block/bindBlock?";
//////                string+
//////                map.put("num","100");
//////                map.put("name","android");
//////                map.put("sum","666");
////
//////            String s = MD5Utils.encryptMD5("{name=456, num=456, password=456, user_name=123}");
//////                String s =  MD5Utils.encryptMD5(StringUnicode.decode(MD5Utils.encryptMD5(StringUnicode.decode(string)) + "xm6leefun"));
//////                Log.e("MD5Utils","s=="+s);
//                netWorkUtlis.setOnNetWorkNormal(URL_GET.ttt(), new NetWorkUtlis.OnNetWork() {
//                    @Override
//                    public void onNetSuccess(String result) {
//                        Log.e("MD5Utils","这是后台的返回值=="+result);
//                    }
//                });
//                默认升序
//                Map<String, String> map = new TreeMap<>();
////                Map<String, String> map = new TreeMap<String, String>(
////                        new Comparator<String>() {
////                            public int compare(String obj1, String obj2) {
////                                // 降序排序
////                                return obj2.compareTo(obj1);
////                            }
////                        });
                ToastUtil.show("点击了测试post");
                Map<String, String> map = new HashMap<>();
                map.put("mobile","18065283783");
                map.put("pwd","123456");
//                String string = maps.toString();
////                String s = MD5Utils.encryptMD5(string);
//                String s =  MD5Utils.encryptMD5(StringUnicode.decode(MD5Utils.encryptMD5(StringUnicode.decode(string)) + "xm6leefun"));
////                String s =  MD5Utils.encryptMD5(MD5Utils.encryptMD5(string) + "xm6leefun");
////                String s1 = StringUnicode.decode(s);
//                map.put("outsideSign",s);
//                String string1 = map.toString();
//                Log.e("MD5Utils","map=="+string+"最后map=="+string1);
//                Log.e("MD5Utils","md后=="+s);
                VolleyRequest.RequestPost(AppManager.getAppManager().currentActivity(), "http://116.62.240.103:8080/api/user/login?", "", map, new VolleyInterface(VolleyInterface.listener,VolleyInterface.errorListener) {
//                VolleyRequest.RequestPost(AppManager.getAppManager().currentActivity(), "http://192.168.1.21:8080/api/user/login?", "", map, new VolleyInterface(VolleyInterface.listener,VolleyInterface.errorListener) {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("result","这是后台的返回值=="+result);
                        ToastUtil.show(result);
                    }
                    @Override
                    public void onError(VolleyError result) {

                    }
                });
//                if (NoDoubleClickUtils.isDoubleClick())
//                    IntentUtils.JumpTo(ForgetPswActivity.class);
                break;
            case R.id.login_btn_ok:
                if (NoDoubleClickUtils.isDoubleClick())
                    initHttp();
                break;
        }
    }
}
