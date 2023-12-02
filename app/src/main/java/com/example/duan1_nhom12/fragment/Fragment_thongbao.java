package com.example.duan1_nhom12.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1_nhom12.R;
import com.example.duan1_nhom12.adapter.SanPhamAdapter;
import com.example.duan1_nhom12.adapter.ThongBaoAdapter;
import com.example.duan1_nhom12.dao.SanPhamDAO;
import com.example.duan1_nhom12.dao.ThongBaoDao;
import com.example.duan1_nhom12.model.SanPhamModel;
import com.example.duan1_nhom12.model.ThongBaoModel;

import java.util.ArrayList;


public class Fragment_thongbao extends Fragment {

    RecyclerView rc ;
    ThongBaoAdapter adapter;
    ArrayList<ThongBaoModel> list;
    ThongBaoDao dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_thongbao, container, false);

        rc= view.findViewById(R.id.rcthongbao);
        dao = new ThongBaoDao(getContext());
        list = dao.getds();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        rc.setLayoutManager(layoutManager);
        adapter= new ThongBaoAdapter(getContext(),list,dao);
        rc.setAdapter(adapter);






        return view;
    }
}