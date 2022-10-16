package com.fpoly.dell.project.model;

public class ThucAn {
    private String mathucan;
    private String Tenthucan;
    private String Maloai;
    private String Soluong;
    private String Dongia;
    public ThucAn(){}
    public ThucAn(String mathucan, String Tenthucan, String Maloai, String Soluong, String Dongia) {
        this.mathucan = mathucan;
        this.Tenthucan = Tenthucan;
        this.Maloai = Maloai;
        this.Soluong = Soluong;
        this.Dongia = Dongia;
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

    @Override
    public String toString() {
        return "ThucAn{" +
                "mathucan='" + mathucan + '\'' +
                ", Tenthucan='" + Tenthucan + '\'' +
                ", Maloai='" + Maloai + '\'' +
                ", Soluong='" + Soluong + '\'' +
                ", Dongia='" + Dongia + '\'' +
                '}';
    }
}
