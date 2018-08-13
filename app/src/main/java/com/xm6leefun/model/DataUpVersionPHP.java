package com.xm6leefun.model;

/**
 * Created by Administrator on 2018/1/24 0024.
 */

public class DataUpVersionPHP {
    /**
     * status : ok
     * code : 200
     * record : {"is_update":true,"newsVs":null,"down_url":"","forced_update":1}
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
         * is_update : true
         * newsVs : null
         * down_url :
         * forced_update : 1
         */

        private String is_update;
        private String newsVs;
        private String down_url;
        private String forced_update;
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getIs_update() {
            return is_update;
        }

        public void setIs_update(String is_update) {
            this.is_update = is_update;
        }

        public String getNewsVs() {
            return newsVs;
        }

        public void setNewsVs(String newsVs) {
            this.newsVs = newsVs;
        }

        public String getDown_url() {
            return down_url;
        }

        public void setDown_url(String down_url) {
            this.down_url = down_url;
        }

        public String getForced_update() {
            return forced_update;
        }

        public void setForced_update(String forced_update) {
            this.forced_update = forced_update;
        }
    }
}
