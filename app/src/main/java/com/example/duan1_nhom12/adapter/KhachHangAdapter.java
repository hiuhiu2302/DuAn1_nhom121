package com.example.duan1_nhom12.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom12.R;
import com.example.duan1_nhom12.ThongTinKhachHangChiTiet;
import com.example.duan1_nhom12.dao.KhachHangDao;
import com.example.duan1_nhom12.dao.SanPhamDAO;
import com.example.duan1_nhom12.model.KhachHangModel;
import com.example.duan1_nhom12.model.SanPhamModel;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHodel>{

    private Context context;
    private ArrayList<KhachHangModel> list;
    private KhachHangDao dao;

    public KhachHangAdapter(Context context, ArrayList<KhachHangModel> list, KhachHangDao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khachhang, parent, false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
        holder.txtuser.setText("  "+list.get(position).getUsername());
        holder.txtten.setText(list.get(position).getTen());
        holder.txtsdt.setText(list.get(position).getSdt());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, ThongTinKhachHangChiTiet.class);
                String username = list.get(position).getUsername();
                intent.putExtra("ctkh",username);
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
        TextView txtuser ,txtten, txtsdt,txtxoa;

        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            txtuser = itemView.findViewById(R.id.txtuser);
            txtten = itemView.findViewById(R.id.txtten);
            txtsdt = itemView.findViewById(R.id.txtsdt);
            txtxoa = itemView.findViewById(R.id.txtxoa);




        }
    }
}
