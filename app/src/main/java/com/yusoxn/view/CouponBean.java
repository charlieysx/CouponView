package com.yusoxn.view;

/**
 * Created by Yusxon on 17/3/25.
 */

public class CouponBean {
    int logo;
    String description;
    String money;
    String startTime;
    String endTime;
    boolean used;

    public CouponBean(int logo, String description, String money, String startTime, String endTime, boolean used) {
        this.logo = logo;
        this.description = description;
        this.money = money;
        this.startTime = startTime;
        this.endTime = endTime;
        this.used = used;
    }
}
