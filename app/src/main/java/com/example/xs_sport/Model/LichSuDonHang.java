package com.example.xs_sport.Model;

public class LichSuDonHang {
    private int id_lichsu;
    private int ma_giohang;
    private int sdt;
    private String loaiGiay;
    private String name;
    private String diaChi;
    private String thoiGian;
    private  String noiDung;
    private String trangThai;
    private double tong;
    private String size;

    public LichSuDonHang() {
    }

    public LichSuDonHang(int id_lichsu, int ma_giohang, int sdt, String loaiGiay, String name, String diaChi, String thoiGian, String noiDung, String trangThai, double tong, String size) {
        this.id_lichsu = id_lichsu;
        this.ma_giohang = ma_giohang;
        this.sdt = sdt;
        this.loaiGiay = loaiGiay;
        this.name = name;
        this.diaChi = diaChi;
        this.thoiGian = thoiGian;
        this.noiDung = noiDung;
        this.trangThai = trangThai;
        this.tong = tong;
        this.size = size;
    }

    public int getId_lichsu() {
        return id_lichsu;
    }

    public void setId_lichsu(int id_lichsu) {
        this.id_lichsu = id_lichsu;
    }

    public int getMa_giohang() {
        return ma_giohang;
    }

    public void setMa_giohang(int ma_giohang) {
        this.ma_giohang = ma_giohang;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getLoaiGiay() {
        return loaiGiay;
    }

    public void setLoaiGiay(String loaiGiay) {
        this.loaiGiay = loaiGiay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public double getTong() {
        return tong;
    }

    public void setTong(double tong) {
        this.tong = tong;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
