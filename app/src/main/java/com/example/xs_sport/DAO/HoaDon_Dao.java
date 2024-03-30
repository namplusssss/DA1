package com.example.xs_sport.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xs_sport.Database.DbHelper;
import com.example.xs_sport.Model.HoaDon;

import java.util.ArrayList;

public class HoaDon_Dao {

    DbHelper dbHelper;
    private SQLiteDatabase sqlite;

    public HoaDon_Dao(Context context){
        dbHelper = new DbHelper(context);
        sqlite = dbHelper.getWritableDatabase();
    }

    public long update(HoaDon i){
        ContentValues values = new ContentValues();
        values.put("trangthai_hoadon", i.getTrangthai_hoadon());
        return sqlite.update("tbl_hoadon", values, "ma_hoadon=?", new String[]{String.valueOf(i.getMa_hoadon())});
    }

    @SuppressLint("Range")
    public ArrayList<HoaDon> SeLectDaDatHang(){
        ArrayList<HoaDon> list = new ArrayList<>();
        Cursor cursor = sqlite.rawQuery("SELECT * FROM tbl_hoadon WHERE trangthai_hoadon LIKE '%Đang chuẩn bị hàng%' ", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                HoaDon i = new HoaDon();

                i.setMa_hoadon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ma_hoadon"))));
                i.setMa_giohang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ma_giohang"))));
                i.setName(cursor.getString(cursor.getColumnIndex("TEN")));
                i.setSdt_hoadon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("sdt_hoadon"))));
                i.setDiachi_hoadon(cursor.getString(cursor.getColumnIndex("diachi_hoadon")));
                i.setThoigian_hoadon(cursor.getString(cursor.getColumnIndex("thoigian_hoadon")));
                i.setNoidung_hoadon(cursor.getString(cursor.getColumnIndex("noidung_hoadon")));
                i.setTong_hoadon(Double.parseDouble(cursor.getString(cursor.getColumnIndex("tong_hoadon"))));
                i.setTrangthai_hoadon(cursor.getString(cursor.getColumnIndex("trangthai_hoadon")));
                list.add(i);

            }while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<HoaDon> SelectDangChuanBi(){
        ArrayList<HoaDon> list = new ArrayList<>();
        Cursor cursor = sqlite.rawQuery("SELECT * FROM tbl_hoadon WHERE trangthai_hoadon LIKE '%Đang giao%'", null);
        if (cursor.getCount() >0 ){
            cursor.moveToFirst();
            do {
                HoaDon i = new HoaDon();

                i.setMa_hoadon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ma_hoadon"))));
                i.setMa_giohang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ma_giohang"))));
                i.setName(cursor.getString(cursor.getColumnIndex("TEN")));
                i.setSdt_hoadon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("sdt_hoadon"))));
                i.setDiachi_hoadon(cursor.getString(cursor.getColumnIndex("diachi_hoadon")));
                i.setThoigian_hoadon(cursor.getString(cursor.getColumnIndex("thoigian_hoadon")));
                i.setNoidung_hoadon(cursor.getString(cursor.getColumnIndex("noidung_hoadon")));
                i.setTong_hoadon(Double.parseDouble(cursor.getString(cursor.getColumnIndex("tong_hoadon"))));
                i.setTrangthai_hoadon(cursor.getString(cursor.getColumnIndex("trangthai_hoadon")));

                list.add(i);
            }while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<HoaDon> SeLectDangGiao(){
        ArrayList<HoaDon> list = new ArrayList<>();
        Cursor cursor = sqlite.rawQuery("SELECT * FROM tbl_hoadon WHERE trangthai_hoadon LIKE '%Đã thanh toán%'", null);
        if (cursor.getCount() >0 ){
            cursor.moveToFirst();
            do {
                HoaDon i = new HoaDon();

                i.setMa_hoadon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ma_hoadon"))));
                i.setMa_giohang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ma_giohang"))));
                i.setName(cursor.getString(cursor.getColumnIndex("TEN")));
                i.setSdt_hoadon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("sdt_hoadon"))));
                i.setDiachi_hoadon(cursor.getString(cursor.getColumnIndex("diachi_hoadon")));
                i.setThoigian_hoadon(cursor.getString(cursor.getColumnIndex("thoigian_hoadon")));
                i.setNoidung_hoadon(cursor.getString(cursor.getColumnIndex("noidung_hoadon")));
                i.setTong_hoadon(Double.parseDouble(cursor.getString(cursor.getColumnIndex("tong_hoadon"))));
                i.setTrangthai_hoadon(cursor.getString(cursor.getColumnIndex("trangthai_hoadon")));

                list.add(i);
            }while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<HoaDon> SeLectDaThanhToan(){
        ArrayList<HoaDon> list = new ArrayList<>();
        Cursor cursor = sqlite.rawQuery("SELECT * FROM tbl_hoadon WHERE trangthai_hoadon LIKE '%Thanh toán thành công%'", null);
        if (cursor.getCount() >0 ){
            cursor.moveToFirst();
            do {
                HoaDon i = new HoaDon();

                i.setMa_hoadon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ma_hoadon"))));
                i.setMa_giohang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ma_giohang"))));
                i.setName(cursor.getString(cursor.getColumnIndex("TEN")));
                i.setSdt_hoadon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("sdt_hoadon"))));
                i.setDiachi_hoadon(cursor.getString(cursor.getColumnIndex("diachi_hoadon")));
                i.setThoigian_hoadon(cursor.getString(cursor.getColumnIndex("thoigian_hoadon")));
                i.setNoidung_hoadon(cursor.getString(cursor.getColumnIndex("noidung_hoadon")));
                i.setTong_hoadon(Double.parseDouble(cursor.getString(cursor.getColumnIndex("tong_hoadon"))));
                i.setTrangthai_hoadon(cursor.getString(cursor.getColumnIndex("trangthai_hoadon")));

                list.add(i);
            }while (cursor.moveToNext());
        }
        return list;
    }
}
