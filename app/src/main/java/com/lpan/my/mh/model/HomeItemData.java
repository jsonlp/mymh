package com.lpan.my.mh.model;

/**
 * Created by lpan on 2019/4/11.
 */
public class HomeItemData {
    public static final String CALCULATE_TILI_TAG = "calculate_tili";


    private String tag;

    private String title;

    public HomeItemData(String tag, String title) {
        this.tag = tag;
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
