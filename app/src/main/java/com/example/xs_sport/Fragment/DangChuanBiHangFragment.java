package com.example.xs_sport.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xs_sport.DAO.HoaDon_Dao;
import com.example.xs_sport.LichSuDonHang_AD.DangCBH_Adapter;
import com.example.xs_sport.Model.HoaDon;
import com.example.xs_sport.R;

import java.util.ArrayList;
import java.util.Collections;


public class DangChuanBiHangFragment extends Fragment {

    private HoaDon_Dao dao;
    private ArrayList<HoaDon> list;
    private DangCBH_Adapter adapter;
    RecyclerView recyclerView;

    public DangChuanBiHangFragment(){

    }

    public static DangChuanBiHangFragment newInstance(String param1, String param2){
        DangChuanBiHangFragment fragment = new DangChuanBiHangFragment();
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
        return inflater.inflate(R.layout.fragment_dang_chuan_bi_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.invoice_ry_danghuanbi);
        HoaDon_Dao dao = new HoaDon_Dao(getContext());
        list = dao.SelectDangChuanBi();
        Collections.reverse(list);
        adapter = new DangCBH_Adapter(list, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }

    @Override
    public void onResume() {
        super.onResume();
        HoaDon_Dao dao = new HoaDon_Dao(getContext());
        list = dao.SelectDangChuanBi();
        Collections.reverse(list);
        adapter = new DangCBH_Adapter(list, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}