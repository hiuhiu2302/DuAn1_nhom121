package com.example.duan1_nhom12.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom12.database.DatabaseHelper;
import com.example.duan1_nhom12.model.HoaDonModel;
import com.example.duan1_nhom12.model.ThongBaoModel;

import java.util.ArrayList;

public class ThongBaoDao {
    DatabaseHelper dbHelper ;

    public ThongBaoDao(Context context){
        dbHelper= new DatabaseHelper(context);
    }
    public ArrayList<ThongBaoModel> getds(){
        ArrayList<ThongBaoModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select*from thongbao",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                list.add( new ThongBaoModel(cursor.getInt(0),cursor.getString(1)));
            }while ((cursor.moveToNext()));
        }
        return  list;
    }


    public boolean themthongbao(String thongtin ){
        SQLiteDatabase sqLiteDatabase= dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("thongtin",thongtin);

        long check = sqLiteDatabase.insert("thongbao",null,contentValues);
        if(check==-1)
            return false;
        return true;

    }
}
