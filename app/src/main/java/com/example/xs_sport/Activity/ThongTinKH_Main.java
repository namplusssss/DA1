package com.example.xs_sport.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.xs_sport.DAO.KhachHang_Dao;
import com.example.xs_sport.DangNhap;
import com.example.xs_sport.Model.KhachHang;
import com.example.xs_sport.R;

public class ThongTinKH_Main extends AppCompatActivity {

    TextView txtusername,txtphone,txtemail;

    ImageView imgAvatar;

    KhachHang_Dao khachHangDao;

    SharedPreferences sharedPreferences;

    String usedId;

    Button btn_suaCanhan,btn_quaylaiCaNhan;

    KhachHang khachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_kh_main);
        txtusername = findViewById(R.id.txt_username);
        txtphone = findViewById(R.id.txt_phone);
        txtemail = findViewById(R.id.txt_email);
        btn_suaCanhan = findViewById(R.id.btn_suaCanhan);
        btn_quaylaiCaNhan = findViewById(R.id.btn_quaylaiCanhan);
        imgAvatar = findViewById(R.id.img_avatar);

        khachHangDao = new KhachHang_Dao(this);
        sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String tendNhap = sharedPreferences.getString("TEN", "");
        String mkdNhap = sharedPreferences.getString("MATKHAU", "");

        usedId = sharedPreferences.getString("USER_ID", "");
        KhachHang khachHang = khachHangDao.LayThongTinKH(tendNhap,mkdNhap);

        txtusername.setText("Họ Tên : " + khachHang.getTEN_KH());
        txtphone.setText("Số điện thoại: "+ khachHang.getSDT());
        txtemail.setText("Email : " + khachHang.getEMAIL_KH());


        String avatarPath = sharedPreferences.getString("AVATAR_PATH","");
        if (!avatarPath.isEmpty()){
            Glide.with(this).load(Uri.parse(avatarPath)).into(imgAvatar);
        }

        btn_suaCanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDialog(khachHang);
            }
        });

        btn_quaylaiCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void changeAvatar(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && requestCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();

            if (selectedImage != null){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("AVATAR_PATH", selectedImage.toString());
                editor.apply();
                Toast.makeText(this, "Thêm ảnh thành công !!!", Toast.LENGTH_SHORT).show();
                Glide.with(this).load(selectedImage).into(imgAvatar);
            }else {
                Toast.makeText(this, "Không thể lấy ảnh, vui lòng thử lại.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void switchAccount(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("AVATAR_PATH");
        editor.apply();

        Intent intent = new Intent(this, DangNhap.class);
        startActivity(intent);
        finish();
    }

    public void updateDialog(KhachHang khachHang){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_thonngtinkh, null);
        builder.setView(view);
        final Dialog dialog = builder.create();
        dialog.show();

        EditText edtTenKH, edt_sdt, edtEmail;
        Button btnLuuthongtin, btnhuy;

        edt_sdt = view.findViewById(R.id.edtSoDienThoai);
        edtEmail = view.findViewById(R.id.edtEmail);

        btnLuuthongtin = view.findViewById(R.id.btnLuuThongTin);
        btnhuy =view.findViewById(R.id.btnHuy);


        edt_sdt.setText(khachHang.getSDT());
        edtEmail.setText(khachHang.getEMAIL_KH());

        btnLuuthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailKH = edtEmail.getText().toString().trim();
                String sdtKH = edt_sdt.getText().toString().trim();

                if (sdtKH.isEmpty() || emailKH.isEmpty()){
                    Toast.makeText(ThongTinKH_Main.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                khachHang.setEMAIL_KH(emailKH);
                khachHang.setSDT(sdtKH);

                if (khachHangDao.UpdateThongTinKH(khachHang) > 0){
                    dialog.dismiss();
                    Toast.makeText(ThongTinKH_Main.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                    txtemail.setText(emailKH);
                    txtphone.setText(sdtKH);
                }else {
                    Toast.makeText(ThongTinKH_Main.this, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}