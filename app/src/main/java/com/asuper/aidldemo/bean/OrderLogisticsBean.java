package com.asuper.aidldemo.bean;

import java.util.List;

/**
 * @author super
 * @date 11/17/20
 */
public class OrderLogisticsBean {
    /**
     * TagCode :
     * TagMsg :
     * expressCompany :
     * location :
     * logisticCode :
     * orderCode :
     * orderState : 0
     * reason :
     * shipperCode :
     * state : 0
     * stateEx : 0
     * traces : [{"acceptStation":"","acceptTime":"","action":"","location":"","remark":""}]
     */
    public String TagMsg;
    public String expressCompany;
    public String location;
    public String logisticCode;
    public String orderCode;
    public int orderState;
    public String reason;
    public String shipperCode;
    public int state;
    public int stateEx;
    public List<TracesBean> traces;

    public static class TracesBean {
        /**
         * acceptStation :
         * acceptTime :
         * action :
         * location :
         * remark :
         */

        public String acceptStation;
        public String acceptTime;
        public String action;
        public String location;
        public String remark;
    }
}
