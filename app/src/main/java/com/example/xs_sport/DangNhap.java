package com.example.xs_sport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.xs_sport.Activity.TrangChu;
import com.example.xs_sport.DAO.Admin_Dao;
import com.example.xs_sport.DAO.KhachHang_Dao;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class DangNhap extends AppCompatActivity {

    TextInputEditText edtDangNhap, edtMatKhau;
    Button btnDangNhap;
    TextView txtchuyenDKi;
    KhachHang_Dao khachHang_dao;

    CheckBox luuMKCheckBox;
    Admin_Dao adminDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        //
        edtDangNhap = findViewById(R.id.edtDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        txtchuyenDKi = findViewById(R.id.txtchuyenDKi);

        //
        khachHang_dao = new KhachHang_Dao(this);
        adminDao = new Admin_Dao(this);

        //
        luuMKCheckBox = findViewById(R.id.LuuMK);

        //
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String saveTk = sharedPreferences.getString("TEN", "");
        String saveMk = sharedPreferences.getString("MATKHAU", "");
        if (!saveTk.isEmpty() && !saveMk.isEmpty()){
            edtDangNhap.setText(saveTk);
            edtMatKhau.setText(saveMk);
            luuMKCheckBox.setChecked(true);
        }

        //
        txtchuyenDKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangNhap.this, DangKy.class));
            }
        });

        //
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tk_Dn = edtDangNhap.getText().toString();
                String mk_Dn = edtMatKhau.getText().toString();


                if (tk_Dn.isEmpty() || mk_Dn.isEmpty()){
                    Toast.makeText(DangNhap.this, "Thiếu tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }else {
                    if (adminDao.checkDangNhap(tk_Dn, mk_Dn) == 1){
                        XuLyDangNhap(tk_Dn, mk_Dn);
                        Intent intent = new Intent(DangNhap.this, Admin_MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(DangNhap.this, "Đăng nhập tài khoản Admin", Toast.LENGTH_SHORT).show();
                    }
                    else if (khachHang_dao.checkDangNhap(tk_Dn, mk_Dn) == 1) {
                        XuLyDangNhap(tk_Dn, mk_Dn);
                        Intent intent = new Intent(DangNhap.this, TrangChu.class);
                        startActivity(intent);
                        Toast.makeText(DangNhap.this, "Đăng nhập tài khoản khách hàng", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(DangNhap.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void XuLyDangNhap(String user, String pass){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (luuMKCheckBox.isChecked()){
            editor.putString("TEN", user);
            editor.putString("MATKHAU", pass);
            editor.apply();
        }else {
            editor.remove("TEN");
            editor.remove("MATKHAU");
            editor.apply();
        }
    }

}