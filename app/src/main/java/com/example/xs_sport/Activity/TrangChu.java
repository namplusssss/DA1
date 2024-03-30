package com.example.xs_sport.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xs_sport.Adapter.Giay_Adapter;
import com.example.xs_sport.DAO.Giay_Dao;
import com.example.xs_sport.Model.Giay;
import com.example.xs_sport.Model.GioHang;
import com.example.xs_sport.R;

import java.util.ArrayList;

public class TrangChu extends AppCompatActivity {

    RecyclerView recyclerView;
    Giay_Dao giayDao;
    ArrayList<Giay> list;
    Giay_Adapter giayAdapter;
    SearchView searchView;

    boolean isSearching = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        recyclerView = findViewById(R.id.view1);
        initRecyclerview();
        bottom_navigation();

        ImageButton searchButton = findViewById(R.id.btn_Trangchu_tapSearch);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSearch();
            }
        });
    }

    private void performSearch(){
        if (!isSearching){
            isSearching = true;
            String searchQuery = getSearchQueryFromSearchView();
            if (searchQuery != null && !searchQuery.isEmpty()){
                list.clear();
                list.addAll(giayDao.search(searchQuery));
                giayAdapter.notifyDataSetChanged();
            }

        }else {
            isSearching = false;
            list.clear();
            list.addAll(giayDao.getAllData());
            giayAdapter.notifyDataSetChanged();
        }
    }

    private String getSearchQueryFromSearchView(){
        EditText searchView = findViewById(R.id.ed_Trangchu_search);

        if (searchView != null) {
            return searchView.getText().toString();
        }
        return null;
    }

    private void bottom_navigation(){
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout btnCaNhan = findViewById(R.id.btnCaNhan);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChu.this, TrangChu.class));
            }
        });


        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChu.this, GioHang_Main.class));
            }
        });

        btnCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChu.this, TrangCaNhan_Main.class));
            }
        });

    }

    private void initRecyclerview(){
        giayDao = new Giay_Dao(this);
        list= giayDao.getAllData();
        giayAdapter = new Giay_Adapter(this,list, giayDao);
        giayAdapter.setData(list);
        recyclerView.setAdapter(giayAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    }
}