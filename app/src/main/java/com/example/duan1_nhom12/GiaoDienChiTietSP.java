package com.example.duan1_nhom12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom12.adapter.SanPhamGH_Adapter;
import com.example.duan1_nhom12.dao.SanPhamDAO;
import com.example.duan1_nhom12.dao.SanPhamGH_dao;
import com.example.duan1_nhom12.dao.SanPhamYT_Dao;
import com.example.duan1_nhom12.model.SanPhamModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class GiaoDienChiTietSP extends AppCompatActivity {
ArrayList<SanPhamModel> list;
    SanPhamGH_dao dao1 = new SanPhamGH_dao(this);
    SanPhamYT_Dao daoYT = new SanPhamYT_Dao(this);


SanPhamDAO dao;
SanPhamGH_Adapter adapter;
ArrayList<SanPhamModel> listgiohang;
ArrayList<SanPhamModel> listyeuthich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_chi_tiet_sp);
        adapter= new SanPhamGH_Adapter(this,listgiohang,dao1);

        dao = new SanPhamDAO(this);

        list=dao.getds();


        ImageButton imgbtTrove = findViewById(R.id.imgbttv);
        imgbtTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GiaoDienChiTietSP.this, Activity_khachhang.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String masp = intent.getStringExtra("masp");
        String ten = intent.getStringExtra("ten");
        String gia = intent.getStringExtra("gia");
        String loai = intent.getStringExtra("loai");
        String mota = intent.getStringExtra("mota");
        String manhacc= intent.getStringExtra("manhacc");

        // Hiển thị dữ liệu trong Activity
        TextView tenTextView = findViewById(R.id.txttensp_ct);
        tenTextView.setText(mota);

        TextView giaLoaiTextView = findViewById(R.id.txtgiasp_ct);
        giaLoaiTextView.setText("đ "+gia);




        ImageView img = findViewById(R.id.imgsanpham_chitiet);
        if (loai.equals("dien thoai")) {
            Picasso.get().load(R.drawable.img_phone).resize(420, 340).centerCrop().into(img);
        } else if (loai.equals("laptop")) {
            Picasso.get().load(R.drawable.img_laptop).resize(420, 340).centerCrop().into(img);
        }
        else if (loai.equals("tainghe")) {
            Picasso.get().load(R.drawable.img_tainghe).resize(420, 340).into(img);
        } else if (loai.equals("game")) {
            Picasso.get().load(R.drawable.img_game).resize(420, 340).into(img);
        }

        ImageButton imgbt_giohang= findViewById(R.id.imgbt_giohang_ct);

        imgbt_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listgiohang = dao1.getds();
//
        boolean check =dao1.themVaoGioHang(ten,Integer.parseInt(gia), loai);

        if(check){
            Toast.makeText(GiaoDienChiTietSP.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(GiaoDienChiTietSP.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();

        }
            }
        });


        ImageView imgyeuthich=  findViewById(R.id.imgbt_yeuthich_ct);
        final boolean[] isRed = {false};




        imgyeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isRed[0]) {
                    Picasso.get().load(R.drawable.img_love).resize(40, 40).into(imgyeuthich);






                } else {
                    Picasso.get().load(R.drawable.img_love_red).resize(40, 40).into(imgyeuthich);



                    boolean check =daoYT.themVaoYeuthich(Integer.parseInt(masp),ten,Integer.parseInt(gia), loai);

                    if(check){
                        Toast.makeText(GiaoDienChiTietSP.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

                        adapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(GiaoDienChiTietSP.this, "Sản phẩm đã có trong danh sách yêu thích", Toast.LENGTH_SHORT).show();

                    }
                }
                isRed[0] = !isRed[0];
            }
        });


        Intent intent1 = getIntent();

        listyeuthich=daoYT.getds();
        SanPhamModel sp = new SanPhamModel(Integer.valueOf(masp),"",0,"","",0);

        if(listyeuthich.contains(sp.getMasp())){
            Picasso.get().load(R.drawable.img_love_red).resize(40, 40).into(imgyeuthich);

        }



    }
}