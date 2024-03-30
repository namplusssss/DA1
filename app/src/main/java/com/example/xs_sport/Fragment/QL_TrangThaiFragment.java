package com.example.xs_sport.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xs_sport.Adapter.TrangThai_Adapter;
import com.example.xs_sport.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class QL_TrangThaiFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 vpstatus;
    private TrangThai_Adapter quanLy_trangThai_adapter;

    public QL_TrangThaiFragment(){

    }

    public static QL_TrangThaiFragment newInstance(String param1, String param2){
        QL_TrangThaiFragment fragment = new QL_TrangThaiFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_q_l__trang_thai, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout= view.findViewById(R.id.tab_layout_status);
        vpstatus=view.findViewById(R.id.vp_status);
        quanLy_trangThai_adapter = new TrangThai_Adapter(getActivity());
        vpstatus.setAdapter(quanLy_trangThai_adapter);
        new TabLayoutMediator(tabLayout, vpstatus, ((tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Đã đặt hàng");
                    break;
                case 1:
                    tab.setText("Đang chuẩn bị hàng");
                    break;
                case 2:
                    tab.setText("Đang giao");
                    break;
                case 3:
                    tab.setText("Đã thanh toán");
                    break;
            }
        })).attach();
    }
}