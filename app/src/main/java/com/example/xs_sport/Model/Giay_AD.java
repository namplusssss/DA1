package com.example.xs_sport.Model;

public class Giay_AD {
    private int ma_giay;
    private String loaiGiay_tenGiay;
    private String anh_giay;
    private String ten_giay;
    private String mota_giay;
    private int gia_giay;
    private String size;

    public Giay_AD() {
    }

    public Giay_AD(int ma_giay, String loaiGiay_tenGiay, String anh_giay, String ten_giay, String mota_giay, int gia_giay, String size) {
        this.ma_giay = ma_giay;
        this.loaiGiay_tenGiay = loaiGiay_tenGiay;
        this.anh_giay = anh_giay;
        this.ten_giay = ten_giay;
        this.mota_giay = mota_giay;
        this.gia_giay = gia_giay;
        this.size = size;
    }

    public int getMa_giay() {
        return ma_giay;
    }

    public void setMa_giay(int ma_giay) {
        this.ma_giay = ma_giay;
    }

    public String getLoaiGiay_tenGiay() {
        return loaiGiay_tenGiay;
    }

    public void setLoaiGiay_tenGiay(String loaiGiay_tenGiay) {
        this.loaiGiay_tenGiay = loaiGiay_tenGiay;
    }

    public String getAnh_giay() {
        return anh_giay;
    }

    public void setAnh_giay(String anh_giay) {
        this.anh_giay = anh_giay;
    }

    public String getTen_giay() {
        return ten_giay;
    }

    public void setTen_giay(String ten_giay) {
        this.ten_giay = ten_giay;
    }

    public String getMota_giay() {
        return mota_giay;
    }

    public void setMota_giay(String mota_giay) {
        this.mota_giay = mota_giay;
    }

    public int getGia_giay() {
        return gia_giay;
    }

    public void setGia_giay(int gia_giay) {
        this.gia_giay = gia_giay;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
