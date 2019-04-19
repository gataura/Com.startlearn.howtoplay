package com.playfun.pcard;

/**
 * Created by pavel on 29.03.2019.
 */

public class State {

    private String miniName; // название
    private String miniCapital;  // столица
    private int thumbResource; // ресурс флага

    public State(String miniName, String miniCapital, int flag){

        this.miniName=miniName;
        this.miniCapital=miniCapital;
        this.thumbResource=flag;
    }

    public String getMiniName() {
        return this.miniName;
    }

    public void setName(String miniName) {
        this.miniName = miniName;
    }

    public String getMiniCapital() {
        return this.miniCapital;
    }

    public void setCapital(String miniCapital) {
        this.miniCapital = miniCapital;
    }

    public int getThumbResource() {
        return this.thumbResource;
    }

    public void setFlagResource(int thumbResource) {
        this.thumbResource = thumbResource;
    }
}