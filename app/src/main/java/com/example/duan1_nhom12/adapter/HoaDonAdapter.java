package com.example.duan1_nhom12.adapter;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom12.R;
import com.example.duan1_nhom12.dao.HoaDonDao;
import com.example.duan1_nhom12.dao.KhachHangDao;
import com.example.duan1_nhom12.model.HoaDonModel;
import com.example.duan1_nhom12.model.KhachHangModel;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHodel> {
    KhachHangDao daokh;

    ArrayList<KhachHangModel> listkh;

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
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoadon, parent, false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {

        holder.txtmahd.setText(""+list.get(position).getMahd());
        holder.txtmakh.setText(""+list.get(position).getMakh());
        holder.txtmasp.setText(""+list.get(position).getMasp());
        holder.txttiendh.setText(""+list.get(position).getTienhoadon());












        //////////////////////ẩn chức năng sửa
        daokh = new KhachHangDao(context);
        listkh = daokh.getds();
        SharedPreferences preferences = context.getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");
        boolean shouldShowFlthem = false;
        for (KhachHangModel kh : listkh) {
            if (kh.getUsername().equals(username)) {
                shouldShowFlthem = true;
                break;
            }
        }
        if (shouldShowFlthem) {
            holder.txtsua.setVisibility(View.GONE);
        } else {
            holder.txtsua.setVisibility(View.VISIBLE);


        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView txtmahd ,txtmakh,txtmasp,txttiendh,txtsua,txtxoa;
        public ViewHodel(@NonNull View itemView) {

            super(itemView);
            txtmahd=itemView.findViewById(R.id.txtmahd_hoadon);
            txtmakh=itemView.findViewById(R.id.txtmakh_hoadon);
            txtmasp=itemView.findViewById(R.id.txtmasp_hoadon);
            txttiendh = itemView.findViewById(R.id.txttienhd_hoadon);
            txtsua =itemView.findViewById(R.id.txtsua_hoadon);
            txtxoa =itemView.findViewById(R.id.txtsua_hoadon);



        }
    }
}
