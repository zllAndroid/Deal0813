package com.xm6leefun.zlldeal.main_code.about_nfc;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.MyBaseActivity;

import java.io.IOException;
import java.nio.charset.Charset;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//商户入驻
public class NFCMLActivity extends MyBaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.editText2)
    EditText editText;
    //    private Tag tag;
    NfcUtils nfcUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        ButterKnife.bind(this);
        includeTopTvTital.setText("NFC");
        nfcUtils = new NfcUtils(this);
    }

    Intent intent = null;
    Tag tag = null;

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        this.intent=intent;
        tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String[] techList = tag.getTechList();
        boolean flag = false;
        for (String tech : techList) {
            if (tech.indexOf("MifareUltralight") >= 0) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            Toast.makeText(this, "不支持MifareUltralight数据格式", Toast.LENGTH_LONG).show();
            return;
        }

//        if (mWriteData.isChecked()){
//
//        }else {
//            String data = readTag(tag);
//            if (data != null)
//                Toast.makeText(this, data, Toast.LENGTH_LONG).show();
//        }
//        setIntent(intent);

//        String str = "111";
//        try {
//            str = nfcUtils.readNFCFromTag(intent);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        ToastUtil.show(str);
//        textView1.setText(str);
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
    protected void onDestroy() {
        super.onDestroy();
        try {
            NfcUtils.mNfcAdapter.disableForegroundDispatch(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_write, R.id.btn_read})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_read:
//                String str = "读取失败";
////              Intent  intent = (Intent)getIntent().getParcelableExtra(NfcAdapter.ext);
//                try {
//                    if (intent!=null)
//                    str = NfcUtils.readNFCFromTag(intent);
//                    if (str!=null)
//                    {
//                        ToastUtil.show("读取成功");
//                    }else
//                    {
//                        ToastUtil.show("读取失败");
//                    }
//
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                    ToastUtil.show("读取失败");
//                }
                textView1.setText(readTag());
//                readTag(tag);
                break;
            case R.id.btn_write:
//                ToastUtil.show("点击了写");
                writeTag();
//                writeTag();
                break;
        }
    }

    private String readTag() {
        if (tag==null)
        {
            Toast.makeText(this, "tag为空", Toast.LENGTH_SHORT).show();
            return "tag为空";
        }
        MifareUltralight light = MifareUltralight.get(tag);
        try {
            light.connect();
            byte[] bytes = light.readPages(4);
            return new String(bytes, Charset.forName("GB2312"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void writeTag() {
        if (tag==null)
        {
            Toast.makeText(this, "tag为空", Toast.LENGTH_SHORT).show();
            return;
        }
        MifareUltralight light = MifareUltralight.get(tag);
        try {
            light.connect();
            light.writePage(4,"china1".getBytes(Charset.forName("GB2312")));
            light.writePage(5,"china2".getBytes(Charset.forName("GB2312")));
            light.writePage(6,"china3".getBytes(Charset.forName("GB2312")));
            light.writePage(7,"china4".getBytes(Charset.forName("GB2312")));
            Toast.makeText(this, "成功写入MifareUltralight格式数据!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                light.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
