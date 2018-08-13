package com.xm6leefun.zlldeal.main_code.about_login.child_login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xm6leefun.model.DataLogin;
import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.AppAllKey;
import com.xm6leefun.zlldeal.base.URL_GET;
import com.xm6leefun.zlldeal.main_code.about_login.ForgetPswActivity;
import com.xm6leefun.zlldeal.main_code.mains.MainActivity;
import com.xm6leefun.zlldeal.utils.ACache;
import com.xm6leefun.zlldeal.utils.DialogUtils;
import com.xm6leefun.zlldeal.utils.EditCheckUtils;
import com.xm6leefun.zlldeal.utils.IntentUtils;
import com.xm6leefun.zlldeal.utils.MyLog;
import com.xm6leefun.zlldeal.utils.NetWorkUtlis;
import com.xm6leefun.zlldeal.utils.NoDoubleClickUtils;
import com.xm6leefun.zlldeal.utils.SPUtils;
import com.xm6leefun.zlldeal.utils.StrUtils;
import com.xm6leefun.zlldeal.utils.ToastUtil;

import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneLoginFragment extends Fragment {
    Unbinder unbinder;

    //    @BindView(R.id.login_one_ed_psw)
    EditText loginOneEdPsw;

    //    @BindView(R.id.login_one_ed_user)
    EditText loginOneEdUser;

    //    @BindView(R.id.login_one_btn_ok)
    Button loginOneBtnOk;

    //    @BindView(R.id.login_one_tv_forget)
    TextView loginOneTvForget;


    //    @BindView(R.id.login_two_ed_user)
    EditText loginTwoEdUser;


    //    @BindView(R.id.login_two_ed_code)
    EditText loginTwoEdCode;


    //    @BindView(R.id.login_two_btn_ok)
    Button loginTwoBtnOk;

    //    @BindView(R.id.login_two_tv_getcode)
    TextView loginTwoTvGetcode;


    //    @BindView(R.id.login_two_tv_forget)
    TextView loginTwoTvForget;

    public OneLoginFragment() {
    }
    View view;
    private ACache mCache =null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int position = bundle.getInt("position");
        String text = (String) bundle.get("text");
        if (view == null) {
            if (position == 0) {
                view = inflater.inflate(R.layout.fragment_login_one, container, false);
                initUIOne();
            } else {
                view = inflater.inflate(R.layout.fragment_login_two, container, false);
                initUITwo();
            }
        }
        mCache = ACache.get(getActivity());
//        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initUITwo() {
        loginTwoEdUser= view.findViewById(R.id.login_two_ed_user);
        loginTwoEdCode= view.findViewById(R.id.login_two_ed_code);

        loginTwoBtnOk= view.findViewById(R.id.login_two_btn_ok);

        loginTwoTvGetcode= view.findViewById(R.id.login_two_tv_getcode);

        loginTwoTvForget= view.findViewById(R.id.login_two_tv_forget);
        loginTwoTvForget.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.JumpTo(  ForgetPswActivity.class);
            }
        });
//        获取验证码
        loginTwoTvGetcode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NoDoubleClickUtils.isDoubleClick())
                    initSend();
            }
        });
//        验证码登录
        loginTwoBtnOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NoDoubleClickUtils.isDoubleClick())
                    initNext();
            }
        });
