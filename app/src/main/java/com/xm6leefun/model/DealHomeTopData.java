package com.xm6leefun.model;

import java.util.List;

/**
 * 创建人：zll .
 * 创建时间：2018/5/11 0011.
 * 作用：首页头部文本数据
 */

public class DealHomeTopData {
    /**
     * status : ok
     * code : 200
     * record : {"companyList":[{"companyId":"1","companyName":"五粮液"},{"companyId":"2","companyName":"泸州老窖"},{"companyId":"3","companyName":"茅台"},{"companyId":"4","companyName":"西凤酒"},{"companyId":"5","companyName":"剑南春"}]}
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
        private List<CompanyListBean> companyList;

        public List<CompanyListBean> getCompanyList() {
            return companyList;
        }

        public void setCompanyList(List<CompanyListBean> companyList) {
            this.companyList = companyList;
        }

        public static class CompanyListBean {
            /**
             * companyId : 1
             * companyName : 五粮液
             */

            private String companyId;
            private String companyName;

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }
        }
    }
}
