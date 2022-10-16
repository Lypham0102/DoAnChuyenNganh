package com.fpoly.dell.project.model;

import java.util.Date;

public class ChiPhi {
    private String Machiphi;
    private String Tenthucan;
    private Date ngaynhap;
    private String Soluong;
    private String Giatien;
    public  ChiPhi(){

    }

    public ChiPhi(String machiphi, String tenthucan, Date ngaynhap, String soluong, String giatien) {
        Machiphi = machiphi;
        Tenthucan = tenthucan;
        this.ngaynhap = ngaynhap;
        Soluong = soluong;
        Giatien = giatien;
    }

    public String getMachiphi() {
        return Machiphi;
    }

    public void setMachiphi(String machiphi) {
        Machiphi = machiphi;
    }

    public String getTenthucan() {
        return Tenthucan;
    }

    public void setTenthucan(String tenthucan) {
        Tenthucan = tenthucan;
    }

    public Date getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(Date ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public String getSoluong() {
        return Soluong;
    }

    public void setSoluong(String soluong) {
        Soluong = soluong;
    }

    public String getGiatien() {
        return Giatien;
    }

    public void setGiatien(String giatien) {
        Giatien = giatien;
    }
}
