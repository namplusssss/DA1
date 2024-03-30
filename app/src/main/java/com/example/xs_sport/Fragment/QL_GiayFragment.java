package com.example.xs_sport.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.xs_sport.Adapter.GiayAD_Adapter;
import com.example.xs_sport.DAO.GiayAD_Dao;
import com.example.xs_sport.DAO.LoaiGiay_Dao;
import com.example.xs_sport.Model.Giay_AD;
import com.example.xs_sport.Model.LoaiGiay;
import com.example.xs_sport.R;

import java.util.ArrayList;
import java.util.Collections;

public class QL_GiayFragment extends Fragment {

    GiayAD_Dao giayDao;
    RecyclerView recyclerGiay;
    SearchView searchView;
    GiayAD_Adapter adapter;
    ArrayList<LoaiGiay> llist;
    ArrayList<Giay_AD> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_q_l__giay, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerGiay = view.findViewById(R.id.recyclerCoffee1);
        ImageButton img_tapSearch = view.findViewById(R.id.btn_fragment_listFood_tapSearch_ad);
        EditText edSearch = view.findViewById(R.id.ed_fragment_listFood_search_ad);
        ImageButton imgAdd = view.findViewById(R.id.floadAdd);

        adapter = new GiayAD_Adapter(getContext(), list, giayDao);

        //
        img_tapSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edSearch.length() > 0){
                    String searchName = edSearch.getText().toString().trim();
                    LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
                    recyclerGiay.setLayoutManager(layoutManager);
                    GiayAD_Dao giayAD_dao = new GiayAD_Dao(getContext());
                    list = new ArrayList<>();
                    list = giayAD_dao.search(searchName);
                    adapter.setData(list);
                    recyclerGiay.setAdapter(adapter);
                } else {
                    reloadData();
                }
            }
        });


        //
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                LoaiGiay_Dao loaiGiay_dao = new LoaiGiay_Dao(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_coffee);
                Giay_AD giay = new Giay_AD();
                EditText ed_listfood_img, ed_listfood_name, ed_listfood_price, ed_listfood_des, ed_listfood_size;
                Spinner spn = dialog.findViewById(R.id.spn_dialog_listfood_add_type);
                Button btnDialogAddSubmit;
                ed_listfood_img = dialog.findViewById(R.id.edt_dialog_listfood_add_img);
                ed_listfood_name = dialog.findViewById(R.id.edt_dialog_listfood_add_name);
                ed_listfood_price = dialog.findViewById(R.id.edt_dialog_listfood_add_price);
                ed_listfood_des = dialog.findViewById(R.id.edt_dialog_listfood_add_des);
                ed_listfood_size = dialog.findViewById(R.id.edt_dialog_listfood_add_size);

                btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_listfood_add_add);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, LoaiGiay_Dao.name());
                spn.setAdapter(adapter1);

                //
                spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                        llist = LoaiGiay_Dao.getAllData();
                        giay.setLoaiGiay_tenGiay(llist.get(position).getTenLoai());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                //
                btnDialogAddSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String img = ed_listfood_img.getText().toString();
                        String name = ed_listfood_name.getText().toString();
                        String priceString = ed_listfood_price.getText().toString();
                        String des = ed_listfood_des.getText().toString();
                        String size = ed_listfood_size.getText().toString();

                        if (img.trim().isEmpty() || name.trim().isEmpty() || size.trim().isEmpty()) {
                            Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else if (priceString.trim().isEmpty()) {
                            Toast.makeText(getContext(), "Vui lòng nhập giá tiền", Toast.LENGTH_SHORT).show();
                        } else {
                            int price = 0;
                            try {
                                price = Integer.parseInt(priceString);
                            } catch (NumberFormatException e) {
                                Toast.makeText(getContext(), "Giá tiền phải là một số", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            giay.setAnh_giay(img);
                            giay.setTen_giay(name);
                            giay.setGia_giay(price);
                            giay.setMota_giay(des);
                            giay.setSize(size);

                            if (giayDao.insert(giay) >= 0) {
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                                list = giayDao.getAllData();
                                adapter.setData(list);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getContext(), "Thêm thất bại!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });
        reloadData();
    }

    private void reloadData(){
        giayDao = new GiayAD_Dao(getContext());
        list = giayDao.getAllData();
        Collections.reverse(list);
        adapter = new GiayAD_Adapter(getContext(),list,giayDao);
        adapter.setData(list);
        recyclerGiay.setAdapter(adapter);
        recyclerGiay.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }
}