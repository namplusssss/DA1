package com.example.xs_sport.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xs_sport.Database.DbHelper;

import java.util.ArrayList;

public class DoanhThu_Dao {
    private final SQLiteDatabase sqLiteDatabase;

    private Context context;

    public DoanhThu_Dao(Context context){
        DbHelper helper = new DbHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sql1 = "   SELECT substr(thoigian_hoadon,7,10) AS date, SUM(tong_hoadon) AS doanhThu" +
                "        FROM tbl_hoadon" +
                "        where  trangthai_hoadon LIKE '%Thanh Toán Thành Công%' AND date BETWEEN ? AND ?";

        ArrayList<Integer> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql1, new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()){
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }

}
