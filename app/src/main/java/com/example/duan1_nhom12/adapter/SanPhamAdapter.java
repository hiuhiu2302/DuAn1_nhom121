package com.example.duan1_nhom12.adapter;

import static android.content.Intent.parseIntent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom12.GiaoDienChiTietSP;
import com.example.duan1_nhom12.R;
import com.example.duan1_nhom12.dao.SanPhamDAO;
import com.example.duan1_nhom12.model.SanPhamModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHodel> {
    private Context context;
    private ArrayList<SanPhamModel> list;
    private SanPhamDAO dao;



//    private String madn;
//
//    public SanPhamAdapter(String madn){
//        this.madn=madn;
//    }
//    public void setInfo(String madn) {
//        this.madn = madn;
//    }


    public SanPhamAdapter(Context context, ArrayList<SanPhamModel> list, SanPhamDAO dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }



    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();


        View view = inflater.inflate(R.layout.item_sanpham, parent, false);



        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, @SuppressLint("RecyclerView") int position) {
        holder.txtten.setText(list.get(position).getTen());
        holder.txtgia.setText("Giá: " + list.get(position).getGia());
        holder.txtloai.setText("Loại: " + list.get(position).getLoai());





        SanPhamModel sp = list.get(position);
        if (sp.getLoai().equals("dien thoai")) {
            Picasso.get().load(R.drawable.img_phone).resize(190,150).centerCrop().into(holder.img);
        } else if (sp.getLoai().equals("laptop")) {
            Picasso.get().load(R.drawable.img_laptop).resize(190,150).centerCrop().into(holder.img);
 }
        else if(sp.getLoai().equals("tainghe")){
            Picasso.get().load(R.drawable.img_tainghe).resize(190,150).into(holder.img);

        }
        else if(sp.getLoai().equals("game")){
            Picasso.get().load(R.drawable.img_game).resize(190,150).into(holder.img);

        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                String masp = ""+list.get(position).getMasp();
                String ten = list.get(position).getTen();
                String gia =""+ list.get(position).getGia();
                String loai = list.get(position).getLoai();
                String mota = list.get(position).getMotasp();
                String manhacc =""+list.get(position).getManhacc();


                Context context = view.getContext();
                Intent intent = new Intent(context, GiaoDienChiTietSP.class);
                intent.putExtra("masp", masp);
                intent.putExtra("ten", ten);
                intent.putExtra("gia", gia);
                intent.putExtra("loai",loai);
                intent.putExtra("mota", mota);
                intent.putExtra("manhacc",manhacc);


                context.startActivity(intent);
                return false;
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {

        TextView txtten, txtgia, txtloai;

        ImageView img;

        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.txtten);
            txtgia = itemView.findViewById(R.id.txtgia);
            txtloai = itemView.findViewById(R.id.txtloai);
            img = itemView.findViewById(R.id.imgsanpham);




        }
    }

    public void showDialog(SanPhamModel sp ){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_sp,null);
        builder.setView(view);
        AlertDialog alertDialog= builder.create();
        alertDialog.show();

        EditText edttensp,edtgiasp,edtmacc,edtmota;
        edttensp= view.findViewById(R.id.edttensp_sua);
        edtgiasp= view.findViewById(R.id.edttensp_sua);
        edtmacc= view.findViewById(R.id.edttensp_sua);
        edtmota= view.findViewById(R.id.edttensp_sua);

        RadioButton rdodt,rdolaptop,rdotainghe,rdogame;
        rdodt = view.findViewById(R.id.rdo_dt_sua);
        rdolaptop = view.findViewById(R.id.rdo_laptop_sua);
        rdotainghe = view.findViewById(R.id.rdo_tainghe_sua);
        rdogame = view.findViewById(R.id.rdo_game_sua);

        Button btncn , btnhuy;
        btncn= view.findViewById(R.id.btnsuasp_sua);
        btnhuy= view.findViewById(R.id.btnhuy_sua);

        edttensp.setText(sp.getTen());
        edtgiasp.setText(""+sp.getGia());
        edtmacc.setText(""+sp.getManhacc());
        edtmota.setText(sp.getMotasp());

        if(sp.getLoai().equals("dien thoai")){
            rdodt.setChecked(true);
        }
        if(sp.getLoai().equals("laptop")){
            rdolaptop.setChecked(true);
        }
        if(sp.getLoai().equals("tainghe")){
            rdotainghe.setChecked(true);
        }
        if(sp.getLoai().equals("game")){
            rdogame.setChecked(true);
        }

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btncn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loai="dien thoai";
                if(rdodt.isChecked()){
                    loai="dien thoai";
                }
                if(rdolaptop.isChecked()){
                    loai="laptop";
                }
                if(rdotainghe.isChecked()){
                    loai="tainghe";
                }
                if(rdogame.isChecked()){
                    loai="game";
                }

                String tensp = edttensp.getText().toString();
                int giasp = Integer.parseInt(edtgiasp.getText().toString());
                String mota =edtmota.getText().toString();
                int manhacc= Integer.parseInt(edtmacc.getText().toString());

                int masp =sp.getMasp();
                boolean check = dao.capnhap(masp,tensp,giasp,loai,mota,manhacc);
                if(check){
                    Toast.makeText(context, "Cập nhập thành công", Toast.LENGTH_SHORT).show();

                    list.clear();
                    list=dao.getds();
                    notifyDataSetChanged();

                }else {
                    Toast.makeText(context, "Cập nhập thất bại", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }

}
