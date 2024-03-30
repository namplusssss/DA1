package com.example.xs_sport.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.xs_sport.DAO.KhachHang_Dao;
import com.example.xs_sport.Model.KhachHang;
import com.example.xs_sport.R;
import com.google.android.material.textfield.TextInputEditText;

public class ChangePassFragment extends Fragment {

    TextInputEditText edPassOld, edPassChange, edRePassChange;
    Button btnSaveUserChange, btnCancleUserChange;

    KhachHang_Dao khdao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_change_pass, container, false);

        khdao = new KhachHang_Dao(getActivity());
        edPassOld = v.findViewById(R.id.edPassOld);
        edPassChange = v.findViewById(R.id.edPassChange);
        edRePassChange = v.findViewById(R.id.edRePassChange);
        btnSaveUserChange = v.findViewById(R.id.btnSaveUserChange);
        btnCancleUserChange  = v.findViewById(R.id.btnCancelUserChange);

        //
        btnCancleUserChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edPassOld.setText("");
                edPassChange.setText("");
                edRePassChange.setText("");
            }
        });

        //
        btnSaveUserChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME","");

                if (validate() >0 ){
                    KhachHang thuthu = khdao.getID(user);
                    thuthu.setMAT_KHAU_KH(edPassChange.getText().toString());

                    if (khdao.updatePass(thuthu) > 0){
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPassChange.setText("");
                        edRePassChange.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }

    public int validate(){
        int check = 1;
        if (edPassOld.getText().length() == 0 || edPassChange.getText().length() == 0 || edRePassChange.getText().length() == 0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD","");
            String pass = edPassChange.getText().toString();
            String rePass = edRePassChange.getText().toString();

            if (!passOld.equals(edPassOld.getText().toString())){
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}