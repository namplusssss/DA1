package com.example.xs_sport;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xs_sport.Adapter.BinhLuan_Adapter;
import com.example.xs_sport.DAO.BinhLuan_Dao;
import com.example.xs_sport.DAO.GioHang_Dao;
import com.example.xs_sport.Model.BinhLuan;
import com.example.xs_sport.Model.Giay;
import com.example.xs_sport.Model.GioHang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChiTietGiay extends AppCompatActivity {

    Button btn_add;
    RecyclerView recyclerView;
    TextView count_cmt;
    BinhLuan_Adapter binhLuan_adapter;
    BinhLuan_Dao binhLuan_dao;
    ArrayList<BinhLuan> list;

    Giay giay;
    TextView tv_avgRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_giay);

        int id_Giay = getIntent().getIntExtra("ma_giay", 0);
        String dataImage = getIntent().getStringExtra("anh_giay");
        String dataname = getIntent().getStringExtra("ten_giay");
        String dataContent = getIntent().getStringExtra("mota_giay");
        int datapirce = getIntent().getIntExtra("gia_giay", 0);
        String size = getIntent().getStringExtra("size");
        String type = getIntent().getStringExtra("loaiGiay_tenGiay");


        tv_avgRating = findViewById(R.id.AVG_rating);
        ImageButton btn_back = findViewById(R.id.btn_infor_food_back);
        btn_add = findViewById(R.id.btn_add_cart);
        recyclerView = findViewById(R.id.recy_comment);
        count_cmt = findViewById(R.id.tv_count_comment);
        binhLuan_dao = new BinhLuan_Dao(getApplicationContext());
        list = binhLuan_dao.getByGiayId(String.valueOf(id_Giay));

        binhLuan_adapter = new BinhLuan_Adapter(ChiTietGiay.this, list);

        recyclerView.setAdapter(binhLuan_adapter);
        count_cmt.setText("("+ binhLuan_dao.countCmt(String.valueOf(id_Giay)) + ")");
        float rating_avg = binhLuan_dao.getAVG(String.valueOf(id_Giay));
        tv_avgRating.setText(String.format("%.1f",rating_avg)+"/5");

        ImageView iv_image = findViewById(R.id.iv_infor_food_img);
        TextView tv_name = findViewById(R.id.tv_infor_food_name);
        TextView tv_content = findViewById(R.id.tv_infor_food_content);
        TextView tv_price = findViewById(R.id.tv_infor_food_price);
        TextView tv_size = findViewById(R.id.tv_infor_food_size);
        TextView tv_type = findViewById(R.id.tv_infor_food_type);

        Picasso.get().load(dataImage).into(iv_image);
        tv_name.setText(dataname);
        tv_content.setText(dataContent);
        tv_price.setText(String.valueOf(datapirce)+" VND");
        tv_size.setText("Size: "+size);
        tv_type.setText("Loại: "+type);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GioHang_Dao gioHang_dao = new GioHang_Dao(getApplication());

                GioHang gioHang = new GioHang();

                gioHang.setMa_giay(id_Giay);
                gioHang.setSoLuong(1);
                gioHang.setTong(datapirce);

                SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String usernameLogged = sharedPreferences.getString("USERNAME", "");

                gioHang.setUsername(usernameLogged);

                if (!gioHang_dao.GiayExists(gioHang.getMa_giay(), gioHang.getUsername())){
                    if (gioHang_dao.insert(gioHang) > 0){
                        // Thông báo thành công và có thể thực hiện các hành động khác sau khi thêm vào giỏ hàng
                        Toast.makeText(ChiTietGiay.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();

                    } else {
                        // Thông báo thất bại nếu không thể thêm vào cơ sở dữ liệu
                        Toast.makeText(ChiTietGiay.this, "Không thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    // Thông báo nếu sản phẩm đã được chọn trước đó
                    Toast.makeText(ChiTietGiay.this, "Sản phẩm đã được chọn", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}