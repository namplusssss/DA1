package com.example.xs_sport.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.xs_sport.Fragment.DaThanhToanFragment;
import com.example.xs_sport.Fragment.DangChuanBiHangFragment;
import com.example.xs_sport.Fragment.DangGiaoFragment;
import com.example.xs_sport.Fragment.LichSuDonHangFragment;
import com.example.xs_sport.LichSuDonHang_KH.LichSuKH_Fragment;

public class TrangThai_Adapter extends FragmentStateAdapter {
    public TrangThai_Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new LichSuDonHangFragment();
            case 1:
                return new DangChuanBiHangFragment();
            case 2:
                return new DangGiaoFragment();
            case 3:
                return new DaThanhToanFragment();

            default:
                return null;
        }


    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
