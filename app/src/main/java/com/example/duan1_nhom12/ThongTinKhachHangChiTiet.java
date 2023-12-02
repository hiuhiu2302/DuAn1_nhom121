package com.example.duan1_nhom12;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1_nhom12.adapter.KhachHangAdapter;
import com.example.duan1_nhom12.dao.KhachHangDao;
import com.example.duan1_nhom12.model.KhachHangModel;

import java.util.ArrayList;

public class ThongTinKhachHangChiTiet extends AppCompatActivity {
    private KhachHangDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang_chi_tiet);
        dao = new KhachHangDao(this);


        EditText edtten, edtsdt,edtdiachi,edtmatk;
        edtten= findViewById(R.id.edtten_ttctkh);
        edtsdt= findViewById(R.id.edtsdt_ttctkh);
        edtdiachi= findViewById(R.id.edtdc_ttctkh);
        edtmatk= findViewById(R.id.edtusername_ttctkh);

        Button btncn =findViewById(R.id.btn_cn_ttctkh);

        /////sự kiện nút quay lại
        ImageButton imgback = findViewById( R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");
        KhachHangModel customer = dao.getCustomerByUsername(username);
        if (customer != null) {
            // Dữ liệu đã được truy xuất thành công
            String matk =customer.getUsername();
            String ten = customer.getTen();
            String pass = customer.getPasswork();
            String sdt = customer.getSdt();
            String diachi = customer.getDiachi();
            // Tiếp tục xử lý dữ liệu
            edtten.setText(ten);
            edtsdt.setText(sdt);
            edtdiachi.setText(diachi);
            edtmatk.setText(matk);

        }

        Intent intent = getIntent();
        String makh = intent.getStringExtra("ctkh");
        KhachHangModel customer1 = dao.getCustomerByUsername(makh);

        if (customer1 != null) {
            // Dữ liệu đã được truy xuất thành công
            String matk =customer1.getUsername();
            String ten = customer1.getTen();
            String pass = customer1.getPasswork();
            String sdt = customer1.getSdt();
            String diachi = customer1.getDiachi();
            // Tiếp tục xử lý dữ liệu
            edtten.setText(ten);
            edtsdt.setText(sdt);
            edtdiachi.setText(diachi);
            edtmatk.setText(matk);

        }

        Button btncapnhap =findViewById(R.id.btn_cn_ttctkh);
        btncapnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matk = edtmatk.getText().toString();
                String sdt =edtsdt.getText().toString();
                String diachi =edtdiachi.getText().toString();
                String pass=customer1.getPasswork();
                String ten =edtten.getText().toString();


                boolean check = dao.capnhapkhachhang(matk,pass,ten,sdt,diachi);
                if(check){
                    Toast.makeText(ThongTinKhachHangChiTiet.this, "Cập nhập thành công", Toast.LENGTH_SHORT).show();



                }else {
                    Toast.makeText(ThongTinKhachHangChiTiet.this, "Cập nhập thất bại", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}