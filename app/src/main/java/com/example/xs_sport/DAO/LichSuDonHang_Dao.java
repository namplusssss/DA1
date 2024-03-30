package com.example.xs_sport.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.xs_sport.Database.DbHelper;
import com.example.xs_sport.Model.LichSuDonHang;

public class LichSuDonHang_Dao {

    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public LichSuDonHang_Dao(Context context){
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public long insert(LichSuDonHang history){
        ContentValues values = new ContentValues();
        values.put("ma_giohang", history.getMa_giohang());
        values.put("TEN" , history.getName());
        values.put("diachi_hoadon", history.getDiaChi());
        values.put("sdt_hoadon", history.getSdt());
        values.put("thoigian_hoadon", history.getThoiGian());
        values.put("noidung_hoadon", history.getNoiDung());
        values.put("tong_hoadon", history.getTong());
        values.put("trangthai_hoadon", history.getTrangThai());

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

        return sqLiteDatabase.insert("tbl_hoadon", null, values);
    }
}
