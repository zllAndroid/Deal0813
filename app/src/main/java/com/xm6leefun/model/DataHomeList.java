package com.xm6leefun.model;

import java.util.List;

/**
 * Created by Administrator on 2018/6/20 0020.
 */

public class DataHomeList {
    /**
     * status : ok
     * code : 200
     * record : {"ticketList":[{"stockId":"1","stockCode":"1845454","stockName":"茅台集团茅坛封藏500ml白酒","periodNum":"1","presellPrice":"998.00","retailPrice":"2999.00","stockPrice":"998.00","stockApplies":"10.00","stockStatus":"1","stockImg":"http://beybow.oss-cn-shenzhen.aliyuncs.com/http://img.aihl.ren/wawu/images/c44b8efc-6721-4c9c-9aaa-3d2508aad02c.png","companyId":"3"},{"stockId":"3","stockCode":"1845456","stockName":"茅台集团茅坛封藏500ml白酒","periodNum":"3","presellPrice":"1000.00","retailPrice":"2999.00","stockPrice":"990.00","stockApplies":"15.00","stockStatus":"1","stockImg":"http://beybow.oss-cn-shenzhen.aliyuncs.com/http://img.aihl.ren/wawu/images/c44b8efc-6721-4c9c-9aaa-3d2508aad02c.png","companyId":"3"}],"counts":2,"pages":1,"page":1}
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
         * ticketList : [{"stockId":"1","stockCode":"1845454","stockName":"茅台集团茅坛封藏500ml白酒","periodNum":"1","presellPrice":"998.00","retailPrice":"2999.00","stockPrice":"998.00","stockApplies":"10.00","stockStatus":"1","stockImg":"http://beybow.oss-cn-shenzhen.aliyuncs.com/http://img.aihl.ren/wawu/images/c44b8efc-6721-4c9c-9aaa-3d2508aad02c.png","companyId":"3"},{"stockId":"3","stockCode":"1845456","stockName":"茅台集团茅坛封藏500ml白酒","periodNum":"3","presellPrice":"1000.00","retailPrice":"2999.00","stockPrice":"990.00","stockApplies":"15.00","stockStatus":"1","stockImg":"http://beybow.oss-cn-shenzhen.aliyuncs.com/http://img.aihl.ren/wawu/images/c44b8efc-6721-4c9c-9aaa-3d2508aad02c.png","companyId":"3"}]
         * counts : 2
         * pages : 1
         * page : 1
         */

        private int counts;
        private int pages;
        private int page;
        private List<TicketListBean> ticketList;

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<TicketListBean> getTicketList() {
            return ticketList;
        }

        public void setTicketList(List<TicketListBean> ticketList) {
            this.ticketList = ticketList;
        }

        public static class TicketListBean {
            /**
             * stockId : 1
             * stockCode : 1845454
             * stockName : 茅台集团茅坛封藏500ml白酒
             * periodNum : 1
             * presellPrice : 998.00
             * retailPrice : 2999.00
             * stockPrice : 998.00
             * stockApplies : 10.00
             * stockStatus : 1
             * stockImg : http://beybow.oss-cn-shenzhen.aliyuncs.com/http://img.aihl.ren/wawu/images/c44b8efc-6721-4c9c-9aaa-3d2508aad02c.png
             * companyId : 3
             */

            private String stockId;
            private String stockCode;
            private String stockName;
            private String periodNum;
            private String presellPrice;
            private String retailPrice;
            private String stockPrice;
            private String stockApplies;
            private String stockStatus;
            private String stockImg;
            private String companyId;

            public String getStockId() {
                return stockId;
            }

            public void setStockId(String stockId) {
                this.stockId = stockId;
            }

            public String getStockCode() {
                return stockCode;
            }

            public void setStockCode(String stockCode) {
                this.stockCode = stockCode;
            }

            public String getStockName() {
                return stockName;
            }

            public void setStockName(String stockName) {
                this.stockName = stockName;
            }

            public String getPeriodNum() {
                return periodNum;
            }

            public void setPeriodNum(String periodNum) {
                this.periodNum = periodNum;
            }

            public String getPresellPrice() {
                return presellPrice;
            }

            public void setPresellPrice(String presellPrice) {
                this.presellPrice = presellPrice;
            }

            public String getRetailPrice() {
                return retailPrice;
            }

            public void setRetailPrice(String retailPrice) {
                this.retailPrice = retailPrice;
            }

            public String getStockPrice() {
                return stockPrice;
            }

            public void setStockPrice(String stockPrice) {
                this.stockPrice = stockPrice;
            }

            public String getStockApplies() {
                return stockApplies;
            }

            public void setStockApplies(String stockApplies) {
                this.stockApplies = stockApplies;
            }

            public String getStockStatus() {
                return stockStatus;
            }

            public void setStockStatus(String stockStatus) {
                this.stockStatus = stockStatus;
            }

            public String getStockImg() {
                return stockImg;
            }

            public void setStockImg(String stockImg) {
                this.stockImg = stockImg;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }
        }
    }
}
