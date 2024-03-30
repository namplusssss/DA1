package com.example.xs_sport.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "xssprot.txt";
    private static final int DB_version = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_version);
    }

    //Bang Admin
    static final String CREATE_TABLE_ADMIN =
            "CREATE TABLE ADMIN(" +
                    "ID_ADMIN INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "TEN TEXT NOT NULL, " +
                    "EMAIL TEXT NOT NULL," +
                    "MATKHAU TEXT NOT NULL)";

    //Bang Khach Hang
    static final String CREATE_TABLE_KHACH_HANG =
            "CREATE TABLE KHACH_HANG(" +
                    "ID_KHACH_HANG INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "TEN TEXT NOT NULL, " +
                    "SDT TEXT NOT NULL, " +
                    "EMAIL TEXT NOT NULL," +
                    "MATKHAU TEXT NOT NULL)";

    //Bang Loai Giay
    public static final String TABLE_LOAIGIAY_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_loaiGiay (" +
            "ma_loaiGiay INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "loaiGiay_tenGiay TEXT NOT NULL" +
            ")";

    //Bang Giay
    public static final String TABLE_GIAY_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_giay (" +
            "ma_giay INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "loaiGiay_tenGiay TEXT REFERENCES tbl_loaiGiay(loaiGiay_tenGiay)," +
            "anh_giay TEXT NOT NULL, " +
            "ten_giay TEXT NOT NULL, " +
            "mota_giay TEXT NOT NULL, " +
            "gia_giay DOUBLE NOT NULL, " +
            "size TEXT " +
            ")";

    //Bang Gio Hang
    public static final String TABLE_GIOHANG_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_giohang (" +
            "ma_giohang INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ma_giay INTEGER REFERENCES tbl_giay(ma_giay), " +
            "TEN TEXT REFERENCES KHACH_HANG(TEN), " +
            "soLuong INTEGER NOT NULL, " +
            "Tong DOUBLE NOT NULL, " +
            "size TEXT)";

    //Bang Hoa Don
    public static final String TABLE_HOADON_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_hoadon (" +
            "ma_hoadon INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "TEN TEXT REFERENCES KHACH_HANG(TEN)," +
            "ma_giohang INTEGER REFERENCES tbl_giohang(ma_giohang), " +
            "loaiGiay_tenGiay TEXT REFERENCES tbl_loaiGiay(loaiGiay_tenGiay)," +
            "sdt_hoadon TEXT NOT NULL, " +
            "diachi_hoadon TEXT NOT NULL, " +
            "noidung_hoadon TEXT NOT NULL, " +
            "tong_hoadon DOUBLE NOT NULL, " +
            "trangthai_hoadon TEXT ," +
            "thoigian_hoadon TEXT NOT NULL)";

    //Bang comment
    public static final String TABLE_COMMENT_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_comment (" +
            "comment_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "comment_content TEXT ," +
            "TEN TEXT REFERENCES KHACH_HANG(TEN)," +
            "ma_giay INTEGER REFERENCES tbl_giay(ma_giay)," +
            "xephang INTEGER " +
            ")";


//    public static final String insert_cmt = "Insert into tbl_comment(comment_content ,TEN ,coffee_id , rating) values" +
//            "('đẹp , tuyệt vời','nam','1','4'), " +
//            "('Shop tuyệt vời','hưng','2','5')," +
//            "('hết nước chấm','thúy','1','5')," +
//            "('okkkk','nguyên','2','4')";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ADMIN);
        sqLiteDatabase.execSQL(CREATE_TABLE_KHACH_HANG);
        sqLiteDatabase.execSQL(TABLE_LOAIGIAY_CREATE);
        sqLiteDatabase.execSQL(TABLE_GIAY_CREATE);
        sqLiteDatabase.execSQL(TABLE_GIOHANG_CREATE);
        sqLiteDatabase.execSQL(TABLE_HOADON_CREATE);
        sqLiteDatabase.execSQL(TABLE_COMMENT_CREATE);


//        static final String CREATE_TABLE_ADMIN =
//                "CREATE TABLE ADMIN(" +
//                        "ID_ADMIN INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "TEN TEXT NOT NULL, " +
//                        "EMAIL TEXT NOT NULL," +
//                        "MATKHAU TEXT NOT NULL)";

        sqLiteDatabase.execSQL("INSERT INTO ADMIN VALUES (1,'ADMIN','hoanghung2472@gmail.com','adm123')");

        sqLiteDatabase.execSQL("INSERT INTO KHACH_HANG VALUES (1, 'hoanghung', '0379663289' ,'hunghh22205@gmail.com', '123456')");

        sqLiteDatabase.execSQL("INSERT INTO tbl_loaiGiay VALUES (1,'NIKE')");
        sqLiteDatabase.execSQL("INSERT INTO tbl_loaiGiay VALUES (2,'ADIDAS')");

        sqLiteDatabase.execSQL("INSERT INTO tbl_giay(ten_giay, loaiGiay_tenGiay, anh_giay, mota_giay, gia_giay, size) VALUES" +
                                                    "('Nike Air Force','Nike', 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/d81dae0f-e39d-4c22-a2a1-ee4b8b82a839/air-force-1-07-shoes-NMmm1B.png','Em dep',2900000,'42')," +
                                                    "('Adidas Stan Smith','Adidas', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/f633a0c9cb124e16914cac210127ca62_9366/Stan_Smith_Shoes_White_FX5501.jpg','Em dep',2900000,'42'),"+
                                                    "('Nike Air Force','Nike', 'https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/d81dae0f-e39d-4c22-a2a1-ee4b8b82a839/air-force-1-07-shoes-NMmm1B.png','Em dep',2900000,'42')");

//        sqLiteDatabase.execSQL(insert_cmt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists ADMIN");
        sqLiteDatabase.execSQL("drop table if exists KHACH_HANG");
        sqLiteDatabase.execSQL("drop table if exists tbl_giay");
        sqLiteDatabase.execSQL("drop table if exists tbl_giohang");
        sqLiteDatabase.execSQL("drop table if exists tbl_hoadon");

        onCreate(sqLiteDatabase);
    }
}
