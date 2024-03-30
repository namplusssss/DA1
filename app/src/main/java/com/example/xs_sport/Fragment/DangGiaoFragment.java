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
import com.example.xs_sport.LichSuDonHang_AD.DangGiao_Adapter;
import com.example.xs_sport.Model.HoaDon;
import com.example.xs_sport.R;

import java.util.ArrayList;
import java.util.Collections;


public class DangGiaoFragment extends Fragment {

    private HoaDon_Dao dao;
    private ArrayList<HoaDon> list;
    private DangGiao_Adapter adapter;
    RecyclerView recyclerView;

    public DangGiaoFragment(){

    }


    public static DangGiaoFragment newInstance(String param1, String param2){
        DangGiaoFragment fragment = new DangGiaoFragment();
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
        return inflater.inflate(R.layout.fragment_dang_giao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.invoice_ry_dang_giao);
        HoaDon_Dao dao = new HoaDon_Dao(getContext());
        list = dao.SeLectDangGiao();
        Collections.reverse(list);
        adapter = new DangGiao_Adapter(list,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    public void onResume(){
        super.onResume();

        HoaDon_Dao dao = new HoaDon_Dao(getContext());
        list = dao.SeLectDangGiao();
        Collections.reverse(list);
        adapter = new DangGiao_Adapter(list, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}