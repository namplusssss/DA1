package com.example.xs_sport.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xs_sport.Database.DbHelper;
import com.example.xs_sport.Model.LoaiGiay;

import java.util.ArrayList;

public class LoaiGiay_Dao {

    DbHelper dbHelper;
    private static SQLiteDatabase sqLite;

    public LoaiGiay_Dao(Context context){
        dbHelper = new DbHelper(context);
        sqLite = dbHelper.getWritableDatabase();
    }


    @SuppressLint("Range")
    public static ArrayList<LoaiGiay> getDataLoaiGiay(String sql, String... SelectAvg){
        ArrayList<LoaiGiay> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_loaiGiay", SelectAvg);
        while (cursor.moveToNext()){
            LoaiGiay giay = new LoaiGiay();
            giay.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ma_loaiGiay"))));
            giay.setTenLoai(cursor.getString(cursor.getColumnIndex("loaiGiay_tenGiay")));
            list.add(giay);
        }
        return list;
    }

    public static ArrayList<LoaiGiay> getAllData(){
        String sql = "SELECT * FROM tbl_loaiGiay";
        return getDataLoaiGiay(sql);
    }

    public static ArrayList<String> name(){
        String name = "SELECT loaiGiay_tenGiay FROM tbl_loaiGiay";
        return getName(name);
    }

    public static ArrayList<String> getName(String sql, String... SelectAvg){
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery(sql, SelectAvg);
        while (cursor.moveToNext()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("loaiGiay_tenGiay"));
            list.add(name);
        }
        return list;
    }

    public long insert(LoaiGiay giay){
        ContentValues values = new ContentValues();
        values.put("loaiGiay_tenGiay", giay.getTenLoai());
        return sqLite.insert("tbl_loaiGiay", null, values);
    }

    public int update(LoaiGiay giay){
        ContentValues values = new ContentValues();
        values.put("loaiGiay_tenGiay", giay.getTenLoai());
        return sqLite.update("tbl_loaiGiay", values, "ma_loaiGiay=?", new String[]{String.valueOf(giay.getMaLoai())});
    }

    public int delete(String id){
        return sqLite.delete("tbl_loaiGiay","ma_loaiGiay=?",new String[]{id});
    }

    public ArrayList<LoaiGiay> getAll(){
        String sql = "Select * from tbl_loaiGiay";
        return getDataLoaiGiay(sql);
    }
}
