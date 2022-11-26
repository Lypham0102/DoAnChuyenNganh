package com.fpoly.dell.project.model;

public class TrangThietBi {
    public String getMaTrangThietBi() {
        return MaTrangThietBi;
    }

    public String getTenTrangThietBi() {
        return TenTrangThietBi;
    }

    public String getGiaTTT() {
        return GiaTTT;
    }

    public void setMaTrangThietBi(String maTrangThietBi) {
        MaTrangThietBi = maTrangThietBi;
    }

    public void setTenTrangThietBi(String tenTrangThietBi) {
        TenTrangThietBi = tenTrangThietBi;
    }

    public void setGiaTTT(String giaTTT) {
        GiaTTT = giaTTT;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {MaNhaCungCap = maNhaCungCap;}

    public String getMaNhaCungCap() {return MaNhaCungCap;}

    private String TenTrangThietBi;

    private String MaTrangThietBi;

    private String GiaTTT;

    private String MaNhaCungCap;

    public TrangThietBi(){
    };

    public TrangThietBi(String maTrangThietBi, String tenTrangThietBi, String giaTTT, String maNhaCungCap){
        MaTrangThietBi= maTrangThietBi;
        TenTrangThietBi = tenTrangThietBi;
        GiaTTT = giaTTT;
        MaNhaCungCap = maNhaCungCap;
    }

    @Override
    public String toString() {
        return getMaTrangThietBi()+" | "+getTenTrangThietBi()+" | "+getGiaTTT();
    }
}

