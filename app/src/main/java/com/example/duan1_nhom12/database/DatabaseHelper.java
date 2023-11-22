package com.example.duan1_nhom12.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context,"QL",null,7);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String dbNhacc ="create table nhacc( manhacc integer PRIMARY KEY autoincrement,\n" +
                "    tennhacc TEXT,\n" +
                "    email TEXT,\n" +
                "    sdt TEXT)";
        sqLiteDatabase.execSQL(dbNhacc);



        String dbSanPham ="create table sanpham(masp integer primary key autoincrement,tensp text, giasp integer,loaisp text, motasp text,manhacc integer references nhacc(manhacc) )";
        sqLiteDatabase.execSQL(dbSanPham);

        String dbAdmin = "create table admin(username text primary key ,hoten text,password text )";
        sqLiteDatabase.execSQL(dbAdmin);

        String dbKhachHang = "create table khachhang(username text primary key ,hoten text,password text,sdt text,diachi text )";
        sqLiteDatabase.execSQL(dbKhachHang);


        String dbHoaDon="create table hoadon( mahd integer PRIMARY KEY autoincrement,maadmin text references admin(username),makhachhang text references khachhang(username) )";

        sqLiteDatabase.execSQL(dbHoaDon);


        String dbChiTietHD="create table chitiethd(macthd integer  PRIMARY KEY autoincrement,mahd integer references hoadon(mahd),masp integer references sanpham(masp))";
        sqLiteDatabase.execSQL(dbChiTietHD);



        String dbSanPhamGh ="create table sanphamgh(masp integer primary key autoincrement,tensp text, giasp integer,loaisp text)";
        sqLiteDatabase.execSQL(dbSanPhamGh);


        String dbSanPhamYT ="create table sanphamyt(masp integer primary key ,tensp text, giasp integer,loaisp text)";
        sqLiteDatabase.execSQL(dbSanPhamYT);




        sqLiteDatabase.execSQL("insert into nhacc values (1,'apple','abc@gmail.com','0123456789'),(2,'androi','abc@gmail.com','0123456789'),(3,'window','abc@gmail.com','0123456789')");
        sqLiteDatabase.execSQL("INSERT INTO sanpham VALUES " +
                "(1,'Iphone 15',25000000,'dien thoai','iPhone 14 Pro 256GB likenew ATV - Vàng',1)," +
                "(2,'Iphone 14',20000000,'dien thoai','iPhone 14 Pro 256GB likenew ATV - Vàng',1)," +
                "(3,'Iphone 13',20000000,'dien thoai','iPhone 14 Pro 256GB likenew ATV - Vàng',1)," +
                "(4,'Laptop HP',20000000,'laptop','Asus TUF F15 | i5-12500H + RTX 3050',3)," +
                "(5,'Laptop HP',20000000,'laptop','Asus TUF F15 | i5-12500H + RTX 3050',3)," +
                "(6,'Tai nghe Bluetooth',20000000,'tainghe','Tai nghe mèo Bluetooth P47M dễ thương xanh navi Giá rẻ',2)," +
                "(7,'Tai nghe Bluetooth',20000000,'tainghe','Tai nghe mèo Bluetooth P47M dễ thương xanh navi Giá rẻ',2)," +
                "(8,'PS5',20000000,'game','Máy chơi game Sony Playstation 5 Digital Slim | PS5 Slim Digital',3)," +
                "(9,'PS5',20000000,'game','Máy chơi game Sony Playstation 5 Digital Slim | PS5 Slim Digital',3)");


        sqLiteDatabase.execSQL("INSERT INTO admin VALUES ('hieund','nguyen duc hieu','abc')");


        sqLiteDatabase.execSQL("INSERT INTO khachhang VALUES ('khachhang1','nguyen duc hieu','123','0767632587','haiduong')");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("drop table if exists admin");
        sqLiteDatabase.execSQL("drop table if exists khachhang");
        sqLiteDatabase.execSQL("drop table if exists sanpham");
        sqLiteDatabase.execSQL("drop table if exists sanphamgh");
        sqLiteDatabase.execSQL("drop table if exists sanphamyt");
        sqLiteDatabase.execSQL("drop table if exists hoadon");
        sqLiteDatabase.execSQL("drop table if exists chitiethd");

        sqLiteDatabase.execSQL("drop table if exists nhacc");


    }

    public String checkCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryAdmin = "SELECT * FROM admin WHERE username = ? AND password = ?";
        String queryKhachHang = "SELECT * FROM khachhang WHERE username = ? AND password = ?";

        Cursor cursorAdmin = db.rawQuery(queryAdmin, new String[]{username, password});
        if (cursorAdmin.getCount() > 0) {
            cursorAdmin.close();
            db.close();
            return "admin";
        }

        Cursor cursorKhachHang = db.rawQuery(queryKhachHang, new String[]{username, password});
        if (cursorKhachHang.getCount() > 0) {
            cursorKhachHang.close();
            db.close();
            return "khachhang";
        }

        cursorAdmin.close();
        cursorKhachHang.close();
        db.close();
        return "invalid";
    }


}
