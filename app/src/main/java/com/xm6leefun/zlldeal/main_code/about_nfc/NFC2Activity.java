package com.xm6leefun.zlldeal.main_code.about_nfc;

import android.content.Intent;
import android.nfc.FormatException;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.MyBaseActivity;
import com.xm6leefun.zlldeal.utils.ToastUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//商户入驻
public class NFC2Activity extends MyBaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.scan_tv)
    TextView scan;
    @BindView(R.id.text)
    EditText mEdText;
    NfcUtils nfcUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_purse);
        ButterKnife.bind(this);
        includeTopTvTital.setText("NFC识别");
         nfcUtils = new NfcUtils(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开启前台调度系统
        try {
            NfcUtils.mNfcAdapter.enableForegroundDispatch(this, NfcUtils.mPendingIntent, NfcUtils.mIntentFilter, NfcUtils.mTechList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //关闭前台调度系统
        try {
            NfcUtils.mNfcAdapter.disableForegroundDispatch(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Intent intent = null;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.intent = intent;
        //当该Activity接收到NFC标签时，运行该方法
        //调用工具方法，读取NFC数据
        String str = "111";
        try {
            str = nfcUtils.readNFCFromTag(intent);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ToastUtil.show(str);
        scan.setText(str);

    }

    @OnClick(R.id.btn_sure)
    public void onViewClicked()  {
        ToastUtil.show("点击了写");
        String trim = mEdText.getText().toString().trim();
        try {
            NfcUtils.writeNFCToTag(trim,intent);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }

    }
}
