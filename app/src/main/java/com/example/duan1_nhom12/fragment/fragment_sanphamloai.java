package com.example.duan1_nhom12.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.duan1_nhom12.R;
import com.example.duan1_nhom12.adapter.SanPhamAdapter;
import com.example.duan1_nhom12.adapter.SanPhamAdapter_QL;
import com.example.duan1_nhom12.dao.SanPhamDAO;
import com.example.duan1_nhom12.model.SanPhamModel;

import java.util.ArrayList;

public class fragment_sanphamloai extends AppCompatActivity {
    SanPhamDAO dao;
    RecyclerView rc ;
    SanPhamAdapter adapter;
    ArrayList<SanPhamModel> list;
    ArrayList<SanPhamModel>listphanloai= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_sanphamloai);

        ImageButton imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView txtnav = findViewById(R.id.txtnav);
        Intent intent= getIntent();
        String loai=intent.getStringExtra("loai");
        if(loai.equals("dien thoai")){
            txtnav.setText("Sản phẩm điện thoại");
        }
        if(loai.equals("laptop")){
            txtnav.setText("Sản phẩm laptop");
        }
        if(loai.equals("tainghe")){
            txtnav.setText("Sản phẩm tai nghe");
        }
        if(loai.equals("game")){
            txtnav.setText("Sản phẩm game");
        }
        rc= findViewById(R.id.rcsanphamloai);
        dao = new SanPhamDAO(this);
        list = dao.getds();
        for(SanPhamModel sp :list){
            if(sp.getLoai().equals(loai)){
                listphanloai.add(sp);
            }

        }
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        rc.setLayoutManager(layoutManager);
        adapter= new SanPhamAdapter(this,listphanloai,dao);
        rc.setAdapter(adapter);

    }
}