package com.fpoly.dell.project.model;

import java.util.Date;

public class BayChuong {

    private String Mabaychuong;
    private String Sochuong;
    public  BayChuong(){

    }

    public BayChuong(String mabaychuong, String sochuong) {
        Mabaychuong = mabaychuong;
        Sochuong = sochuong;
    }
    public String getMabaychuong() {
        return Mabaychuong;
    }

    public void setMabaychuong(String mabaychuong) {
        Mabaychuong = mabaychuong;
    }

    public String getSochuong() {
        return Sochuong;
    }

    public void setSochuong(String sochuong) {
        Sochuong = sochuong;
    }

}
