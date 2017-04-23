package com.testapplication.project.kucherenko.dnu.testsloyev.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable {

    @SerializedName("data")
    private DataObjects dataObjects = new DataObjects();

    public DataObjects getDataObjects() {
        return dataObjects;
    }

    public void setDataObjects(DataObjects dataObjects) {
        this.dataObjects = dataObjects;
    }
}
