package com.xm6leefun.model;

/**
 * Created by Administrator on 2018/3/7 0007.
 */

public class DataUpVersion {
    /**
     * code : 200
     * data : {"edition":{"createTime":1513156826000,"createTimeStr":"2017-12-13 17:20:26","id":1,"isDel":0,"isForce":0,"name":"安卓","num":"1","remark":"安卓","type":"android","updateTime":1513156826000,"updateTimeStr":"2017-12-13 17:20:26","url":"http://xm6leefun.oss-cn-shanghai.aliyuncs.com/xm6leefun/file/app-debug.apk"}}
     * msg : OK
     */

    private int code;
    private RecordBean record;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class RecordBean {
        /**
         * edition : {"createTime":1513156826000,"createTimeStr":"2017-12-13 17:20:26","id":1,"isDel":0,"isForce":0,"name":"安卓","num":"1","remark":"安卓","type":"android","updateTime":1513156826000,"updateTimeStr":"2017-12-13 17:20:26","url":"http://xm6leefun.oss-cn-shanghai.aliyuncs.com/xm6leefun/file/app-debug.apk"}
         */

        private EditionBean edition;

        public EditionBean getEdition() {
            return edition;
        }

        public void setEdition(EditionBean edition) {
            this.edition = edition;
        }

        public static class EditionBean {
            /**
             * createTime : 1513156826000
             * createTimeStr : 2017-12-13 17:20:26
             * id : 1
             * isDel : 0
             * isForce : 0
             * name : 安卓
             * num : 1
             * remark : 安卓
             * type : android
             * updateTime : 1513156826000
             * updateTimeStr : 2017-12-13 17:20:26
             * url : http://xm6leefun.oss-cn-shanghai.aliyuncs.com/xm6leefun/file/app-debug.apk
             */

            private long createTime;
            private String createTimeStr;
            private String id;
            private String isDel;
            private String isForce;
            private String name;
            private int num;
            private String remark;
            private String type;
            private long updateTime;
            private String updateTimeStr;
            private String url;

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getCreateTimeStr() {
                return createTimeStr;
            }

            public void setCreateTimeStr(String createTimeStr) {
                this.createTimeStr = createTimeStr;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIsDel() {
                return isDel;
            }

            public void setIsDel(String isDel) {
                this.isDel = isDel;
            }

            public String getIsForce() {
                return isForce;
            }

            public void setIsForce(String isForce) {
                this.isForce = isForce;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getUpdateTimeStr() {
                return updateTimeStr;
            }

            public void setUpdateTimeStr(String updateTimeStr) {
                this.updateTimeStr = updateTimeStr;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
