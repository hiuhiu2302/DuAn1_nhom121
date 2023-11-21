package com.example.duan1_nhom12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.duan1_nhom12.adapter.SanPhamGH_Adapter;
import com.example.duan1_nhom12.dao.SanPhamGH_dao;
import com.example.duan1_nhom12.model.SanPhamModel;

import java.util.ArrayList;

public class GiaoDienGioHang extends AppCompatActivity {

    ArrayList<SanPhamModel>listgiohang;
    SanPhamGH_dao dao;
    SanPhamGH_Adapter adapter;
    RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_gio_hang);

        rc= findViewById(R.id.rcviewgiohang);
        dao = new SanPhamGH_dao(this);
        listgiohang = dao.getds();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        rc.setLayoutManager(layoutManager);
        adapter= new SanPhamGH_Adapter(this,listgiohang,dao);
        rc.setAdapter(adapter);


        ImageButton img = findViewById(R.id.ibtback_GH);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GiaoDienGioHang.this,Activity_khachhang.class);
                startActivity(intent);
            }
        });




    }
}