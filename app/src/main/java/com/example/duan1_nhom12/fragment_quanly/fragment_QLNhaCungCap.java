package com.example.duan1_nhom12.fragment_quanly;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1_nhom12.R;
import com.example.duan1_nhom12.adapter.KhachHangAdapter;
import com.example.duan1_nhom12.adapter.NhaccAdapter;
import com.example.duan1_nhom12.dao.KhachHangDao;
import com.example.duan1_nhom12.dao.NhaccDao;
import com.example.duan1_nhom12.model.KhachHangModel;
import com.example.duan1_nhom12.model.NhaCungCapModel;

import java.util.ArrayList;

public class fragment_QLNhaCungCap extends Fragment {
    NhaccDao dao;
    RecyclerView rc ;
    NhaccAdapter adapter;
    ArrayList<NhaCungCapModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__q_l_nha_cung_cap, container, false);
        rc= view.findViewById(R.id.rcnhacc);
        dao = new NhaccDao(getContext());
        list = dao.getds();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        rc.setLayoutManager(layoutManager);
        adapter= new NhaccAdapter(getContext(),list,dao);
        rc.setAdapter(adapter);
        return view;
    }
}