package com.testapplication.project.kucherenko.dnu.testsloyev.interfaces;

import com.testapplication.project.kucherenko.dnu.testsloyev.model.Data;

import retrofit.Call;
import retrofit.http.GET;

public interface ILink {

    @GET("/android.json")
    Call<Data> getMatchData();
}
