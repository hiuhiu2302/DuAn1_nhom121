package com.example.duan1_nhom12.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom12.database.DatabaseHelper;
import com.example.duan1_nhom12.model.KhachHangModel;
import com.example.duan1_nhom12.model.NhaCungCapModel;

import java.util.ArrayList;

public class NhaccDao {
    DatabaseHelper dbHelper ;


    public NhaccDao(Context context){
        dbHelper= new DatabaseHelper(context);
    }
    public ArrayList<NhaCungCapModel> getds(){
        ArrayList<NhaCungCapModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select*from nhacc",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                //String username, String passwork, String ten, String sdt, String diachi
                list.add( new NhaCungCapModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));

            }while ((cursor.moveToNext()));
        }
        return  list;
    }
}
