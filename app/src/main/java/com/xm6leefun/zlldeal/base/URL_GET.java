package com.xm6leefun.zlldeal.base;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/6 0006.
 */

public class URL_GET {
    public static String USER_TOKEN= "";
    public static String USER_HEAD= "";
    public static String USER_NAME= "";
    public static String USER_PHONE= "";
    public static String USER_IS_PSW= "";
    public static ArrayList<String> mList=new ArrayList<String>();
    public static String URL = "http://192.168.4.55:40001/wwwroot/app/index.php?api_act=";
//    public static String URL = "http://192.168.1.21:8080";

//    登录
    public static String userLogin(String mobile,String password){
        mList.clear();
        mList.add("mobile="+mobile);
        mList.add("password="+password);
        return URL+"userLogin"+ SignForXm6leefunPHP.getSing(mList);
    }
    public static String appUpdate(){
        mList.clear();
        return URL+"appUpdate"+SignForXm6leefunPHP.getSing(mList);
    }
//    首页头
    public static String companyList(){
        mList.clear();
        return URL+"companyList"+SignForXm6leefunPHP.getSing(mList);
    }
    public static String ticketList(String ticketType,String page,String companyId,String nowPrice,String amplitudeType){
        mList.clear();
        mList.add("ticketType="+ticketType);
        mList.add("page="+page);
        mList.add("companyId="+companyId);
        mList.add("nowPrice="+nowPrice);
        mList.add("amplitudeType="+amplitudeType);
        return URL+"ticketList"+SignForXm6leefunPHP.getSing(mList);
    }
//    	短信类型：1 支付 2提现 3修改支付密码 4修改登录密码 5注册 6更改手机号 7手机短信登入 8忘记密码
    public static String smsCode(String mobile,String type){
        mList.clear();
        mList.add("mobile="+mobile);
        mList.add("type="+type);
        return URL+"smsCode"+ SignForXm6leefunPHP.getSing(mList);
    }
//    验证码登录
    public static String userSmsLogin(String mobile,String vcode){
        mList.clear();
        mList.add("mobile="+mobile);
        mList.add("vcode="+vcode);
        return URL+"userSmsLogin"+ SignForXm6leefunPHP.getSing(mList);
    }
//    忘记登录密码接口
    public static String forgetLoginPassword(String mobile,String password,String vcode){
        mList.clear();
        mList.add("mobile="+mobile);
        mList.add("password="+password);
        mList.add("vcode="+vcode);
        return URL+"forgetLoginPassword"+ SignForXm6leefunPHP.getSing(mList);
    }
    public static String feedBack(String content,String mobile){
        mList.clear();
        mList.add("content="+content);
        mList.add("mobile="+mobile);
        return URL+"feedBack"+ SignForXm6leefunPHP.getSing(mList);
    }


    public static String getWaitShipTask(String scId,String pageNo){
        mList.clear();
        mList.add("scId="+scId);
        mList.add("pageNo="+pageNo);
        return URL+"/api/task/getWaitShipTask?"+ SignForXm6leefunJava.getSing(mList);
    }
    public static String getExpressList(String scId){
        mList.clear();
        mList.add("scId="+scId);
        return URL+"/api/task/getExpressList?"+ SignForXm6leefunJava.getSing(mList);
    }
    public static String getEdition(){
        mList.clear();
        mList.add("type="+"android");
        return URL+"/api/shippingClerk/getEdition?"+ SignForXm6leefunJava.getSing(mList);
    }
    public static String getWaitShipTaskData(String scId,String taskId){
        mList.clear();
        mList.add("scId="+scId);
        mList.add("taskId="+taskId);
        return URL+"/api/task/getWaitShipTaskData?"+ SignForXm6leefunJava.getSing(mList);
    }
//    校验qrCode接口
    public static String checkQrCode(String scId,String qrCode){
        mList.clear();
        mList.add("scId="+scId);
        mList.add("qrCode="+qrCode);
        return URL+"/api/task/checkQrCode?"+ SignForXm6leefunJava.getSing(mList);
    }
//    校验nfc接口
    public static String checkNfcUid(String scId,String nfcUid){
        mList.clear();
        mList.add("scId="+scId);
        mList.add("nfcUid="+nfcUid);
        return URL+"/api/task/checkNfcUid?"+ SignForXm6leefunJava.getSing(mList);
    }
//    历史记录接口
    public static String getHistoryTask(String scId,String pageNo){
        mList.clear();
        mList.add("scId="+scId);
        mList.add("pageNo="+pageNo);
        return URL+"/api/task/getHistoryTask?"+ SignForXm6leefunJava.getSing(mList);
    }
    public static String getHistoryTaskDetails(String scId,String historyDate,String pageNo){
        mList.clear();
        mList.add("scId="+scId);
        mList.add("historyDate="+historyDate);
        mList.add("pageNo="+pageNo);
        return URL+"/api/task/getHistoryTaskDetails?"+ SignForXm6leefunJava.getSing(mList);
    }
//    历史任务详情
    public static String getHistoryTaskData(String scId,String taskId){
        mList.clear();
        mList.add("scId="+scId);
        mList.add("taskId="+taskId);
        return URL+"/api/task/getHistoryTaskData?"+ SignForXm6leefunJava.getSing(mList);
    }
//    短信接口
    public static String getNoteCode(String mobile,String type){
        mList.clear();
        mList.add("mobile="+mobile);
        mList.add("type="+type);
        return URL+"/api/noteCode/getNoteCode?"+ SignForXm6leefunJava.getSing(mList);
    }
//    修改密码接口
    public static String resetPwd(String mobile,String pwd,String code){
        mList.clear();
        mList.add("mobile="+mobile);
        mList.add("pwd="+pwd);
        mList.add("code="+code);
        return URL+"/api/shippingClerk/resetPwd?"+ SignForXm6leefunJava.getSing(mList);
    }

}
