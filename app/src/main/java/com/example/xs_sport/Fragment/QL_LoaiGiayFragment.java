package com.example.xs_sport.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xs_sport.Adapter.LoaiGiay_Adapter;
import com.example.xs_sport.DAO.LoaiGiay_Dao;
import com.example.xs_sport.Model.Giay;
import com.example.xs_sport.Model.LoaiGiay;
import com.example.xs_sport.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;


public class QL_LoaiGiayFragment extends Fragment {

    TextInputLayout ip_ten_loai;
    TextInputEditText ed_ten_loai;
    ListView ls;
    FloatingActionButton fab;

    LoaiGiay_Adapter loaiGiayAdapter;
    ArrayList<Giay> list;
    LoaiGiay loaiGiay;
    LoaiGiay_Dao loaiGiayDao;
    ArrayList<LoaiGiay> listLoaiGiay;

    int a;
    int temp = 0;

    public QL_LoaiGiayFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_l__loai_giay, container, false);
        Anhxa(view);
        getData();
        return view;
    }

    //
    private void Anhxa(View view){
        ls = view.findViewById(R.id.ls_lc);
        fab = view.findViewById(R.id.flb_loaiCoffee);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = -1;
                if (listLoaiGiay.size() == 0){
                    Toast.makeText(getActivity(), "Bạn chưa thêm loại Giày", Toast.LENGTH_SHORT).show();
                }else {
                    openDialog(Gravity.CENTER);
                }
            }
        });

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                a = i;
                openDialog(Gravity.CENTER);
            }
        });
    }


    //
    private void getData(){
        loaiGiayDao = new LoaiGiay_Dao(getContext());
        listLoaiGiay = loaiGiayDao.getAllData();
        Collections.reverse(listLoaiGiay);
        loaiGiayAdapter = new LoaiGiay_Adapter(listLoaiGiay, getContext(), loaiGiayDao);
        loaiGiayAdapter.setData(listLoaiGiay);
        ls.setAdapter(loaiGiayAdapter);
    }


    //
    private void openDialog(int gravity){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_loai_giay);
        Window window = dialog.getWindow();
        if (window == null){
            return;
        }

        if (Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }else {
            dialog.setCancelable(false);
        }

        TextView lcTile = (TextView) dialog.findViewById(R.id.title_loai_coffee);

        ed_ten_loai=dialog.findViewById(R.id.ed_name_loai_coffee);
        ip_ten_loai=dialog.findViewById(R.id.input_name_loai_coffee);

        Button btnadd = dialog.findViewById(R.id.btn_dialog_add_loai_coffee);
        Button btncancel = dialog.findViewById(R.id.btn_dialog_cancle_loai_coffee);

        loaiGiayDao = new LoaiGiay_Dao(getActivity());
        loaiGiay = new LoaiGiay();
        if (a == -1){
            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validate();
                    if (temp == 0){
                        loaiGiay.setTenLoai(ed_ten_loai.getText().toString());
                        if (loaiGiayDao.insert(loaiGiay) > 0){
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            getData();
                        } else {
                            Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });


            //
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Hủy Thêm", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }
        dialog.show();
    }

    private void validate(){
        if (ed_ten_loai.getText().length() == 0){
            ip_ten_loai.setError("Tên loại giày không được để trống");
            temp++;
        }else {
            ip_ten_loai.setError("");
        }
    }
}