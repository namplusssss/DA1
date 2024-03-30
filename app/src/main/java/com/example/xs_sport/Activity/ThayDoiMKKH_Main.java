package com.example.xs_sport.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.xs_sport.DAO.KhachHang_Dao;
import com.example.xs_sport.R;

public class ThayDoiMKKH_Main extends AppCompatActivity {

    EditText edPassOld,edPassChange,edRePassChange;
    Button btnSaveUserChange,btnCancelUserChange;
    KhachHang_Dao khachHang_dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_mkkh_main);

        edPassChange = findViewById(R.id.edPassChange);
        edPassOld = findViewById(R.id.edPassOld);
        edRePassChange = findViewById(R.id.edRePassChange);
        btnSaveUserChange = findViewById(R.id.btnSaveUserChange);
        btnCancelUserChange = findViewById(R.id.btnCancelUserChange);

        khachHang_dao = new KhachHang_Dao(this);

        btnCancelUserChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnSaveUserChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mkCu = edPassOld.getText().toString();
                String mkMoi = edPassChange.getText().toString();
                String mkNhaplai = edRePassChange.getText().toString();

                if (mkCu.isEmpty() || mkMoi.isEmpty() || mkNhaplai.isEmpty()){
                    Toast.makeText(ThayDoiMKKH_Main.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mkMoi.equals(mkNhaplai)){
                    if (khachHang_dao.checkPasswordAndChange(mkCu, mkMoi)){
                        Toast.makeText(ThayDoiMKKH_Main.this, "Đổi thành công", Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPassChange.setText("");
                        edRePassChange.setText("");
                        edPassOld.setFocusable(false);
                        edPassChange.setFocusable(false);
                        edRePassChange.setFocusable(false);
                        finish();

                    } else {
                        Toast.makeText(ThayDoiMKKH_Main.this, "Đổi thất bại", Toast.LENGTH_SHORT).show();
                    }


                }else {
                    Toast.makeText(ThayDoiMKKH_Main.this, "Mật khẩu nhập lại sai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}