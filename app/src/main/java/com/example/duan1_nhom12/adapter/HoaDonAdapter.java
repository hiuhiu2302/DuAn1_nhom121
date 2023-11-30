package com.example.duan1_nhom12.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom12.dao.HoaDonDao;
import com.example.duan1_nhom12.dao.KhachHangDao;
import com.example.duan1_nhom12.model.HoaDonModel;
import com.example.duan1_nhom12.model.KhachHangModel;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHodel> {


    private Context context;
    private ArrayList<HoaDonModel> list;
    private HoaDonDao dao;

    public HoaDonAdapter(Context context, ArrayList<HoaDonModel> list, HoaDonDao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
        }
    }
}
