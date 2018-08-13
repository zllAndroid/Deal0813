package com.xm6leefun.model;

/**
 * Created by Administrator on 2018/2/8 0008.
 */

public class DataLogin {
    /**
     * status : ok
     * code : 200
     * record : {"mobile":"18150960007","nickName":"emmmm","token":"12638282-3C98-46BD-87FA-248034FEE09D","headUrl":"article/152099831084605.jpg","issetPassword":1}
     */

    private String status;
    private int code;
    private RecordBean record;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public static class RecordBean {
        /**
         * mobile : 18150960007
         * nickName : emmmm
         * token : 12638282-3C98-46BD-87FA-248034FEE09D
         * headUrl : article/152099831084605.jpg
         * issetPassword : 1
         */

        private String mobile;
        private String nickName;
        private String token;
        private String headUrl;
        private String issetPassword;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getIssetPassword() {
            return issetPassword;
        }

        public void setIssetPassword(String issetPassword) {
            this.issetPassword = issetPassword;
        }
    }
}
