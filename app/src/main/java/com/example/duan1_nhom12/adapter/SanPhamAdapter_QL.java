package com.example.duan1_nhom12.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class SanPhamAdapter_QL extends RecyclerView.Adapter<SanPhamAdapter_QL.ViewHodel>  {
    private Context context;
    private ArrayList<SanPhamModel> list;
    private SanPhamDAO dao;

    public SanPhamAdapter_QL(Context context, ArrayList<SanPhamModel> list, SanPhamDAO dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sampham_quanly, parent, false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder,@SuppressLint("RecyclerView") int position) {
        holder.txtten.setText(list.get(position).getTen());
        holder.txtgia.setText("Giá: " + list.get(position).getGia());
     //   holder.txtloai.setText("Loại: " + list.get(position).getLoai());

        //holder.txtloai.setVisibility(View.INVISIBLE);
//
//        if(madn.equals("2")){
//            holder.txtloai.setVisibility(View.INVISIBLE);
//
//        }



        SanPhamModel sp = list.get(position);

        if (sp.getLoai().equals("dien thoai")) {
            Picasso.get().load(R.drawable.img_phone).resize(130,110).centerCrop().into(holder.img);
        } else if (sp.getLoai().equals("laptop")) {
            Picasso.get().load(R.drawable.img_laptop).resize(130,110).centerCrop().into(holder.img);
        }
        else if(sp.getLoai().equals("tainghe")){
            Picasso.get().load(R.drawable.img_tainghe).resize(130,110).into(holder.img);

        }
        else if(sp.getLoai().equals("game")){
            Picasso.get().load(R.drawable.img_game).resize(130,110).into(holder.img);

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
//////////////////////////////////////////////////////
        holder.txtsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

////////////////////////////////////////////////////
        holder.txtxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = list.get(position).getMasp();
                dao.delete(String.valueOf(check));
                Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
                list.clear();
                list=dao.getds();
                notifyDataSetChanged();
            }
        });
    }
    /////////////////////////////////////////////

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView txtten, txtgia, txtloai,txtsua,txtxoa;

        ImageView img;

        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.txtten);
            txtgia = itemView.findViewById(R.id.txtgia);
            txtloai = itemView.findViewById(R.id.txtloai);

            txtxoa =itemView.findViewById(R.id.txtxoasp);
            txtsua= itemView.findViewById(R.id.txtsuasp);
            img = itemView.findViewById(R.id.imgsanpham);



        }
    }
}
