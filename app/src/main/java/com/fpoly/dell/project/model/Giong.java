package com.fpoly.dell.project.model;

public class Giong {
    public String getMaGiong() {
        return MaGiong;
    }

    public String getTenGiong() {
        return TenGiong;
    }

    public String getXuatXu() {
        return XuatXu;
    }

    public void setMaGiong(String maGiong) {
        MaGiong = maGiong;
    }

    public void setTenGiong(String tenGiong) {
        TenGiong = tenGiong;
    }

    public void setXuatXu(String xuatXu) {
        XuatXu = xuatXu;
    }

    private String MaGiong;
    private String TenGiong;
    private String XuatXu;
    public Giong(){
    };
    public Giong(String maGiong, String tenGiong, String xuatXu){
        MaGiong = maGiong;
        TenGiong = tenGiong;
        XuatXu = xuatXu;
    }
    @Override
    public String toString() {
        return getMaGiong()+" | "+getTenGiong()+" | "+getXuatXu();
    }

}
