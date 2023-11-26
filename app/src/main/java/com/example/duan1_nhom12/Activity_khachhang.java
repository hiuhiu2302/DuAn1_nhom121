package com.example.duan1_nhom12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.duan1_nhom12.adapter.SanPhamAdapter;
import com.example.duan1_nhom12.fragment.Fragment_home;
import com.example.duan1_nhom12.fragment.Fragment_thongbao;
import com.example.duan1_nhom12.fragment.Fragment_thongtin;
import com.example.duan1_nhom12.fragment.Fragment_yeuthich;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Activity_khachhang extends AppCompatActivity {
    private MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khachhang);


        bottomNavigation= findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.icon_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.icon_yeuthich));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.icon_thongbao));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.icon_thongtin));


        bottomNavigation.show(1,true);

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES


                switch (model.getId()){

                    case 1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout, new Fragment_home())
                                .commit();

                        Intent intent= getIntent();
                        String dntk = intent.getStringExtra("dntk");
                        Toast.makeText(Activity_khachhang.this, ""+dntk, Toast.LENGTH_SHORT).show();

                        break;

                    case 2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout, new Fragment_yeuthich())
                                .commit();
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout, new Fragment_thongbao())
                                .commit();
                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout, new Fragment_thongtin())
                                .commit();
                        break;
                }

                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES



                return null;
            }
        });

    }


//    private void guimadn(){
//
//        Intent intent=getIntent();
//        String dntk = intent.getStringExtra("dntk");
//        SanPhamAdapter sanpham = new SanPhamAdapter(dntk);
//        sanpham.setInfo(dntk);
//
//
//
//    }
}