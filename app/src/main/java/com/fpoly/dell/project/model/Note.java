package com.fpoly.dell.project.model;
import java.util.Date;

public class Note{
    private String Manote;
    private String Tennote;
    private String Noidung;

    public void setManote(String manote) {Manote = manote;}


    public void setTennote(String tennote) {Tennote = tennote;}

    public void setNoidung(String noidung) {Noidung = noidung;}


    public String getManote() {return Manote;}


    public String getTennote() {return Tennote;}

    public String getNoidung() {return Noidung;}



    public  Note(){}
    public  Note(String manote, String tennote, String noidung){
        this.Manote = manote;
        this.Tennote = tennote;
        this.Noidung = noidung;

    }
    @Override
    public String toString() {
        return "Note: "+ getTennote();
    }

}
