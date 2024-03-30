package com.example.xs_sport.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xs_sport.Database.DbHelper;
import com.example.xs_sport.Model.HoaHon_KH;

import java.util.ArrayList;

public class HoaDonKH_Dao {
    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public HoaDonKH_Dao(Context context){
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public long insert(HoaHon_KH kh){
        ContentValues values = new ContentValues();

//        "tbl_hoadon (" +
//                "ma_hoadon INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "TEN TEXT REFERENCES KHACH_HANG(TEN)," +
//                "ma_giohang INTEGER REFERENCES tbl_giohang(ma_giohang), " +
//                "loaiGiay_tenGiay TEXT REFERENCES tbl_loaiGiay(loaiGiay_tenGiay)," +
//                "sdt_hoadon TEXT NOT NULL, " +
//                "diachi_hoadon TEXT NOT NULL, " +
//                "noidung_hoadon TEXT NOT NULL, " +
//                "tong_hoadon DOUBLE NOT NULL, " +
//                "trangthai_hoadon TEXT ," +
//                "thoigian_hoadon TEXT NOT NULL)";


        values.put("ma_giohang", kh.getMa_giohang());
        values.put("TEN", kh.getName());
        values.put("sdt_hoadon", kh.getSdt_hoadon());
        values.put("diachi_hoadon", kh.getDiachi_hoadon());
        values.put("noidung_hoadon", kh.getNoidung_hoadon());
        values.put("tong_hoadon", kh.getTong_hoadon());
        values.put("trangthai_hoadon", kh.getTrangthai_hoadon());
        values.put("thoigian_hoadon", kh.getThoigian_hoadon());

        return sqLiteDatabase.insert("tbl_hoadon", null, values);
    }

    public int delete(int ID){
        return sqLiteDatabase.delete("tbl_hoadon","ma_hoadon=?", new String[]{String.valueOf(ID)});
    }

    @SuppressLint("Range")
    public ArrayList<HoaHon_KH> getByUser(String username){
        ArrayList<HoaHon_KH> list = new ArrayList<>();
        String query = "SELECT * FROM tbl_hoadon WHERE TEN =? AND trangthai_hoadon LIKE '%Đang chuẩn bị hàng%'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{username});
        while (cursor.moveToNext()){
            HoaHon_KH history = new HoaHon_KH();
            history.setMa_hoadon(cursor.getInt(cursor.getColumnIndex("ma_hoadon")));
            history.setMa_giohang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ma_giohang"))));
            history.setSdt_hoadon(cursor.getInt(cursor.getColumnIndex("sdt_hoadon")));
            history.setName(cursor.getString(cursor.getColumnIndex("TEN")));
            history.setDiachi_hoadon(cursor.getString(cursor.getColumnIndex("diachi_hoadon")));
            history.setThoigian_hoadon(cursor.getString(cursor.getColumnIndex("thoigian_hoadon")));
            history.setNoidung_hoadon(cursor.getString(cursor.getColumnIndex("noidung_hoadon")));
            history.setTong_hoadon(cursor.getDouble(cursor.getColumnIndex("tong_hoadon")));
            history.setTrangthai_hoadon(cursor.getString(cursor.getColumnIndex("trangthai_hoadon")));
            list.add(history);
        }
        cursor.close();
        return list;
    }


    @SuppressLint("Range")
    public ArrayList<HoaHon_KH> SelectUserDangCB(String username){
        ArrayList<HoaHon_KH> list = new ArrayList<>();
        String query = "SELECT * FROM tbl_hoadon WHERE TEN =? AND trangthai_hoadon LIKE '%Đang giao%'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{username});
        while (cursor.moveToNext()){
            HoaHon_KH history = new HoaHon_KH();
            history.setMa_hoadon(cursor.getInt(cursor.getColumnIndex("ma_hoadon")));
            history.setMa_giohang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ma_giohang"))));
            history.setSdt_hoadon(cursor.getInt(cursor.getColumnIndex("sdt_hoadon")));
            history.setName(cursor.getString(cursor.getColumnIndex("TEN")));
            history.setDiachi_hoadon(cursor.getString(cursor.getColumnIndex("diachi_hoadon")));
            history.setThoigian_hoadon(cursor.getString(cursor.getColumnIndex("thoigian_hoadon")));
            history.setNoidung_hoadon(cursor.getString(cursor.getColumnIndex("noidung_hoadon")));
            history.setTong_hoadon(cursor.getDouble(cursor.getColumnIndex("tong_hoadon")));
            history.setTrangthai_hoadon(cursor.getString(cursor.getColumnIndex("trangthai_hoadon")));
            list.add(history);
        }
        cursor.close();
        return list;
    }


    @SuppressLint("Range")
    public ArrayList<HoaHon_KH> SelectUserDangGiao(String username){
        ArrayList<HoaHon_KH> list = new ArrayList<>();
        String query = "SELECT * FROM tbl_hoadon WHERE TEN =? AND trangthai_hoadon LIKE '%Đã thanh toán%'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{username});
        while (cursor.moveToNext()){
            HoaHon_KH history = new HoaHon_KH();
            history.setMa_hoadon(cursor.getInt(cursor.getColumnIndex("ma_hoadon")));
            history.setMa_giohang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ma_giohang"))));
            history.setSdt_hoadon(cursor.getInt(cursor.getColumnIndex("sdt_hoadon")));
            history.setName(cursor.getString(cursor.getColumnIndex("TEN")));
            history.setDiachi_hoadon(cursor.getString(cursor.getColumnIndex("diachi_hoadon")));
            history.setThoigian_hoadon(cursor.getString(cursor.getColumnIndex("thoigian_hoadon")));
            history.setNoidung_hoadon(cursor.getString(cursor.getColumnIndex("noidung_hoadon")));
            history.setTong_hoadon(cursor.getDouble(cursor.getColumnIndex("tong_hoadon")));
            history.setTrangthai_hoadon(cursor.getString(cursor.getColumnIndex("trangthai_hoadon")));
            list.add(history);
        }
        cursor.close();
        return list;
    }


    @SuppressLint("Range")
    public ArrayList<HoaHon_KH> SelectUserDaThanhToan(String username){
        ArrayList<HoaHon_KH> list = new ArrayList<>();
        String query = "SELECT * FROM tbl_hoadon WHERE TEN =? AND trangthai_hoadon LIKE '%Thanh toán thành công%'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{username});
        while (cursor.moveToNext()){
            HoaHon_KH history = new HoaHon_KH();
            history.setMa_hoadon(cursor.getInt(cursor.getColumnIndex("ma_hoadon")));
            history.setMa_giohang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ma_giohang"))));
            history.setSdt_hoadon(cursor.getInt(cursor.getColumnIndex("sdt_hoadon")));
            history.setName(cursor.getString(cursor.getColumnIndex("TEN")));
            history.setDiachi_hoadon(cursor.getString(cursor.getColumnIndex("diachi_hoadon")));
            history.setThoigian_hoadon(cursor.getString(cursor.getColumnIndex("thoigian_hoadon")));
            history.setNoidung_hoadon(cursor.getString(cursor.getColumnIndex("noidung_hoadon")));
            history.setTong_hoadon(cursor.getDouble(cursor.getColumnIndex("tong_hoadon")));
            history.setTrangthai_hoadon(cursor.getString(cursor.getColumnIndex("trangthai_hoadon")));
            list.add(history);
        }
        cursor.close();
        return list;
    }

}
