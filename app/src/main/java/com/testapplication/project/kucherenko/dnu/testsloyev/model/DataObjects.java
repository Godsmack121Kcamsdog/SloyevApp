package com.testapplication.project.kucherenko.dnu.testsloyev.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

class DataObjects implements Serializable {

    @SerializedName("21-04-2017")
    private ArrayList<String> dataList1 = new ArrayList<>();
    @SerializedName("22-04-2017")
    private ArrayList<String> dataList2 = new ArrayList<>();
    @SerializedName("23-04-2017")
    private ArrayList<String> dataList3 = new ArrayList<>();

    public ArrayList<String> getDataList1() {
        return dataList1;
    }

    public ArrayList<String> getDataList2() {
        return dataList2;
    }

    public ArrayList<String> getDataList3() {
        return dataList3;
    }

    public void setDataList1(ArrayList<String> dataList1) {
        this.dataList1 = dataList1;
    }

    public void setDataList2(ArrayList<String> dataList2) {
        this.dataList2 = dataList2;
    }

    public void setDataList3(ArrayList<String> dataList3) {
        this.dataList3 = dataList3;
    }
}