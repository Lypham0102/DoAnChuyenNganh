package com.fpoly.dell.project.model;

public class ChungLoai {
    private String Machungloai;
    private String Tenvatnuoi;
    private String Vitrichuong;
    public ChungLoai(){

    }

    public ChungLoai(String machungloai, String tenvatnuoi, String vitrichuong) {
        Machungloai = machungloai;
        Tenvatnuoi = tenvatnuoi;
        Vitrichuong = vitrichuong;
    }

    public String getMachungloai() {
        return Machungloai;
    }

    public void setMachungloai(String machungloai) {
        Machungloai = machungloai;
    }

    public String getTenvatnuoi() {
        return Tenvatnuoi;
    }

    public void setTenvatnuoi(String tenvatnuoi) {
        Tenvatnuoi = tenvatnuoi;
    }

    public String getVitrichuong() {
        return Vitrichuong;
    }

    public void setVitrichuong(String vitrichuong) {
        Vitrichuong = vitrichuong;
    }

    @Override
    public String toString() {
        return getMachungloai()+" | "+getTenvatnuoi();
    }
}
