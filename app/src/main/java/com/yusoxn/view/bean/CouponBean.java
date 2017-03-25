package com.yusoxn.view.bean;

/**
 * Created by Yusxon on 17/3/25.
 */

public class CouponBean {
    public int logo;
    public String description;
    public String money;
    public String startTime;
    public String endTime;
    public boolean used;

    public CouponBean(int logo, String description, String money, String startTime, String endTime, boolean used) {
        this.logo = logo;
        this.description = description;
        this.money = money;
        this.startTime = startTime;
        this.endTime = endTime;
        this.used = used;
    }
}
