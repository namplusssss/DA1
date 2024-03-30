package com.example.xs_sport;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xs_sport.DAO.KhachHang_Dao;
import com.example.xs_sport.Model.KhachHang;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;


public class DangKy extends AppCompatActivity {

    TextInputEditText edtDangKy, edt_SDT, edtEmail, edt_MkDangKy, edt_ReMK;
    TextView txtchuyenDNhap;
    Button btnDangKy;
    KhachHang_Dao khachHangDao;
    KhachHang khachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        //
        edtDangKy = findViewById(R.id.edtDangKy);
        edt_SDT = findViewById(R.id.edt_SDT);
        edtEmail = findViewById(R.id.edtEmail);
        edt_MkDangKy = findViewById(R.id.edt_MkDangKy);
        edt_ReMK = findViewById(R.id.edt_ReMK);

        //
        txtchuyenDNhap = findViewById(R.id.txtchuyenDNhap);
        btnDangKy = findViewById(R.id.btnDangKy);


        //
        txtchuyenDNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangKy.this, DangNhap.class));
            }
        });

        //
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taiKhoanDK = edtDangKy.getText().toString();
                String email = edtEmail.getText().toString();
                String sdt = edt_SDT.getText().toString();
                String mk_DK = edt_MkDangKy.getText().toString();

                int validationResult = validate();
                if (validationResult == 1){
                    khachHangDao = new KhachHang_Dao(DangKy.this);
                    khachHang = new KhachHang();
                    khachHang.setTEN_KH(taiKhoanDK);
                    khachHang.setEMAIL_KH(email);
                    khachHang.setSDT(sdt);
                    khachHang.setMAT_KHAU_KH(mk_DK);

                    long result = khachHangDao.insert(khachHang);
                    if (result > 0){
                        Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DangKy.this, DangNhap.class));
                    }else {
                        Toast.makeText(DangKy.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public int validate(){
        int check = 1;
        if (    edtDangKy.getText().length() == 0|| edtEmail.getText().length() == 0|| edt_MkDangKy.getText().length() == 0|| edt_ReMK.getText().length() == 0)   {
            Toast.makeText(this, "Bạn cần điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }  else {
            //
            String pass = edt_MkDangKy.getText().toString();
            String pass2 = edt_ReMK.getText().toString();

            if (!pass.equals(pass2)){
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }

            //
            String sdt = edt_SDT.getText().toString();
            String email = edtEmail.getText().toString();

            if (!isValidPhoneNumber(sdt)){
                Toast.makeText(this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                check = -1;
            }

            String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if (!EmailPatternMatches(email, regexEmail)){
                Toast.makeText(this, "Email không chính xác", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (pass.length() < 5){
                Toast.makeText(this, "Mật khẩu phải hơn 5 ký tự", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

    private boolean isValidPhoneNumber(String phoneNumber){
        return Patterns.PHONE.matcher(phoneNumber).matches() && phoneNumber.length() == 10;
    }

    private static boolean EmailPatternMatches(String emailAddress, String regexPattern ){
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();

    }
}