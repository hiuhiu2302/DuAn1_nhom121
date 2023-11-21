package com.example.duan1_nhom12.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom12.GiaoDienChiTietSP;
import com.example.duan1_nhom12.R;
import com.example.duan1_nhom12.dao.SanPhamGH_dao;
import com.example.duan1_nhom12.model.SanPhamModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SanPhamGH_Adapter  extends RecyclerView.Adapter<SanPhamGH_Adapter.ViewHodel>  {
    private Context context;
    private ArrayList<SanPhamModel> list;
    private SanPhamGH_dao dao;

    public SanPhamGH_Adapter(Context context, ArrayList<SanPhamModel> list, SanPhamGH_dao dao) {
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
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
        holder.txtten.setText(list.get(position).getTen());
        holder.txtgia.setText("Giá: " + list.get(position).getGia());
        holder.txtloai.setText("Loại: " + list.get(position).getLoai());
        SanPhamModel sp = list.get(position);
        if (sp.getLoai().equals("dien thoai")) {
            Picasso.get().load(R.drawable.img_phone).resize(190,150).into(holder.img);
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

                String ten = list.get(position).getTen();
                String gia =""+ list.get(position).getGia();
                String loai = list.get(position).getLoai();


                Context context = view.getContext();
                Intent intent = new Intent(context, GiaoDienChiTietSP.class);
                intent.putExtra("ten", ten);
                intent.putExtra("gia", gia);
                intent.putExtra("loai",loai);
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
}
