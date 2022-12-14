package com.fpoly.dell.project.model;

import java.util.Random;

public class ThucAn {
    private String mathucan;
    private String Tenthucan;
    private String Maloai;
    private String Soluong;
    private String Dongia;
    private String MaNhaCungCap;

    public ThucAn(){}
    public ThucAn(String mathucan, String Tenthucan, String Maloai, String Soluong, String Dongia, String maNhaCungCap) {
        this.mathucan = mathucan;
        this.Tenthucan = Tenthucan;
        this.Maloai = Maloai;
        this.Soluong = Soluong;
        this.Dongia = Dongia;
        this.MaNhaCungCap = maNhaCungCap;
    }

    public String getMathucan() {
        return mathucan;
    }

    public void setMathucan(String mathucan) {
        this.mathucan = mathucan;
    }

    public String getTenthucan() {
        return Tenthucan;
    }

    public void setTenthucan(String tenthucan) {
        this.Tenthucan = tenthucan;
    }

    public String getMaloai() {
        return Maloai;
    }

    public void setMaloai(String maloai) {
        this.Maloai = maloai;
    }

    public String getSoluong() {
        return Soluong;
    }

    public void setSoluong(String soluong) {
        this.Soluong = soluong;
    }

    public String getDongia() {
        return Dongia;
    }

    public void setDongia(String dongia) {
        this.Dongia = dongia;
    }

    public String getMaNhaCungCap() {return MaNhaCungCap;}

    public void setMaNhaCungCap(String maNhaCungCap) {MaNhaCungCap = maNhaCungCap;}


    //public void setMancc(String mancc) {Mancc = mancc;}

    //public String getMancc() {return Mancc;}


    @Override
    public String toString() {
        return "Tên cám: "+ getTenthucan()+" | "+" Mã loại cám: "+getMaloai();
    }
}
