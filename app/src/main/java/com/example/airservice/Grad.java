package com.example.airservice;

import androidx.core.content.pm.PermissionInfoCompat;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="grad_tabela")
public class Grad {

    @PrimaryKey(autoGenerate = true)
    private int id;


    private String imeDrzave;


    private String imeGrada;


    private int aqi;

    public Grad(String imeDrzave, String imeGrada, int aqi) {
        this.imeDrzave = imeDrzave;
        this.imeGrada = imeGrada;
        this.aqi = aqi;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getImeDrzave() {
        return imeDrzave;
    }

    public String getImeGrada() {
        return imeGrada;
    }

    public void setImeDrzave(String imeDrzave){this.imeDrzave=imeDrzave;}

    public void setImeGrada(String imeGrada){this.imeGrada=imeGrada;}

    public void setAqi(int aqi){this.aqi=aqi;}

    public int getAqi() {
        return aqi;
    }

    @Override
    public String toString(){
        return imeGrada + " " + imeDrzave + " "  + aqi;
    }
}
