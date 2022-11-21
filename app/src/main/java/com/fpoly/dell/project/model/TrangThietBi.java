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

    private String MaTrangThietBi;
    private String TenTrangThietBi;
    private String GiaTTT;

    public TrangThietBi(){
    };

    public TrangThietBi(String maTrangThietBi, String tenTrangThietBi, String giaTTT){
        MaTrangThietBi= maTrangThietBi;
        TenTrangThietBi = tenTrangThietBi;
        GiaTTT = giaTTT;
    }

    @Override
    public String toString() {
        return getMaTrangThietBi()+" | "+getTenTrangThietBi()+" | "+getGiaTTT();
    }
}

