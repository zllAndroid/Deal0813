package com.xm6leefun.zlldeal.main_code.about_mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.AppAllKey;
import com.xm6leefun.zlldeal.base.AppManager;
import com.xm6leefun.zlldeal.base.MyBaseActivity;
import com.xm6leefun.zlldeal.base.URL_GET;
import com.xm6leefun.zlldeal.utils.BtnToEditListenerUtils;
import com.xm6leefun.zlldeal.utils.DialogUtils;
import com.xm6leefun.zlldeal.utils.NetWorkUtlis;
import com.xm6leefun.zlldeal.utils.StrUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MineAdvicesActivity extends MyBaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;

    @Nullable
    @BindView(R.id.feed_ed_advice)
    EditText feedEdAdvice;
    @Nullable
    @BindView(R.id.feed_ed_phone)
    EditText feedEdPhone;
    @Nullable
    @BindView(R.id.feed_btn_ok)
    Button feedBtnOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void initBaseUI() {
        super.initBaseUI();
        includeTopTvTital.setText("意见反馈");
        BtnToEditListenerUtils.getInstance()
                .setBtn(feedBtnOk)
                .addEditView(feedEdAdvice)
                .addEditView(feedEdPhone)
                .build();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_feedback;
    }
    @OnClick(R.id.feed_btn_ok)
    public void onViewClicked() {
        String mEdAdvice = feedEdAdvice.getText().toString().trim();
        String mEdPhone = feedEdPhone.getText().toString().trim();
        if (StrUtils.isEmpty(mEdAdvice))
        {
            DialogUtils.showDialog("反馈内容不能为空");
            return;
        }
        if (StrUtils.isEmpty(mEdPhone))
        {
            DialogUtils.showDialog("联系方式不能为空");
            return;
        }
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(AppAllKey.LodingFlower, URL_GET.feedBack(mEdAdvice,mEdPhone), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String result) {
                DialogUtils.showDialogOne("您的意见反馈提交成功，我们会尽快处理", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        AppManager.getAppManager().finishActivity();
                    }
                });
//                Tip.getDialog(MineAdvicesActivity.this,"您的意见反馈提交成功，我们会尽快处理",true);
            }
        });

    }
}
