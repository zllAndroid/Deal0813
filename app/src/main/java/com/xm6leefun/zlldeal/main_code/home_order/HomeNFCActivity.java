package com.xm6leefun.zlldeal.main_code.home_order;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xm6leefun.model.DataNfc;
import com.xm6leefun.model.RealmTaskResule;
import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.AppAllKey;
import com.xm6leefun.zlldeal.base.AppManager;
import com.xm6leefun.zlldeal.base.CommonParameter;
import com.xm6leefun.zlldeal.base.MyBaseActivity;
import com.xm6leefun.zlldeal.base.URL_GET;
import com.xm6leefun.zlldeal.main_code.about_realm.RealmHelper;
import com.xm6leefun.zlldeal.utils.DialogUtils;
import com.xm6leefun.zlldeal.utils.IntentUtils;
import com.xm6leefun.zlldeal.utils.NetWorkUtlis;
import com.xm6leefun.zlldeal.utils.SPUtils;
import com.xm6leefun.zlldeal.utils.StrUtils;
import com.xm6leefun.zlldeal.utils.Tip;
import com.xm6leefun.zlldeal.utils.ToastUtil;

import java.nio.charset.Charset;
import java.util.Locale;

import butterknife.BindView;

//商户入驻
public class HomeNFCActivity extends MyBaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    String qrCode =null;
    private RealmHelper mRealmHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_nfc);
