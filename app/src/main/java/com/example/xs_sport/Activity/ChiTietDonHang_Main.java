package com.example.xs_sport.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.xs_sport.R;

public class ChiTietDonHang_Main extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang_main);


        int ma_giohang = getIntent().getIntExtra("ma_giohang", 0);
        int sdt = getIntent().getIntExtra("sdt", 0);
        String name = getIntent().getStringExtra("name");
        String diachi = getIntent().getStringExtra("diachi");
        String thoigian = getIntent().getStringExtra("thoigian");
        String noidung = getIntent().getStringExtra("noidung");
        String trangthai = getIntent().getStringExtra("trangthai");
        double tong = getIntent().getDoubleExtra("tong", 0);

        TextView tv_cart = findViewById(R.id.infor_id_cart);
        TextView tv_phone = findViewById(R.id.infor_id_phone);
        TextView tv_name = findViewById(R.id.infor_id_hoten);
        TextView tv_address = findViewById(R.id.infor_id_address);
        TextView tv_Time = findViewById(R.id.infor_id_time);
        TextView tv_content = findViewById(R.id.infor_id_noidung);
        TextView tv_status = findViewById(R.id.info_status);
        TextView tv_sum = findViewById(R.id.infor_id_sum);
        Button back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_cart.setText(String.valueOf(ma_giohang));
        tv_phone.setText(String.valueOf(sdt));
        tv_name.setText(name);
        tv_address.setText(diachi);
        tv_Time.setText(thoigian);
        tv_content.setText(noidung);
        tv_status.setText(trangthai);
        tv_sum.setText(String.valueOf(tong));
    }
}