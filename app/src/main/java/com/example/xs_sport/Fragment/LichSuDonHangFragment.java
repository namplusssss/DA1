package com.example.xs_sport.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xs_sport.DAO.HoaDon_Dao;
import com.example.xs_sport.LichSuDonHang_AD.HoaDon_Adapter;
import com.example.xs_sport.Model.HoaDon;
import com.example.xs_sport.R;

import java.util.ArrayList;
import java.util.Collections;


public class LichSuDonHangFragment extends Fragment {

    private HoaDon_Dao dao;
    private ArrayList<HoaDon> list;
    private HoaDon_Adapter adapter;
    RecyclerView recyclerView;

    public LichSuDonHangFragment(){

    }


    public static LichSuDonHangFragment newInstance(String param1, String param2){
        LichSuDonHangFragment fragment = new LichSuDonHangFragment();
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
        return inflater.inflate(R.layout.fragment_lich_su_don_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.invoice_ry);
        HoaDon_Dao dao = new HoaDon_Dao(getContext());
        list = dao.SeLectDaDatHang();
        Collections.reverse(list);
        adapter = new HoaDon_Adapter(list, getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}