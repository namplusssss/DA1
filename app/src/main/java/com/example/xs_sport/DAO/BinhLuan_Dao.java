package com.example.xs_sport.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xs_sport.Database.DbHelper;
import com.example.xs_sport.Model.BinhLuan;

import java.util.ArrayList;

public class BinhLuan_Dao {
    DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public BinhLuan_Dao(Context context){
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public ArrayList<BinhLuan> getByGiayId(String ID){
        String sql = "SELECT * FROM tbl_comment where ma_giay=?";
        return getData(sql, ID);
    }

    @SuppressLint("Range")
    public ArrayList<BinhLuan> getData(String sql, String... SelectAvg){
        ArrayList<BinhLuan> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvg);
        while (cursor.moveToNext()){
            BinhLuan binhLuan = new BinhLuan();
            binhLuan.setComment_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("comment_id"))));
            binhLuan.setUser_name(cursor.getString(cursor.getColumnIndex("user_name")));
            binhLuan.setComment_content(cursor.getString(cursor.getColumnIndex("comment_content")));
            binhLuan.setMa_giay(cursor.getInt(cursor.getColumnIndex("ma_giay")));
            binhLuan.setXephang(cursor.getInt(cursor.getColumnIndex("xephang")));
        }
        return list;
    }

    public int countCmt(String id){
        String sql = "SELECT COUNT(comment_id) FROM tbl_comment WHERE ma_giay=?";
        Cursor c = sqLiteDatabase.rawQuery(sql, new String[]{id});
        int count = 0;
        if (c != null){
            if (c.moveToFirst()){
                count = c.getInt(0);
            }
            c.close();
        }
        return count;
    }

    public float getAVG(String giayID){
        String query = "SELECT AVG(xephang) FROM tbl_comment WHERE ma_giay=?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{giayID});
        float averageRating = 0;
        if (cursor.moveToFirst()){
            averageRating = cursor.getFloat(0);
        }
        cursor.close();
        return averageRating;
    }
}
