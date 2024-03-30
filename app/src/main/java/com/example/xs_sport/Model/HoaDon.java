package com.example.xs_sport.Model;

public class HoaDon {
    private int ma_hoadon;
    private int ma_giohang;
    private int sdt_hoadon;
    private String name;
    private String diachi_hoadon;
    private String thoigian_hoadon;
    private  String noidung_hoadon;
    private String trangthai_hoadon;
    private double tong_hoadon;

    public HoaDon() {
    }

    public HoaDon(int ma_hoadon, int ma_giohang, int sdt_hoadon, String name, String diachi_hoadon, String thoigian_hoadon, String noidung_hoadon, String trangthai_hoadon, double tong_hoadon) {
        this.ma_hoadon = ma_hoadon;
        this.ma_giohang = ma_giohang;
        this.sdt_hoadon = sdt_hoadon;
        this.name = name;
        this.diachi_hoadon = diachi_hoadon;
        this.thoigian_hoadon = thoigian_hoadon;
        this.noidung_hoadon = noidung_hoadon;
        this.trangthai_hoadon = trangthai_hoadon;
        this.tong_hoadon = tong_hoadon;
    }

    public int getMa_hoadon() {
        return ma_hoadon;
    }

    public void setMa_hoadon(int ma_hoadon) {
        this.ma_hoadon = ma_hoadon;
    }

    public int getMa_giohang() {
        return ma_giohang;
    }

    public void setMa_giohang(int ma_giohang) {
        this.ma_giohang = ma_giohang;
    }

    public int getSdt_hoadon() {
        return sdt_hoadon;
    }

    public void setSdt_hoadon(int sdt_hoadon) {
        this.sdt_hoadon = sdt_hoadon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiachi_hoadon() {
        return diachi_hoadon;
    }

    public void setDiachi_hoadon(String diachi_hoadon) {
        this.diachi_hoadon = diachi_hoadon;
    }

    public String getThoigian_hoadon() {
        return thoigian_hoadon;
    }

    public void setThoigian_hoadon(String thoigian_hoadon) {
        this.thoigian_hoadon = thoigian_hoadon;
    }

    public String getNoidung_hoadon() {
        return noidung_hoadon;
    }

    public void setNoidung_hoadon(String noidung_hoadon) {
        this.noidung_hoadon = noidung_hoadon;
    }

    public String getTrangthai_hoadon() {
        return trangthai_hoadon;
    }

    public void setTrangthai_hoadon(String trangthai_hoadon) {
        this.trangthai_hoadon = trangthai_hoadon;
    }

    public double getTong_hoadon() {
        return tong_hoadon;
    }

    public void setTong_hoadon(double tong_hoadon) {
        this.tong_hoadon = tong_hoadon;
    }
}

