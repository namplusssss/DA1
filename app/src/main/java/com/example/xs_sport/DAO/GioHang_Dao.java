package com.example.xs_sport.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xs_sport.Database.DbHelper;
import com.example.xs_sport.Model.GioHang;

import java.util.ArrayList;

public class GioHang_Dao {

    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public GioHang_Dao(Context context){
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public ArrayList<GioHang> getAllData(){
        String sql = "SELECT * FROM tbl_giohang";
        return getData(sql);
    }

    public long insert(GioHang gioHang){
        ContentValues values = new ContentValues();
        values.put("ma_giay", gioHang.getMa_giay());
        values.put("TEN", gioHang.getUsername());
        values.put("soLuong", gioHang.getTong());
        values.put("Tong", gioHang.getTong());
        values.put("size", gioHang.getSize());
        return sqLiteDatabase.insert("tbl_giohang", null, values);
    }

    @SuppressLint("Range")
    public ArrayList<GioHang> getData(String sql, String... SelectAvg){
        ArrayList<GioHang> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvg);

        while (cursor.moveToNext()){
            GioHang gioHang = new GioHang();
            gioHang.setMa_giohang   (cursor.getInt(cursor.getColumnIndex("ma_giohang")));
            gioHang.setMa_giay      (cursor.getInt(cursor.getColumnIndex("ma_giay")));
            gioHang.setUsername     (cursor.getString(cursor.getColumnIndex("TEN")));
            gioHang.setSoLuong      (cursor.getInt(cursor.getColumnIndex("soLuong")));
            gioHang.setTong         (cursor.getDouble(cursor.getColumnIndex("Tong")));
            gioHang.setSize         (cursor.getString(cursor.getColumnIndex("size")));
            list.add(gioHang);
        }
        cursor.close();
        return list;
    }

    public boolean GiayExists(int giayId, String username){
        String query = "SELECT * FROM tbl_giohang where ma_giay = ? AND TEN=?";
        String[] selectionArgs = {String.valueOf(giayId), username};
        Cursor cursor = sqLiteDatabase.rawQuery(query, selectionArgs);
        boolean exists = cursor.getCount() >0;
        cursor.close();
        return exists;
    }

    public ArrayList<GioHang> getByUser(String user){
        String sql = "SELECT * FROM tbl_giohang where TEN = ?";
        return getData(sql, user);
    }

    public long upDateSum(GioHang gioHang){

        ContentValues values = new ContentValues();
        values.put("ma_giay" , gioHang.getMa_giay());
        values.put("TEN", gioHang.getUsername());
        values.put("soLuong", gioHang.getSoLuong());
        values.put("Tong", gioHang.getTong());
        values.put("size", gioHang.getSize());

        return sqLiteDatabase.update("tbl_giohang", values, "ma_giay=?", new String[]{String.valueOf(gioHang.getMa_giohang())});
    }

    public int delete(int ID){
        return sqLiteDatabase.delete("tbl_giohang", "ma_giay=?", new String[]{String.valueOf(ID)});
    }

    public void DeleteCart(String username){
        String query = "DELETE FROM tbl_giohang WHERE TEN=?";
        String[] selectionArgs = {username};
        Cursor cursor = sqLiteDatabase.rawQuery(query, selectionArgs);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
    }
}
