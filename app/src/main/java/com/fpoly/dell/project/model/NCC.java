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
    public String getSdt() { return Sdt; }

    public void setMaNCC(String maNCC) {MaNCC = maNCC; }

    public void setTenNCC(String tenNCC) {
        TenNCC = tenNCC;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public void setSdt(String sdt) { Sdt = sdt; }

    private String MaNCC;
    private String TenNCC;
    private String DiaChi;
    private String Sdt;

    public NCC(){
    };
     public NCC (String maNCC, String tenNCC, String diaChi, String sdt){
         MaNCC = maNCC;
         TenNCC = tenNCC;
         DiaChi = diaChi;
         Sdt = sdt;
     }
    @Override
    public String toString() {
        return getTenNCC()+" | "+getDiaChi();
    }
}
