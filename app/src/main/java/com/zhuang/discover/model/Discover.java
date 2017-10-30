package com.zhuang.discover.model;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;

import com.zhuang.discover.myview.TextClickableSpan;

import java.util.Date;

/**
 * Created by zhuang on 2017/10/26.
 */

public class Discover {
    private int id;
    private String describe;
    private String imageUrl;
    private String merchant;//商家
    private String user;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public SpannableString getDescribeSpannableString() {
        String s = user + ": " + describe;
        SpannableString spannableString = new SpannableString(s);
        spannableString.setSpan(new TextClickableSpan(), 0, user.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
