package com.example.xs_sport.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.xs_sport.LichSuDonHang_KH.DaThanhToanKH_Fragment;
import com.example.xs_sport.LichSuDonHang_KH.DangCBHangKH_Fragment;
import com.example.xs_sport.LichSuDonHang_KH.DangGiaoHangKH_Fragment;
import com.example.xs_sport.LichSuDonHang_KH.LichSuKH_Fragment;

public class QLy_LSKH_Adapter extends FragmentStateAdapter {


    public QLy_LSKH_Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new LichSuKH_Fragment();
            case 1:
                return new DangCBHangKH_Fragment();
            case 2:
                return new DangGiaoHangKH_Fragment();
            case 3:
                return new DaThanhToanKH_Fragment();
            default:
                return null;

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
