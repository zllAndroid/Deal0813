package com.xm6leefun.zlldeal.main_code.about_nfc;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.MyBaseActivity;
import com.xm6leefun.zlldeal.utils.Tip;
import com.xm6leefun.zlldeal.utils.ToastUtil;

import java.io.IOException;

//商户入驻
public class NFC3Activity extends MyBaseActivity {
    TextView tv1, tv2, tv3;
    EditText etSector, etBlock, etData;
    // private NfcAdapter nfcAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private int mCount = 0;
    String info = "";

    private int bIndex;
    private int bCount;

    private int BlockData;
    private String BlockInfo;
    private RadioButton mRead, mWriteData, mChange;
    private byte[] b3;
    byte[] code= MifareClassic.KEY_NFC_FORUM;//读写标签中每个块的密码
    private byte[] data3, b0;
    private String temp = "";
    private NfcAdapter mNfcAdapter;
    private Context mContext;
    int block[] = { 4, 5, 6, 8, 9, 10, 12, 13, 14, 16, 17, 18, 20, 21, 22, 24,
            25, 26, 28, 29, 30, 32, 33, 34, 36, 37, 38, 40, 41, 42, 44, 45, 46,
            48, 49, 50, 52, 53, 54, 56, 57, 58, 60, 61, 62 };
    // private StringBuilder metaInfo=new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc3);
        mContext = this;
        checkNFCFunction(); // NFC Check
        init();

        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        // Setup an intent filter for all MIME based dispatches
        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        try {
            ndef.addDataType("*/*");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
        mFilters = new IntentFilter[] { ndef, };

        // 根据标签类型设置
        mTechLists = new String[][] { new String[] { NfcA.class.getName() } };
    }

