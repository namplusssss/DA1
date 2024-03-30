package com.example.xs_sport.LichSuDonHang_KH;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xs_sport.DAO.HoaDonKH_Dao;
import com.example.xs_sport.Model.HoaHon_KH;
import com.example.xs_sport.R;

import java.util.ArrayList;
import java.util.Collections;

public class DaThanhToanKH_Fragment extends Fragment {

    private HoaDonKH_Dao dao;
    private ArrayList<HoaHon_KH> list;
    private HoaDonKH_Adapter adapter;
    RecyclerView recyclerView;

    public DaThanhToanKH_Fragment(){

    }

    public static DaThanhToanKH_Fragment newInstance(String param1, String param2){
        DaThanhToanKH_Fragment fragment = new DaThanhToanKH_Fragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_da_thanh_toan_k_h_, container, false);
        recyclerView = view.findViewById(R.id.history_DaThanhToan);
        reloadData();
        return view;
    }

    public void reloadData(){
        dao = new HoaDonKH_Dao(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String loggedInUserName = sharedPreferences.getString("TEN", "");
        list = dao.SelectUserDaThanhToan(loggedInUserName);
        Collections.reverse(list);
        adapter = new HoaDonKH_Adapter(list, getContext(), dao);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }
}