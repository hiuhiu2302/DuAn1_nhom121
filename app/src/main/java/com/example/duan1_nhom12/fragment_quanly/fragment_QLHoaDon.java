package com.example.duan1_nhom12.fragment_quanly;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1_nhom12.R;
import com.example.duan1_nhom12.adapter.HoaDonAdapter;
import com.example.duan1_nhom12.adapter.KhachHangAdapter;
import com.example.duan1_nhom12.dao.HoaDonDao;
import com.example.duan1_nhom12.dao.KhachHangDao;
import com.example.duan1_nhom12.model.HoaDonModel;
import com.example.duan1_nhom12.model.KhachHangModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class fragment_QLHoaDon extends Fragment {
    HoaDonDao dao;
    RecyclerView rc ;
    HoaDonAdapter adapter;
    ArrayList<HoaDonModel> list;



    KhachHangDao daokh;

    ArrayList<KhachHangModel> listkh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__q_l_hoa_don, container, false);
        rc= view.findViewById(R.id.rcview_hoadon);
        dao = new HoaDonDao(getContext());
        list = dao.getds();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        rc.setLayoutManager(layoutManager);
        adapter= new HoaDonAdapter(getContext(),list,dao);
        rc.setAdapter(adapter);

        FloatingActionButton flthem = view.findViewById(R.id.flthemhoadon);
        flthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




        daokh = new KhachHangDao(getContext());
        listkh = daokh.getds();
        SharedPreferences preferences = getContext().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");
        boolean shouldShowFlthem = false;
        for (KhachHangModel kh : listkh) {
            if (kh.getUsername().equals(username)) {
                shouldShowFlthem = true;
                break;
            }
        }
        if (shouldShowFlthem) {
            flthem.setVisibility(View.GONE);
        } else {
            flthem.setVisibility(View.VISIBLE);

        }


        return view;
    }
}