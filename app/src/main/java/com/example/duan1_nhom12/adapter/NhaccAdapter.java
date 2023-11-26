package com.example.duan1_nhom12.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom12.R;
import com.example.duan1_nhom12.dao.KhachHangDao;
import com.example.duan1_nhom12.dao.NhaccDao;
import com.example.duan1_nhom12.model.KhachHangModel;
import com.example.duan1_nhom12.model.NhaCungCapModel;

import java.util.ArrayList;

public class NhaccAdapter extends RecyclerView.Adapter<NhaccAdapter.ViewHodel>{

    private Context context;
    private ArrayList<NhaCungCapModel> list;
    private NhaccDao dao;

    public NhaccAdapter(Context context, ArrayList<NhaCungCapModel> list, NhaccDao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nhacungcap, parent, false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {

        holder.txtma.setText(""+list.get(position).getManhacc());
        holder.txtten.setText(""+list.get(position).getTennhacc());
        holder.txtemail.setText(""+list.get(position).getEmail());
        holder.txtsdt.setText(""+list.get(position).getSdt());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView txtma, txtten , txtemail, txtsdt,txtsua,txtxoa;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);

            txtma = itemView.findViewById(R.id.txtmanhacc);
            txtten = itemView.findViewById(R.id.txttennhacc);
            txtemail = itemView.findViewById(R.id.txtemail);
            txtsdt = itemView.findViewById(R.id.txtsdtnhacc);
            txtsua = itemView.findViewById(R.id.txtsuanhacc);
            txtxoa = itemView.findViewById(R.id.txtxoanhacc);

        }
    }
}
