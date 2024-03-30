package com.example.xs_sport.Model;

public class GioHang {
    private int ma_giohang;
    private int ma_giay;
    private int soLuong;
    private double Tong;
    private  String username;
    private String size;
    private String loaiGiay;

    public GioHang() {
    }


    public GioHang(int ma_giohang, int ma_giay, int soLuong, double tong, String username, String size, String loaiGiay) {
        this.ma_giohang = ma_giohang;
        this.ma_giay = ma_giay;
        this.soLuong = soLuong;
        this.Tong = tong;
        this.username = username;
        this.size = size;
        this.loaiGiay = loaiGiay;
    }

    public int getMa_giohang() {
        return ma_giohang;
    }

    public void setMa_giohang(int ma_giohang) {
        this.ma_giohang = ma_giohang;
    }

    public int getMa_giay() {
        return ma_giay;
    }

    public void setMa_giay(int ma_giay) {
        this.ma_giay = ma_giay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTong() {
        return Tong;
    }

    public void setTong(double tong) {
        Tong = tong;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLoaiGiay() {
        return loaiGiay;
    }

    public void setLoaiGiay(String loaiGiay) {
        this.loaiGiay = loaiGiay;
    }
}
