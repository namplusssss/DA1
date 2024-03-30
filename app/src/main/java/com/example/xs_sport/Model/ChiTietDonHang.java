package com.example.xs_sport.Model;

public class ChiTietDonHang {
    private int id_chiTietDonhang;
    private int id_donHang;
    private int id_giay;
    private int so_luong;
    private double gia;
    private String size;
    private String type;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(int id_chiTietDonhang, int id_donHang, int id_giay, int so_luong, double gia, String size, String type) {
        this.id_chiTietDonhang = id_chiTietDonhang;
        this.id_donHang = id_donHang;
        this.id_giay = id_giay;
        this.so_luong = so_luong;
        this.gia = gia;
        this.size = size;
        this.type = type;
    }

    public int getId_chiTietDonhang() {
        return id_chiTietDonhang;
    }

    public void setId_chiTietDonhang(int id_chiTietDonhang) {
        this.id_chiTietDonhang = id_chiTietDonhang;
    }

    public int getId_donHang() {
        return id_donHang;
    }

    public void setId_donHang(int id_donHang) {
        this.id_donHang = id_donHang;
    }

    public int getId_giay() {
        return id_giay;
    }

    public void setId_giay(int id_giay) {
        this.id_giay = id_giay;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
