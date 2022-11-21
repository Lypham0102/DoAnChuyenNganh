package com.fpoly.dell.project.model;

public class NCC {
    public String getMaNCC() {
        return MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setMaNCC(String maNCC) {
        MaNCC = maNCC;
    }

    public void setTenNCC(String tenNCC) {
        TenNCC = tenNCC;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    private String MaNCC;
    private String TenNCC;
    private String DiaChi;

    public NCC(){

    };
     public NCC (String maNCC, String tenNCC, String diaChi){
         MaNCC = maNCC;
         TenNCC = tenNCC;
         DiaChi = diaChi;
     }

    @Override
    public String toString() {
        return getMaNCC()+" | "+getTenNCC()+" | "+getDiaChi();
    }
}
