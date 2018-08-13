package com.xm6leefun.zlldeal.about_retrofit.model_request;

/**
 * Created by Administrator on 2018/2/24.
 */

public class RequestData {

    public String mobile;
    public String pwd;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public RequestData(String mobile, String pwd) {
        this.mobile = mobile;
        this.pwd = pwd;
    }
    public RequestData() {
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "mobile='" + mobile + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
