package com.testapplication.project.kucherenko.dnu.testsloyev.client;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InfoRequest {

    private Context context;
    private IGetInfo info;

    public InfoRequest(Context context, IGetInfo info) {
        this.context = context;
        this.info = info;
    }

    public void request(String url) {
        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, (response != null) + "", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        RequestQueueSingleton.getInstance(context).addToRequestQueue(request);
    }

    public interface IGetInfo {
        void getInfo(ArrayList<?> infoList);
    }
}
