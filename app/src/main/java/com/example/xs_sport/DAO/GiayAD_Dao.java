package com.example.xs_sport.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.xs_sport.Database.DbHelper;
import com.example.xs_sport.Model.Giay_AD;

import java.util.ArrayList;

public class GiayAD_Dao {
    DbHelper dbHelper;

    private SQLiteDatabase sqLiteDatabase;

    public GiayAD_Dao(Context context){
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public ArrayList<Giay_AD> getAllData(){
        String sql = "SELECT * FROM tbl_giay";
        return getData(sql);
    }

    @SuppressLint("Range")
    public ArrayList<Giay_AD> getData(String sql, String... SelectAvg){
        ArrayList<Giay_AD> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_giay", SelectAvg);

        while (cursor.moveToNext()){
            Giay_AD giay = new Giay_AD();
            giay.setMa_giay(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ma_giay"))));
            giay.setLoaiGiay_tenGiay(cursor.getString(cursor.getColumnIndex("loaiGiay_tenGiay")));
            giay.setAnh_giay(cursor.getString(cursor.getColumnIndex("anh_giay")));
            giay.setTen_giay(cursor.getString(cursor.getColumnIndex("ten_giay")));
            giay.setMota_giay(cursor.getString(cursor.getColumnIndex("mota_giay")));
            giay.setGia_giay(cursor.getInt(cursor.getColumnIndex("gia_giay")));
            giay.setSize(cursor.getString(cursor.getColumnIndex("size")));
            list.add(giay);
        }
        return list;
    }

    public long insert(Giay_AD giayAd){
        ContentValues values = new ContentValues();
        values.put("loaiGiay_tenGiay", giayAd.getLoaiGiay_tenGiay());
        values.put("anh_giay", giayAd.getAnh_giay());
        values.put("ten_giay", giayAd.getTen_giay());
        values.put("mota_giay", giayAd.getMota_giay());
        values.put("gia_giay", giayAd.getGia_giay());
        values.put("size", giayAd.getSize());
        return sqLiteDatabase.insert("tbl_giay", null, values);
    }


    private ArrayList<String> getSuggestions(String query){
        SQLiteDatabase sqLite = dbHelper.getReadableDatabase();
        ArrayList<String> suggestions = new ArrayList<>();

        String[] projection = {"ten_giay"};
        String selection = "ten_giay LIKE ?";
        String[] selectionArgs = new String[]{"%" + query + "%"};

        Cursor cursor = sqLite.query(
                true,
                "tbl_giay",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null,
                "10"
        );

        if (cursor != null && cursor.moveToFirst()){
            do {
                String suggestion = cursor.getString(Integer.parseInt(String.valueOf(cursor.getColumnIndex("ten_giay"))));
                suggestions.add(suggestion);
            }while (cursor.moveToNext());
        }

        if (cursor != null){
            cursor.close();
        }

        for (String suggestion : suggestions){
            Log.d("SearchSuggestions", suggestion);
        }
        return suggestions;
    }


    @SuppressLint("Range")
    public ArrayList<Giay_AD> search(String ten){
        SQLiteDatabase sqlite = dbHelper.getReadableDatabase();
        ArrayList<Giay_AD> list = new ArrayList<>();

        String[] projection =
                {

                        "ma_giay",
                        "anh_giay",
                        "loaiGiay_tenGiay",
                        "ten_giay",
                        "mota_giay",
                        "gia_giay",
                        "size"
                };

        String selection = "ten_giay LIKE ?";
        String[] selectionArgs = new String[] {"%" + ten + "%"};

        Cursor cursor = sqlite.query(
                "tbl_giay",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()){
            do {
                Giay_AD giay = new Giay_AD();
                giay.setMa_giay(cursor.getInt(cursor.getColumnIndex("ma_giay")));
                giay.setLoaiGiay_tenGiay(cursor.getString(cursor.getColumnIndex("loaiGiay_tenGiay")));
                giay.setAnh_giay(cursor.getString(cursor.getColumnIndex("anh_giay")));
                giay.setTen_giay(cursor.getString(cursor.getColumnIndex("ten_giay")));
                giay.setMota_giay(cursor.getString(cursor.getColumnIndex("mota_giay")));
                giay.setGia_giay(cursor.getInt(cursor.getColumnIndex("gia_giay")));
                giay.setSize(cursor.getString(cursor.getColumnIndex("size")));
            }while (cursor.moveToNext());
        }

        if (cursor != null){
            cursor.close();
        }

        ArrayList<String> suggestions = getSuggestions(ten);

        return list;
    }

    public Giay_AD getByID(int id){
        Cursor cursor = sqLiteDatabase.query("tbl_giay", null, "ma_giay=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToNext()){
            return new Giay_AD(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6));
        }else {
            return null;
        }
    }

    public long update(Giay_AD ad){
        ContentValues values = new ContentValues();
        values.put("loaiGiay_tenGiay", ad.getLoaiGiay_tenGiay());
        values.put("anh_giay", ad.getAnh_giay());
        values.put("ten_giay", ad.getTen_giay());
        values.put("mota_giay", ad.getMota_giay());
        values.put("gia_giay", ad.getGia_giay());
        values.put("size", ad.getSize());
        return sqLiteDatabase.update("tbl_giay", values, "ma_giay=?", new String[]{String.valueOf(ad.getMa_giay())});
    }

    public int delete(int ID){
        return sqLiteDatabase.delete("tbl_giay", "ma_giay=?", new String[]{String.valueOf(ID)});
    }

}