//        ButterKnife.bind(this);
        includeTopTvTital.setText("写入标签");
        initGetData();
        mRealmHelper = new RealmHelper(this);
    }

    private void initGetData() {
        Intent intent = getIntent();
        if (intent!=null)
        {
            qrCode = intent.getStringExtra("qrCode");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home_nfc;
    }
    @Override
    protected boolean isNFC() {
        return true;
    }
    Tag tag =null;
    @Override
    public void onNewIntent(Intent intent) {
        String uid = "";
        tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag!=null) {
            byte[] aa = tag.getId();
            uid += bytesToHexString(aa);//获取卡的UID
            Log.e("uid",uid);
            if (!StrUtils.isEmpty(uid)) {
                RealmTaskResule realmTaskResule = mRealmHelper.queryResultByUid(uid);
                if (!mRealmHelper.isHaveExistBynfcUid(uid)) {
                    NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
                    String id = (String) SPUtils.get(HomeNFCActivity.this, AppAllKey.User_ID, "");
                    netWorkUtlis.setOnNetWork(URL_GET.checkNfcUid(id, uid), this);
                } else {
                    ToastUtil.show("该nfc已被使用，请更换");
//                    Tip.getDialog(HomeNFCActivity.this,"该nfc已被使用，请更换");
//                    DialogUtils.showDialog("该nfc已被使用，请更换");
//                    Tip.getDialog(HomeNFCActivity.this, "该nfc已被使用，请更换");
                }
            }else
            {
                ToastUtil.show("获取标签序列号失败，请重试");
            }
        }
    }

    @Override
    public void onHandleMessage(Message msg) {
        super.onHandleMessage(msg);
        switch (msg.what) {
            case CommonParameter.HANDLE_MSG_SUCCESS:
                if (!StrUtils.isEmpty(msg.obj.toString())) {
                    DataNfc dataHomeDetails = JSON.parseObject(msg.obj.toString(), DataNfc.class);
                    DataNfc.RecordBean taskData = dataHomeDetails.getRecord();
                    if (taskData.getFlag().equals("0"))
                    {
                        if ( writeTag(tag,taskData.getNfcUrl()))
                        {
                            RealmTaskResule realmTaskResule = new RealmTaskResule();
//                            realmTaskResule.setExpressNum("123"+string);
                            realmTaskResule.setQrcode(qrCode);
                            realmTaskResule.setNfccode(taskData.getNfcCode());
                            realmTaskResule.setNfcUid(taskData.getNfcUid());
//                            realmTaskResule.setId(string+"");
                            realmTaskResule.setTaskId(HomeDetailsActivity.taskId);
//                          mRealmList.add(realmTaskResule);
                            if (!mRealmHelper.isHaveExist(HomeDetailsActivity.taskId)) {
//                          mRealmList.add(realmTaskResule);
                                mRealmHelper.addDog(realmTaskResule);
                            } else {
                                ToastUtil.show("已经存在");
                                return;
                            }
//                            DialogUtils.showDialog("写入成功", new DialogUtils.OnClickSureListener() {
//                                @Override
//                                public void onClickSure() {
//                                    AppManager.getAppManager().finishActivity();
//                                }
//                            });
                            DialogUtils.showDialog("写入成功", new DialogUtils.OnClickSureListener() {
                                @Override
                                public void onClickSure() {
                                    AppManager.getAppManager().finishActivity();
                                }
                            });
//                            Tip.getDialog(HomeNFCActivity.this, "写入成功", new Tip.OnClickSureListener() {
//                                @Override
//                                public void onClickSure() {
//                                    AppManager.getAppManager().finishActivity();
//                                }
//                            });
                        }
                        else {
                            ToastUtil.show("写入失败");
//                            DialogUtils.showDialog("写入失败");
//                            Tip.getDialog(HomeNFCActivity.this,"写入失败");
                        }
                    }else if (taskData.getFlag().equals("1"))
                    {
                        ToastUtil.show(taskData.getNfcMsg());
                    }
//                    initUI(taskData);
                }
                break;
        }
    }
    private void MakeDialog(final String tital,final String qrCode) {
        Tip.getDialog(HomeNFCActivity.this, tital , new Tip.OnClickSureListener() {
            @Override
            public void onClickSure() {
                IntentUtils.JumpToHaveOne(HomeNFCActivity.class,"qrCode",qrCode);
            }
        }, new Tip.OnClickCancleListener() {
            @Override
            public void onClickCancle() {

            }
        });
    }
    //字符序列转换为16进制字符串
    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (int i = 0; i < src.length; i++) {
            buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(src[i] & 0x0F, 16);
            stringBuilder.append(buffer);
        }
        return stringBuilder.toString();
    }
    /**
     * 写数据
     *
     //     * @param ndefMessage 创建好的NDEF文本数据
     * @param tag         标签
     * @return
     */
    public static boolean writeTag( Tag tag,String WriteText) {
        try {
//            NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{createUriRecord(mUri)});
            NdefMessage msg = new NdefMessage(
                    new NdefRecord[] {
                            createTextRecord(WriteText)
//            NdefRecord.createApplicationRecord("com.example.android.beam")
                    });
            Ndef ndef = Ndef.get(tag);
            boolean b = ndef.canMakeReadOnly();
            ndef.connect();
            ndef.writeNdefMessage(msg);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    /**
     * 创建NDEF文本数据
     *
     * @param text
     * @return
     */
    public static NdefRecord createTextRecord(String text) {
        byte[] langBytes = Locale.CHINA.getLanguage().getBytes(Charset.forName("US-ASCII"));
        Charset utfEncoding = Charset.forName("UTF-8");
        //将文本转换为UTF-8格式
        byte[] textBytes = text.getBytes(utfEncoding);
        //设置状态字节编码最高位数为0
        int utfBit = 0;
        //定义状态字节
        char status = (char) (utfBit + langBytes.length);
        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        //设置第一个状态字节，先将状态码转换成字节
        data[0] = (byte) status;
        //设置语言编码，使用数组拷贝方法，从0开始拷贝到data中，拷贝到data的1到langBytes.length的位置
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        //设置文本字节，使用数组拷贝方法，从0开始拷贝到data中，拷贝到data的1 + langBytes.length
        //到textBytes.length的位置
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);
        //通过字节传入NdefRecord对象
        //NdefRecord.RTD_TEXT：传入类型 读写
        NdefRecord ndefRecord = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                NdefRecord.RTD_TEXT, new byte[0], data);
        return ndefRecord;
    }

}
