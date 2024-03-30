package com.example.xs_sport.LichSuDonHang_KH;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.xs_sport.Adapter.QLy_LSKH_Adapter;
import com.example.xs_sport.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LichSuDH extends AppCompatActivity {

    private TabLayout tabLayoutstatus;
    private ViewPager2 vpstatus;
    private QLy_LSKH_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_dh);

        tabLayoutstatus= findViewById(R.id.tab_layout_Hitory);
        vpstatus=findViewById(R.id.vp_Hitory);

        adapter = new QLy_LSKH_Adapter(this);

        vpstatus.setAdapter(adapter);

        new TabLayoutMediator(tabLayoutstatus, vpstatus, ((tab, position)->{
            switch (position){
                case 0:
                    tab.setText("Đã Đặt Hàng");
                    break;
                case 1:
                    tab.setText("Đang Chuẩn Bị Hàng");
                    break;
                case 2:
                    tab.setText("Đang Giao");
                    break;
                case 3:
                    tab.setText("Đã Thanh Toán");

                    break;
            }
        })).attach();
    }
}