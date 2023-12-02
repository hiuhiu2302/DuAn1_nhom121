package com.example.duan1_nhom12.fragment_quanly;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.duan1_nhom12.R;
import com.example.duan1_nhom12.adapter.SanPhamAdapter;
import com.example.duan1_nhom12.adapter.SanPhamAdapter_QL;
import com.example.duan1_nhom12.dao.SanPhamDAO;
import com.example.duan1_nhom12.model.SanPhamModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class fragment_QLSanPham extends Fragment {

    SanPhamDAO dao;
    RecyclerView rc ;
    SanPhamAdapter_QL adapter;
    ArrayList<SanPhamModel> list;

    SanPhamModel sp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__q_l_san_pham, container, false);

        rc= view.findViewById(R.id.rcviewQLsp);
        dao = new SanPhamDAO(getContext());
        list = dao.getds();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        rc.setLayoutManager(layoutManager);
        adapter= new SanPhamAdapter_QL(getContext(),list,dao); 
        rc.setAdapter(adapter);


        FloatingActionButton fladd=view.findViewById(R.id.flthemsanpham);
        SanPhamModel sp = new SanPhamModel();
        fladd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog1(sp);
                adapter.notifyDataSetChanged();

            }
        });
        return view;
    }


    public void showDialog1(SanPhamModel sp ){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity)getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_sp,null);
        builder.setView(view);
        AlertDialog alertDialog= builder.create();
        alertDialog.show();

        EditText edttensp,edtgiasp,edtmacc,edtmota;
        edttensp= view.findViewById(R.id.edttensp_sua);
        edtgiasp= view.findViewById(R.id.edtgiasp_sua);
        edtmacc= view.findViewById(R.id.edtnhaccsp_sua);
        edtmota= view.findViewById(R.id.edtmotasp_sua);

        RadioButton rdodt,rdolaptop,rdotainghe,rdogame;
        rdodt = view.findViewById(R.id.rdo_dt_sua);
        rdolaptop = view.findViewById(R.id.rdo_laptop_sua);
        rdotainghe = view.findViewById(R.id.rdo_tainghe_sua);
        rdogame = view.findViewById(R.id.rdo_game_sua);

        Button btncn , btnhuy;
        btncn= view.findViewById(R.id.btnsuasp_sua);
        btnhuy= view.findViewById(R.id.btnhuy_sua);


        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btncn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loai="dien thoai";
                if(rdodt.isChecked()){
                    loai="dien thoai";
                }
                if(rdolaptop.isChecked()){
                    loai="laptop";
                }
                if(rdotainghe.isChecked()){
                    loai="tainghe";
                }
                if(rdogame.isChecked()){
                    loai="game";
                }

                String tensp = edttensp.getText().toString();
                int giasp = Integer.parseInt(edtgiasp.getText().toString());
                String mota =edtmota.getText().toString();
                int manhacc= Integer.parseInt(edtmacc.getText().toString());

                boolean check = dao.themVaoGioHang(tensp,giasp,loai,mota,manhacc);
                if(check){
                    Toast.makeText(getContext(), "Cập nhập thành công", Toast.LENGTH_SHORT).show();


                    list = dao.getds();
                    GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
                    rc.setLayoutManager(layoutManager);
                    adapter= new SanPhamAdapter_QL(getContext(),list,dao);
                    rc.setAdapter(adapter);

                    alertDialog.dismiss();
                    adapter.notifyDataSetChanged();

                }else {
                    Toast.makeText(getContext(), "Cập nhập thất bại", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }

}