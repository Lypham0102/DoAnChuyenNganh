package com.fpoly.dell.project.model;

public class VatNuoi {
    private String mavatnuoi;
    private String Machungloai;
    private String Soluong;
    private String Loaithucan;
    private String Suckhoe;
    public VatNuoi(){}

    public VatNuoi(String mavatnuoi, String machungloai, String soluong, String loaithucan, String suckhoe) {
        this.mavatnuoi = mavatnuoi;
        this.Machungloai = machungloai;
        this.Soluong = soluong;
        this.Loaithucan = loaithucan;
        this.Suckhoe = suckhoe;

    }





    public String getMavatnuoi() {
        return mavatnuoi;
    }

    public void setMavatnuoi(String mavatnuoi) {
        this.mavatnuoi = mavatnuoi;
    }

    public String getMachungloai() {
        return Machungloai;
    }

    public void setMachungloai(String machungloai) {
        Machungloai = machungloai;
    }

    public String getSoluong() {
        return Soluong;
    }

    public void setSoluong(String soluong) {
        Soluong = soluong;
    }

    public String getLoaithucan() {
        return Loaithucan;
    }

    public void setLoaithucan(String loaithucan) {
        Loaithucan = loaithucan;
    }

    public String getSuckhoe() {
        return Suckhoe;
    }

    public void setSuckhoe(String suckhoe) {
        Suckhoe = suckhoe;
    }



    @Override
    public String toString() {
        return "VatNuoi{" +
                "mavatnuoi='" + mavatnuoi + '\'' +
                ", Machungloai='" + Machungloai + '\'' +
                ", Soluong='" + Soluong + '\'' +
                ", Loaithucan='" + Loaithucan + '\'' +
                ", Suckhoe='" + Suckhoe + '\'' +
                '}';
    }
}
