package com.example.duan1_nhom12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom12.adapter.HoaDonAdapter;
import com.example.duan1_nhom12.dao.HoaDonDao;
import com.example.duan1_nhom12.dao.KhachHangDao;
import com.example.duan1_nhom12.model.KhachHangModel;
import com.squareup.picasso.Picasso;

public class GiaoDienThanhToan extends AppCompatActivity {
private KhachHangDao dao;
private HoaDonDao daohd ;
private HoaDonAdapter adapterHD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_thanh_toan);


        daohd= new HoaDonDao(this);


         dao = new KhachHangDao(this);

         //ánh xạ
        TextView txttekh, txtsdtkh,txtdiachikh,txttensp,txtgiap,txttongtien;

        txttekh =findViewById(R.id.txttenkh_thanhtoan);
        txtsdtkh =findViewById(R.id.txtsdt_thanhtoan);
        txtdiachikh =findViewById(R.id.txtdiachi_thanhtoan);
        txttensp =findViewById(R.id.txtten);
        txtgiap =findViewById(R.id.txtgia);
        txttongtien=findViewById(R.id.txttongtien_tt);


        // Truy xuất dữ liệu của một khách hàng dựa trên mã username
        SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");
        KhachHangModel customer = dao.getCustomerByUsername(username);
        if (customer != null) {
            // Dữ liệu đã được truy xuất thành công
            String ten = customer.getTen();
            String pass = customer.getPasswork();
            String sdt = customer.getSdt();
            String diachi = customer.getDiachi();
            // Tiếp tục xử lý dữ liệu
            txttekh.setText(ten+"  |");
            txtsdtkh.setText(sdt);
            txtdiachikh.setText(diachi);
        }

        Intent intent = getIntent();
        String masp = intent.getStringExtra("masp");
        String tensp = intent.getStringExtra("mota");
        String giasp = intent.getStringExtra("gia");
        String loai = intent.getStringExtra("loai");

        txttensp.setText(tensp);
        txtgiap.setText(giasp);
        ImageView img = findViewById(R.id.imgsanpham);
        if (loai.equals("dien thoai")) {
            Picasso.get().load(R.drawable.img_phone).resize(100, 100).centerCrop().into(img);
        } else if (loai.equals("laptop")) {
            Picasso.get().load(R.drawable.img_laptop).resize(100, 100).centerCrop().into(img);
        }
        else if (loai.equals("tainghe")) {
            Picasso.get().load(R.drawable.img_tainghe).resize(100, 100).into(img);
        } else if (loai.equals("game")) {
            Picasso.get().load(R.drawable.img_game).resize(100, 100).into(img);
        }

        RadioButton rdobt, rdonhanh,rdohoatoc;
        rdobt= findViewById(R.id.rdobt);
        rdonhanh= findViewById(R.id.rdonhanh);
        rdohoatoc= findViewById(R.id.rdohoatoc);

        rdobt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtgiavc = findViewById(R.id.txtgiavc);
                TextView txtgiahang= findViewById(R.id.txtgiahang);
                TextView txttienthanhtoan=findViewById(R.id.txttienthanhtoan);

                txtgiavc.setText("20000");
                String gia =txtgiap.getText().toString();
                txtgiahang.setText(gia);
                int tongtiten=Integer.parseInt(txtgiavc.getText().toString()) +Integer.parseInt(txtgiahang.getText().toString());
                txttienthanhtoan.setText(""+tongtiten);
                txttongtien.setText(""+tongtiten);


            }
        });
        rdonhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtgiavc = findViewById(R.id.txtgiavc);
                TextView txtgiahang= findViewById(R.id.txtgiahang);
                TextView txttienthanhtoan=findViewById(R.id.txttienthanhtoan);

                txtgiavc.setText("30000");
                String gia =txtgiap.getText().toString();
                txtgiahang.setText(gia);
                int tongtiten=Integer.parseInt(txtgiavc.getText().toString()) +Integer.parseInt(txtgiahang.getText().toString());
                txttienthanhtoan.setText(""+tongtiten);
                txttongtien.setText(""+tongtiten);


            }
        });
        rdohoatoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtgiavc = findViewById(R.id.txtgiavc);
                TextView txtgiahang= findViewById(R.id.txtgiahang);
                TextView txttienthanhtoan=findViewById(R.id.txttienthanhtoan);

                txtgiavc.setText("50000");
                String gia =txtgiap.getText().toString();
                txtgiahang.setText(gia);
                int tongtiten=Integer.parseInt(txtgiavc.getText().toString()) +Integer.parseInt(txtgiahang.getText().toString());
                txttienthanhtoan.setText(""+tongtiten);

                txttongtien.setText(""+tongtiten);

            }
        });


        TextView txtdathang = findViewById(R.id.txtdathang);
        txtdathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tien=txttongtien.getText().toString();
                boolean check = daohd.themhoadon(username,Integer.valueOf(masp),Integer.valueOf(tien)) ;
                if(txttongtien.getText().toString().equals("0")){
                    Toast.makeText(GiaoDienThanhToan.this, "Bạn chưa chọn phương thức vận chuyển", Toast.LENGTH_SHORT).show();
                }else {
                    if(check){
                        Toast.makeText(GiaoDienThanhToan.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                        Intent inten= new Intent(GiaoDienThanhToan.this,Activity_khachhang.class);
                        startActivity(inten);


                        // adapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(GiaoDienThanhToan.this, "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();

                    }
                }


            }
        });


        ImageButton imgback = findViewById(R.id.ibtback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}