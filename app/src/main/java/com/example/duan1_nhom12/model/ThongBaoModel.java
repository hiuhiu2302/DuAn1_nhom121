package com.example.duan1_nhom12.model;

public class ThongBaoModel {
    private int matb;
    private String thongtin;

    public int getMatb() {
        return matb;
    }

    public ThongBaoModel(int matb, String thongtin) {
        this.matb = matb;
        this.thongtin = thongtin;
    }

    public void setMatb(int matb) {
        this.matb = matb;
    }

    public ThongBaoModel(String thongtin) {
        this.thongtin = thongtin;
    }

    public String getThongtin() {
        return thongtin;
    }

    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }
}
