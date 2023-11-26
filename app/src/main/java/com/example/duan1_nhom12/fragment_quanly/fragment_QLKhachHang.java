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
import com.example.duan1_nhom12.adapter.SanPhamAdapter_QL;
import com.example.duan1_nhom12.dao.KhachHangDao;
import com.example.duan1_nhom12.dao.SanPhamDAO;
import com.example.duan1_nhom12.model.KhachHangModel;
import com.example.duan1_nhom12.model.SanPhamModel;

import java.util.ArrayList;


public class fragment_QLKhachHang extends Fragment {

    KhachHangDao dao;
    RecyclerView rc ;
    KhachHangAdapter adapter;
    ArrayList<KhachHangModel> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment__q_l_khach_hang, container, false);

        rc= view.findViewById(R.id.rckhachhang);
        dao = new KhachHangDao(getContext());
        list = dao.getds();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        rc.setLayoutManager(layoutManager);
        adapter= new KhachHangAdapter(getContext(),list,dao);
        rc.setAdapter(adapter);

        return view;

    }
}