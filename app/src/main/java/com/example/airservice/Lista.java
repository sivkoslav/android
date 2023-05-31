package com.example.airservice;

import java.util.ArrayList;

public class Lista {

    ArrayList<String> imenaGradova;

    public Lista(){
        imenaGradova=new ArrayList<>();
    }

    public ArrayList<String> getImenaGradova() {
        return imenaGradova;
    }

    public void setImenaGradova(ArrayList<String> imenaGradova) {
        this.imenaGradova = imenaGradova;
    }
}
