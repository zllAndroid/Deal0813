package com.xm6leefun.zlldeal.main_code.about_nfc;

import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.MyBaseActivity;
import com.xm6leefun.zlldeal.utils.ToastUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//商户入驻
public class NFCActivity extends MyBaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.editText2)
    EditText editText;
    private Tag tag;
    NfcUtils nfcUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        ButterKnife.bind(this);
        includeTopTvTital.setText("NFC识别");
        nfcUtils = new NfcUtils(this);
    }
    Intent  intent =null;
    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.intent=intent;
        setIntent(intent);
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

//    @Override
//    protected void onPause() {
//        super.onPause();
//        //关闭前台调度系统
//        try {
//            NfcUtils.mNfcAdapter.disableForegroundDispatch(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @OnClick({R.id.btn_write, R.id.btn_read})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_read:
                String str = "读取失败";
//              Intent  intent = (Intent)getIntent().getParcelableExtra(NfcAdapter.ext);
                try {
                    if (intent!=null)
                    str = NfcUtils.readNFCFromTag(intent);
                    if (str!=null)
                    {
                        ToastUtil.show("读取成功");
                    }else
                    {
                        ToastUtil.show("读取失败");
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    ToastUtil.show("读取失败");
                }
                textView1.setText(str);
                break;
            case R.id.btn_write:
//                ToastUtil.show("点击了写");
                writeTag();
                break;
        }
    }

//    public void readTag() {
//        NdefMessage msg = null;
//
//        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())){
//
//            tag = (Tag)getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
//
//            if (tag !=null){
//
//                Ndef ndef = Ndef.get(tag);
//
//                msg = ndef.getCachedNdefMessage();
//
//                for (NdefRecordrecord:msg.getRecords()) {
//                    if (record != null) {
//                        String payload = new String(record.getPayload());
//                        String actualData = payload.substring(3);
//                        textView1.setText(actualData);
//                        textView1.setTextColor(Color.WHITE);
//                    } else {
//
//                        Log.i("MyNFCApp", "No record detected");
//
//                    }
//                }
//
//            } else {
//
//                Log.i("MyNFCApp","No tag detected");
//
//            }
//
//        }
//
//    }


    public void writeTag() {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())){
            tag = (Tag)getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if (tag !=null){
                Ndef ndef = Ndef.get(tag);
                    try {
                        ndef.connect();
                    } catch (IOException e) {
                        e.printStackTrace();
                        ToastUtil.show("写入失败1");
                    }
                    String payload = editText.getText().toString().trim();
//                    String payload = "春眠不觉晓，处处闻啼鸟。夜来风雨声，花落知多少。";
                    NdefRecord record = createTextRecord(payload, Locale.getDefault(),true);
                    NdefMessage msg = new NdefMessage(new NdefRecord[] {record});
                    int maxSize = ndef.getMaxSize();
                    if (msg.toByteArray().length >maxSize) {
                        Log.i("MyNFCApp","要写入的数据长度已经超过限定值");
                        return;
                    } else {
                        try {
                            ndef.writeNdefMessage(msg);
                            ToastUtil.show("写入成功");
                        } catch (IOException e) {
                            e.printStackTrace();
                            ToastUtil.show("写入失败2");
                        } catch (FormatException e) {
                            e.printStackTrace();
                            ToastUtil.show("写入失败3");
                        }
                    }
                        try {
                            ndef.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        }

    public NdefRecord createTextRecord(String payload, Locale locale,boolean encodeInUtf8) {

        byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII"));

        Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF-8"): Charset.forName("UTF-16");

        byte[] textBytes = payload.getBytes(utfEncoding);

        int utfBit = encodeInUtf8? 0 : (1 << 7);

        char status = (char)(utfBit+langBytes.length);

        byte[] data = new byte[1+langBytes.length +textBytes.length];

        data[0] = (byte)status;

        System.arraycopy(langBytes, 0,data, 1,langBytes.length);

        System.arraycopy(textBytes, 0,data, 1 +langBytes.length,textBytes.length);

        NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,NdefRecord.RTD_TEXT,new byte[0],data);
        return record;
    }
}