    private void checkNFCFunction() {
        // TODO Auto-generated method stub
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        // check the NFC adapter first
        if (mNfcAdapter == null) {
            // mTextView.setText("NFC apdater is not available");

            Tip.getDialogOne(NFC3Activity.this, "没发现NFC设备，请确认您的设备支持NFC功能!", new Tip.OnClickSureListener() {
                @Override
                public void onClickSure() {
//                    Intent setnfc = new Intent(
//                            Settings.ACTION_NFC_SETTINGS);
//                    startActivity(setnfc);
                }
            });
//            Dialog dialog = null;
//            CustomDialog.Builder customBuilder = new CustomDialog.Builder(
//                    mContext);
//            customBuilder.setTitle("很遗憾")
//                    .setMessage("没发现NFC设备，请确认您的设备支持NFC功能!")
//                    .setPositiveButton("是",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog,
//                                                    int which) {
//                                    dialog.dismiss();
//                                    finish();
//                                }
//                            });
//            dialog = customBuilder.create();
//            dialog.setCancelable(false);// back
//            dialog.setCanceledOnTouchOutside(false);
//            SetDialogWidth(dialog).show();
            return;
        } else {
            if (!mNfcAdapter.isEnabled()) {
                Tip.getDialogOne(NFC3Activity.this, "请确认NFC功能是否开启!", new Tip.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        Intent setnfc = new Intent(
                                Settings.ACTION_NFC_SETTINGS);
                        startActivity(setnfc);
                    }
                });
//                Dialog dialog = null;
//                CustomDialog.Builder customBuilder = new CustomDialog.Builder(
//                        mContext);
//                customBuilder
//                        .setTitle("提示")
//                        .setMessage("请确认NFC功能是否开启!")
//                        .setIcon(R.drawable.dialog_icon2)
//                        .setPositiveButton("现在去开启......",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog,
//                                                        int which) {
//                                        dialog.dismiss();
//                                        Intent setnfc = new Intent(
//                                                Settings.ACTION_NFC_SETTINGS);
//                                        startActivity(setnfc);
//                                    }
//                                });
//                dialog = customBuilder.create();
//                dialog.setCancelable(false);// back
//                dialog.setCanceledOnTouchOutside(false);
//                SetDialogWidth(dialog).show();
                return;
            }
        }
    }

    private Dialog SetDialogWidth(Dialog dialog) {
        // TODO 自动生成的方法存根
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (screenWidth > screenHeight) {
            params.width = (int) (((float) screenHeight) * 0.875);

        } else {
            params.width = (int) (((float) screenWidth) * 0.875);
        }
        dialog.getWindow().setAttributes(params);

        return dialog;
    }

    private void init() {
        // TODO 自动生成的方法存根
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        etSector = (EditText) findViewById(R.id.etSector);
        etBlock = (EditText) findViewById(R.id.etBlock);
        etData = (EditText) findViewById(R.id.etData);

        mRead = (RadioButton) findViewById(R.id.rb_read);
        mWriteData = (RadioButton) findViewById(R.id.rb_write);
        mChange = (RadioButton) findViewById(R.id.rb_Change);
    }

    @Override
    protected void onResume() {
        // TODO 自动生成的方法存根
        super.onResume();
        enableForegroundDispatch();
        // mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters,
        // mTechLists);
    }

    private void enableForegroundDispatch() {
        // TODO 自动生成的方法存根
        if (mNfcAdapter != null) {
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent,
                    mFilters, mTechLists);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // TODO 自动生成的方法存根
        super.onNewIntent(intent);
        tv1.setText("发现新的 Tag:  " + ++mCount + "\n");// mCount 计数
        String intentActionStr = intent.getAction();// 获取到本次启动的action
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intentActionStr)// NDEF类型
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(intentActionStr)// 其他类型
                || NfcAdapter.ACTION_TAG_DISCOVERED.equals(intentActionStr)) {// 未知类型

            // 在intent中读取Tag id
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] bytesId = tag.getId();// 获取id数组

            info += ByteArrayChange.ByteArrayToHexString(bytesId) + "\n";
            tv2.setText("标签UID:  " + "\n" + info);

            // 读取存储信息
            if (mRead.isChecked()) {
                // mChange=false;
                tv3.setText("读取成功! " + readTag(tag));
                // readNfcVTag(tag);
                etSector.setText("");
                etBlock.setText("");
                etData.setText("");
            }

            // 写数据
            if (mWriteData.isChecked()) {
                //writeTag(tag);
                String str= etData.getText().toString();
                writeTag(tag,str);
            }

            // 转换为ASCll
            if (mChange.isChecked()) {
                tv3.setText(change(tag));
                ToastUtil.show("转换成功");
                etSector.setText("");
                etBlock.setText("");
                etData.setText("");
            }
        }
    }

    // 写数据
    public void writeTag(Tag tag, String str) {
        MifareClassic mfc = MifareClassic.get(tag);

        try {
            if (mfc != null) {
                mfc.connect();
            } else {
                ToastUtil.show("写入失败");
                return;
            }
            Log.i("write", "----connect-------------");
            boolean CodeAuth = false;
            byte[] b1 = str.getBytes();
            if (b1.length <= 720) {
                //System.out.println("------b1.length:" + b1.length);
                int num = b1.length / 16;
                System.out.println("num= " + num);
                int next = b1.length / 48 + 1;
                System.out.println("扇区next的值为" + next);
                b0 = new byte[16];
                if (!(b1.length % 16 == 0)) {
                    for (int i = 1, j = 1; i <= num; i++) {
                        CodeAuth = mfc.authenticateSectorWithKeyA(j, code);
                        System.arraycopy(b1, 16 * (i - 1), b0, 0, 16);
                        mfc.writeBlock(block[i - 1], b0);
                        if (i % 3 == 0) {
                            j++;
                        }
                    }
                    //Log.d("下一个模块", "测试");
                    CodeAuth = mfc.authenticateSectorWithKeyA(next,// 非常重要------
                            code);
                    //Log.d("获取第5块的密码", "---成功-------");
                    byte[] b2 = { 0 };
                    b0 = new byte[16];
                    System.arraycopy(b1, 16 * num, b0, 0, b1.length % 16);
                    System.arraycopy(b2, 0, b0, b1.length % 16, b2.length);
                    mfc.writeBlock(block[num], b0);
                    mfc.close();
                    Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    for (int i = 1, j = 1; i <= num; i++) {
                        if (i % 3 == 0) {
                            j++;
                            System.out.println("扇区j的值为：" + j);
                        }
                        CodeAuth = mfc.authenticateSectorWithKeyA(j,// 非常重要---------
                                code);
                        System.arraycopy(b1, 16 * (i - 1), b0, 0, 16);
                        mfc.writeBlock(block[i - 1], b0);
                        str += ByteArrayChange.ByteArrayToHexString(b0);
                        System.out.println("Block" + i + ": " + str);
                    }
                    mfc.close();
                    ToastUtil.show("写入成功");
                    return;
                }
            } else {
                ToastUtil.show("字符过长，内存不足");
                return;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                mfc.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // //读取数据
    public String readTag(Tag tag) {
        MifareClassic mfc = MifareClassic.get(tag);
        for (String tech : tag.getTechList()) {
            System.out.println(tech);// 显示设备支持技术
        }
        boolean auth = false;
        // 读取TAG

        try {
            // metaInfo.delete(0, metaInfo.length());//清空StringBuilder;
            StringBuilder metaInfo = new StringBuilder();
            // Enable I/O operations to the tag from this TagTechnology object.
            mfc.connect();
            int type = mfc.getType();// 获取TAG的类型
            int sectorCount = mfc.getSectorCount();// 获取TAG中包含的扇区数
            String typeS = "";
            switch (type) {
                case MifareClassic.TYPE_CLASSIC:
                    typeS = "TYPE_CLASSIC";
                    break;
                case MifareClassic.TYPE_PLUS:
                    typeS = "TYPE_PLUS";
                    break;
                case MifareClassic.TYPE_PRO:
                    typeS = "TYPE_PRO";
                    break;
                case MifareClassic.TYPE_UNKNOWN:
                    typeS = "TYPE_UNKNOWN";
                    break;

            }
            metaInfo.append("  卡片类型：" + typeS + "\n共" + sectorCount + "个扇区\n共"
                    + mfc.getBlockCount() + "个块\n存储空间: " + mfc.getSize()
                    + "B\n");
            for (int j = 0; j < sectorCount; j++) {
                // Authenticate a sector with key A.
                auth = mfc.authenticateSectorWithKeyA(j,
                        MifareClassic.KEY_NFC_FORUM);// 逐个获取密码
                /*
                 * byte[]
                 * codeByte_Default=MifareClassic.KEY_DEFAULT;//FFFFFFFFFFFF
                 * byte[]
                 * codeByte_Directory=MifareClassic.KEY_MIFARE_APPLICATION_DIRECTORY
                 * ;//A0A1A2A3A4A5 byte[]
                 * codeByte_Forum=MifareClassic.KEY_NFC_FORUM;//D3F7D3F7D3F7
                 */if (auth) {
                    metaInfo.append("Sector " + j + ":验证成功\n");
                    // 读取扇区中的块
                    bCount = mfc.getBlockCountInSector(j);
                    bIndex = mfc.sectorToBlock(j);
                    for (int i = 0; i < bCount; i++) {
                        byte[] data = mfc.readBlock(bIndex);
                        metaInfo.append("Block " + bIndex + " : "
                                + ByteArrayChange.ByteArrayToHexString(data)
                                + "\n");
                        bIndex++;
                    }
                } else {
                    metaInfo.append("Sector " + j + ":验证失败\n");
                }
            }
            return metaInfo.toString();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (mfc != null) {
                try {
                    mfc.close();
                } catch (IOException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG)
                            .show();
                }
            }
        }
        return null;

    }

    // 转换Hex为字符串
    public String change(Tag tag) {
        MifareClassic mfc = MifareClassic.get(tag);
        Log.d("----------", "change----------");
        boolean auth = false;
        // 读取TAG

        String ChangeInfo = "";
        String Ascll = "";
        // Enable I/O operations to the tag from this TagTechnology object.
        try {
            mfc.connect();
            int sectorCount = mfc.getSectorCount();// 获取TAG中包含的扇区数
            for (int j = 1; j < sectorCount; j++) {
                // Authenticate a sector with key A.
                auth = mfc.authenticateSectorWithKeyA(j,
                        MifareClassic.KEY_NFC_FORUM);
                if (auth) {
                    Log.i("change 的auth验证成功", "开始读取模块信息");

                    byte[] data0 = mfc.readBlock(4 * j);
                    byte[] data1 = mfc.readBlock(4 * j + 1);
                    byte[] data2 = mfc.readBlock(4 * j + 2);
                    data3 = new byte[data0.length + data1.length + data2.length];
                    System.arraycopy(data0, 0, data3, 0, data0.length);
                    System.arraycopy(data1, 0, data3, data0.length,
                            data1.length);
                    System.arraycopy(data2, 0, data3, data0.length
                            + data1.length, data2.length);

                    ChangeInfo = ByteArrayChange.ByteArrayToHexString(data3);
                    temp = "扇区" + (j) + "里的内容为："
                            + ToStringHex.decode(ChangeInfo) + "\n";
                }
                Ascll += temp;
            }
            return Ascll;
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            ToastUtil.show("转换失败");
        } finally {
            if (mfc != null) {
                try {
                    mfc.close();
                } catch (IOException e) {
                    ToastUtil.show(e.getMessage());
                }
            }
        }
        return "";
    }

    public int StringToInt(String s) {
        if (!(TextUtils.isEmpty(s)) || s.length() > 0) {
            BlockData = Integer.parseInt(s);
        } else {
            ToastUtil.show("Block输入有误");
        }
        System.out.println(BlockData);
        return BlockData;
    }

    @Override
    public void onPause() {
        super.onPause();
        disableForegroundDispatch();
    }

    private void disableForegroundDispatch() {
        // TODO 自动生成的方法存根
        if (mNfcAdapter != null) {
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }
}
