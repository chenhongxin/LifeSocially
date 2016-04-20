package com.life.lifesocially.model;

/**
 * Created by chenhongxinxiangya on 2016/3/28.
 */
public class User {
    // 定位
    private String lat;
    private String lng;
    private String address;
    private boolean isLocation;


    public User setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLat() {
        return lat;
    }

    public User setLng(String lng) {
        this.lng = lng;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public boolean isLocation() {
        return isLocation;
    }

    public User setIsLocation(boolean isLocation) {
        this.isLocation = isLocation;
        return this;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAddress() {
        return address;
    }
}
