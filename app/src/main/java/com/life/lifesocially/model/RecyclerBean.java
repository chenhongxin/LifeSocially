package com.life.lifesocially.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhongxinxiangya on 2016/3/29.
 */
public class RecyclerBean {

    private String id;
    private String title;
    private String content;
    private boolean one;
    private boolean two;
    private int position;
    private String description;
    private String imageUrl;
    private String startTime;
    private String endTime;
    private List<RecyclerBean> recyclerBeans = new ArrayList<RecyclerBean>();

    public String getTitle() {
        return title;
    }

    public RecyclerBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public RecyclerBean setContent(String content) {
        this.content = content;
        return this;
    }

    public boolean isOne() {
        return one;
    }

    public RecyclerBean setOne(boolean one) {
        this.one = one;
        return this;
    }

    public boolean isTwo() {
        return two;
    }

    public RecyclerBean setTwo(boolean two) {
        this.two = two;
        return this;
    }

    public int getPosition() {
        return position;
    }

    public RecyclerBean setPosition(int position) {
        this.position = position;
        return this;
    }


    public String getId() {
        return id;
    }

    public RecyclerBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecyclerBean setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public RecyclerBean setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public RecyclerBean setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public RecyclerBean setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public RecyclerBean setRecyclerBeans(List<RecyclerBean> recyclerBeans) {
        this.recyclerBeans.addAll(recyclerBeans);
        return this;
    }

    public List<RecyclerBean> getRecyclerBeans() {
        return recyclerBeans;
    }
}