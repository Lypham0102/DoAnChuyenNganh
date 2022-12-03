package com.fpoly.dell.project.model;

public class CamBien {
    public float getHUMIDITY() {
        return HUMIDITY;
    }

    public float getPPM() {
        return PPM;
    }

    public float getTEMPERATURE() {
        return TEMPERATURE;
    }



    public void setPPM(float PPM) {
        this.PPM = PPM;
    }

    public void setTEMPERATURE(float TEMPERATURE) {
        this.TEMPERATURE = TEMPERATURE;
    }

    public void setHUMIDITY(float HUMIDITY) {
        this.HUMIDITY = HUMIDITY;
    }

    private float HUMIDITY;
    private float PPM;
    private float TEMPERATURE;

    public  CamBien(){
    }

    public CamBien(float humidity, float ppm, float temperature){
        this.HUMIDITY = humidity;
        this.PPM = ppm;
        this.TEMPERATURE = temperature;

    }

}
