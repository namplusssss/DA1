package com.example.xs_sport.Model;

public class BinhLuan {
    int comment_id;
    int ma_giay;
    int xephang;
    String user_name,comment_content;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getMa_giay() {
        return ma_giay;
    }

    public void setMa_giay(int ma_giay) {
        this.ma_giay = ma_giay;
    }

    public int getXephang() {
        return xephang;
    }

    public void setXephang(int xephang) {
        this.xephang = xephang;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }
}
