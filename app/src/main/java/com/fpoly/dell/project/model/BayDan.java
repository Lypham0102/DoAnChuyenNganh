package com.fpoly.dell.project.model;

public class BayDan {
    private String maBayDan;
    private String soBayDan;
    private String soLuongVat;
    private String maThucAn;
    public BayDan(){}
    public BayDan(String maBayDan, String maThucAn,  String soBayDan, String soLuongVat) {
        this.maBayDan = maBayDan;
        this.maThucAn = maThucAn;
        this.soBayDan = soBayDan;
        this.soLuongVat = soLuongVat;
    }

    public String getMaBayDan() {
        return maBayDan;
    }

    public void setMaBayDan(String maBayDan) {
        this.maBayDan = maBayDan;
    }

    public String getSoBayDan() {
        return soBayDan;
    }

    public void setSoBayDan(String soBayDan) {
        this.soBayDan = soBayDan;
    }

    public String getSoLuongVat() {
        return soLuongVat;
    }

    public void setSoLuongVat(String soLuongVat) {
        this.soLuongVat = soLuongVat;
    }
    public String getMaThucAn() {return maThucAn;}

    public void setMaThucAn(String maThucAn) {
        this.maThucAn = maThucAn;
    }
}
