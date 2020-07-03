package com.truck.photo;

import android.graphics.drawable.Drawable;

public class ClearCacheBean {
    private String appname;
    private String packagename;
    private Drawable appicon;
    private long size;

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public Drawable getAppicon() {
        return appicon;
    }

    public void setAppicon(Drawable appicon) {
        this.appicon = appicon;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public ClearCacheBean(String appname, String packagename, Drawable appicon, long size) {
        this.appname = appname;
        this.packagename = packagename;
        this.appicon = appicon;
        this.size = size;
    }
}