//忘记密码
        loginTwoTvForget.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(ForgetPswActivity.class);
            }
        });
    }
    private void initNext() {
        String phone = loginTwoEdUser.getText().toString().trim();
        String code = loginTwoEdCode.getText().toString().trim();
        if (!EditCheckUtils.isMobileNO(phone)) {
            DialogUtils.showDialog( getResources().getString(R.string.phone_is_error));
            return;
        }
        if (StrUtils.isEmpty(code)) {
            DialogUtils.showDialog( getResources().getString(R.string.code_is_null));
            return;
        }
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(AppAllKey.LodingFlower,URL_GET.userSmsLogin(phone, code), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String result) {
                DataLogin dataLogin = JSON.parseObject(result, DataLogin.class);
                DataLogin.RecordBean record = dataLogin.getRecord();
                if (record!=null)
                {
                    SaveLoginResultData(record);
                    IntentUtils.JumpFinishTo(MainActivity.class);
                }



//                IntentUtils.JumpToHaveOne(ResetPayPswAgainActivity.class,
//                        ResetPayPswAgainActivity.ResetPayPswAgainKEY,ResetPayPswAgainActivity.ResetPayPswAgainCode);
//                AppManager.getAppManager().finishActivity();
            }
        });
    }
    private void initSend() {
        String phone = loginTwoEdUser.getText().toString().trim();
        if (!EditCheckUtils.isMobileNO(phone)) {
            DialogUtils.showDialog( getResources().getString(R.string.phone_is_error));
            return;
        }
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(AppAllKey.LodingFlower,URL_GET.smsCode(phone, "7"), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String result) {
                ToastUtil.show("短信发送中,请查收");
                timer.start();
            }
        });
    }
    private CountDownTimer timer =new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            loginTwoTvForget.setText((l / 1000) + "s");
            loginTwoTvForget.setClickable(false);
        }
        @Override
        public void onFinish() {
            loginTwoTvForget.setEnabled(true);
            loginTwoTvForget.setClickable(true);
            loginTwoTvForget.setText("获取验证码");
        }
    };
    private void initUIOne() {
        loginOneEdUser = view.findViewById(R.id.login_one_ed_user);
        loginOneEdPsw = view.findViewById(R.id.login_one_ed_psw);
        loginOneBtnOk = view.findViewById(R.id.login_one_btn_ok);
        loginOneTvForget = view.findViewById(R.id.login_one_tv_forget);
        loginOneTvForget.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.JumpTo(  ForgetPswActivity.class);
            }
        });

        loginOneBtnOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                initHttpOne();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }

    private void initHttpOne() {
        final String phone = loginOneEdUser.getText().toString().trim();
        final  String psw = loginOneEdPsw.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
//            Tip.getDialog(getActivity(),getResources().getString(R.string.phone_is_null));
            DialogUtils.showDialog(getResources().getString(R.string.phone_is_null));
            return;
        }
        if (!EditCheckUtils.isMobileNO(phone)) {
            DialogUtils.showDialog(getResources().getString(R.string.phone_is_error));
//            Tip.getDialog(getActivity(),getResources().getString(R.string.phone_is_error));
            return;
        }
        if (StrUtils.isEmpty(psw)) {
            DialogUtils.showDialog(getResources().getString(R.string.psw_is_null));
//            Tip.getDialog(getActivity(),"密码不得为空");
            return;
        }
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(URL_GET.userLogin(phone, psw), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String msg) {
                MyLog.e("url",msg);
                if (!StrUtils.isEmpty(msg)) {
                    try {
                        SPUtils.put(getActivity(), AppAllKey.SP_LOGIN_ACCOUNT, phone);
                        SPUtils.put(getActivity(), AppAllKey.SP_LOGIN_PSW, psw);
                        DataLogin dataLogin = JSON.parseObject(msg, DataLogin.class);
                        DataLogin.RecordBean record = dataLogin.getRecord();
                        if (record != null){
                            SaveLoginResultData(record);
                            IntentUtils.JumpFinishTo(MainActivity.class);
                        }else {
                            ToastUtil.show("登陆参数有误");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void SaveLoginResultData( DataLogin.RecordBean dataBean) {
        URL_GET.USER_TOKEN = dataBean.getToken();
        URL_GET.USER_HEAD = dataBean.getHeadUrl();
        URL_GET.USER_NAME = dataBean.getNickName();
        URL_GET.USER_PHONE = dataBean.getMobile();
        URL_GET.USER_IS_PSW = dataBean.getIssetPassword();
        SPUtils.put(getActivity(),AppAllKey.User_NI_NAME,dataBean.getNickName());
        SPUtils.put(getActivity(),AppAllKey.User_HEAD_URL,dataBean.getHeadUrl());
        SPUtils.put(getActivity(),AppAllKey.User_PHONE,dataBean.getMobile());
        SPUtils.put(getActivity(),AppAllKey.User_IS_SET_PSW,dataBean.getIssetPassword());
        String json = JSON.toJSON(dataBean).toString();
        mCache.clear();
        mCache.put(AppAllKey.TOKEN_KEY, json);
    }
}
