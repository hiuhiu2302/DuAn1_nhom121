package com.example.duan1_nhom12.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom12.database.DatabaseHelper;
import com.example.duan1_nhom12.model.SanPhamModel;

import java.util.ArrayList;

public class SanPhamGH_dao {
    DatabaseHelper dbHelper ;
    public SanPhamGH_dao(Context context){
        dbHelper= new DatabaseHelper(context);
    }
    public ArrayList<SanPhamModel> getds(){
        ArrayList<SanPhamModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select*from sanphamgh",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                list.add( new SanPhamModel(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3)));

            }while ((cursor.moveToNext()));
        }
        return  list;
    }


    public boolean themVaoGioHang(String ten,int giatien,String loai){
        SQLiteDatabase sqLiteDatabase= dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp",ten);
        contentValues.put("giasp",giatien);
        contentValues.put("loaisp",loai);
        long check = sqLiteDatabase.insert("sanphamgh",null,contentValues);
        if(check==-1)
            return false;
        return true;

    }


    public  boolean capnhap(int masp,String ten ,int gia,String loai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp",ten);
        contentValues.put("giasp",gia);
        contentValues.put("loaisp",loai);
        long check = sqLiteDatabase.update("sanphamgh",contentValues,"masp=?",new String[]{String.valueOf(masp)});
        if(check==-1)
            return  false;
        return true;



    }





}


