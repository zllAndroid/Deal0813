package com.xm6leefun.zlldeal.about_retrofit_model;

/**
 * Created by Administrator on 2017/9/14 0014.
 */

public class DataLogins {
//    /**
//     * status : ok
//     * code : 200
//     * record : {"mobile":"13860169273","user_type":"1003","user_token":"6E1093F1-011D-4E9A-82E0-D089130C2EFD","is_status":"1","head_url":"default/avatar/default.png","isset_password":1}
//     */
//
//    private String status;
//    private int code;
//    private RecordBean record;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public RecordBean getRecord() {
//        return record;
//    }
//
//    public void setRecord(RecordBean record) {
//        this.record = record;
//    }
//
//    public static class RecordBean {
//        /**
//         * mobile : 13860169273
//         * user_type : 1003
//         * user_token : 6E1093F1-011D-4E9A-82E0-D089130C2EFD
//         * is_status : 1
//         * head_url : default/avatar/default.png
//         * isset_password : 1 //1是已设置交易密码，0未设置交易密码
//         * is_activation : 1//黑卡是否已被激活使用(1是已经激活使用  2是未激活使用)
//         */
//
//        private String mobile;
//        private String user_type;
//        private String user_token;
//        private String is_status;
//        private String head_url;
//        private String isset_password;
//        private String is_activation;
//
//        public String getIs_activation() {
//            return is_activation;
//        }
//
//        public void setIs_activation(String is_activation) {
//            this.is_activation = is_activation;
//        }
//        public String getMobile() {
//            return mobile;
//        }
//
//        public void setMobile(String mobile) {
//            this.mobile = mobile;
//        }
//
//        public String getUser_type() {
//            return user_type;
//        }
//
//        public void setUser_type(String user_type) {
//            this.user_type = user_type;
//        }
//
//        public String getUser_token() {
//            return user_token;
//        }
//
//        public void setUser_token(String user_token) {
//            this.user_token = user_token;
//        }
//
//        public String getIs_status() {
//            return is_status;
//        }
//
//        public void setIs_status(String is_status) {
//            this.is_status = is_status;
//        }
//
//        public String getHead_url() {
//            return head_url;
//        }
//
//        public void setHead_url(String head_url) {
//            this.head_url = head_url;
//        }
//
//        public String getIsset_password() {
//            return isset_password;
//        }
//
//        public void setIsset_password(String isset_password) {
//            this.isset_password = isset_password;
//        }
//    }
    /**
     * success : 1
     * user : {"email":"","headPortrait":"","id":6,"isValid":0,"manor":0,"mobile":"17605086378","nickName":"哈哈","pwd":"","roleId":1,"site":"","token":"d0e1a7ba5e203507a2cf3c936835cf6d","tokenId":831,"wechat":""}
     */

    private String success;
    private UserBean user;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * email :
         * headPortrait :
         * id : 6
         * isValid : 0
         * manor : 0
         * mobile : 17605086378
         * nickName : 哈哈
         * pwd :
         * roleId : 1
         * site :
         * token : d0e1a7ba5e203507a2cf3c936835cf6d
         * tokenId : 831
         * wechat :
         */

        private String email;
        private String headPortrait;
        private String id;
        private int isValid;
        private int manor;
        private String mobile;
        private String nickName;
        private String pwd;
        private int roleId;
        private String site;
        private String token;
        private String tokenId;
        private String wechat;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIsValid() {
            return isValid;
        }

        public void setIsValid(int isValid) {
            this.isValid = isValid;
        }

        public int getManor() {
            return manor;
        }

        public void setManor(int manor) {
            this.manor = manor;
        }

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

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getTokenId() {
            return tokenId;
        }

        public void setTokenId(String tokenId) {
            this.tokenId = tokenId;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }
    }
}
