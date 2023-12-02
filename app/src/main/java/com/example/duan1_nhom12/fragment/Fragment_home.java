package com.example.duan1_nhom12.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.duan1_nhom12.GiaoDienGioHang;
import com.example.duan1_nhom12.R;
import com.example.duan1_nhom12.adapter.SanPhamAdapter;
import com.example.duan1_nhom12.dao.SanPhamDAO;
import com.example.duan1_nhom12.model.SanPhamModel;

import java.util.ArrayList;

public class Fragment_home extends Fragment {

SanPhamDAO dao;
RecyclerView rc ;
SanPhamAdapter adapter;
ArrayList<SanPhamModel> list;

SanPhamModel sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
//
        ImageSlider imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels= new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.img_slideqc, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_slideqc1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_slideqc2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_slideqc3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_slideqc4, ScaleTypes.FIT));


        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
//

       rc= view.findViewById(R.id.rcsanphamhome);
       dao = new SanPhamDAO(getContext());
       list = dao.getds();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        rc.setLayoutManager(layoutManager);
        adapter= new SanPhamAdapter(getContext(),list,dao);
        rc.setAdapter(adapter);


        ImageButton imgbt = view.findViewById(R.id.ibtGioHang);
        imgbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), GiaoDienGioHang.class);
                startActivity(intent);
            }
        });


        ImageButton imgdt= view.findViewById(R.id.ibtdt);
        imgdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),fragment_sanphamloai.class);
                intent.putExtra("loai","dien thoai");
                startActivity(intent);

            }
        });

        ImageButton imglaptop= view.findViewById(R.id.ibtlaptop);
        imglaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),fragment_sanphamloai.class);
                intent.putExtra("loai","laptop");
                startActivity(intent);

            }
        });

        ImageButton imgtainghe= view.findViewById(R.id.ibttainghe);
        imgtainghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),fragment_sanphamloai.class);
                intent.putExtra("loai","tainghe");
                startActivity(intent);

            }
        });

        ImageButton imggame= view.findViewById(R.id.ibtgame);
        imggame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),fragment_sanphamloai.class);
                intent.putExtra("loai","game");
                startActivity(intent);

            }
        });


        return view;
    }


}