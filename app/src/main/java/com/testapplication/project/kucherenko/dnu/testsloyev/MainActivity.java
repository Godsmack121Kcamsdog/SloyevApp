package com.testapplication.project.kucherenko.dnu.testsloyev;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.testapplication.project.kucherenko.dnu.testsloyev.client.MParser;
import com.testapplication.project.kucherenko.dnu.testsloyev.client.RetrofitClient;
import com.testapplication.project.kucherenko.dnu.testsloyev.interfaces.ILink;
import com.testapplication.project.kucherenko.dnu.testsloyev.model.Container;
import com.testapplication.project.kucherenko.dnu.testsloyev.model.Data;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Data> dataList;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ImageView backView;
    private ImageView burgerView;
    private ImageView weiterView;

    private Map<Integer, List<String>> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        backView = (ImageView) findViewById(R.id.back_view);
        burgerView = (ImageView) findViewById(R.id.burger_view);
        weiterView = (ImageView) findViewById(R.id.weiter_button);
        backView.setOnClickListener(this);
        burgerView.setOnClickListener(this);
        weiterView.setOnClickListener(this);
        dataList = new ArrayList<>();
        getData();

        //Container for info saving
        Container container = null;
        try {
            //ResponseParser
            MParser parser = new MParser(new JSONObject(responseString));
            container = parser.parse();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        map = container.getTimesMap();
        WheelView<String> date = (WheelView) findViewById(R.id.date_wheel);
        date.setWheelAdapter(new ArrayWheelAdapter(this));
        date.setSkin(WheelView.Skin.Holo);

        final WheelView<String> time = (WheelView) findViewById(R.id.times_wheel);
        time.setSkin(WheelView.Skin.Holo);
        time.setWheelAdapter(new ArrayWheelAdapter(this));
        date.setWheelData(container.getDatesList());

        WheelView.OnWheelItemSelectedListener<String> listener = new WheelView.OnWheelItemSelectedListener<String>() {
            @Override
            public void onItemSelected(int position, String s) {
                time.setWheelData(map.get(position));
            }
        };

        WheelView.OnWheelItemSelectedListener<String> listener2 = new WheelView.OnWheelItemSelectedListener<String>() {
            @Override
            public void onItemSelected(int position, String s) {
                Toast.makeText(MainActivity.this, "jhkj", Toast.LENGTH_SHORT).show();
            }
        };

        date.setOnWheelItemSelectedListener(listener);
        time.setOnWheelItemSelectedListener(listener2);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        return id == R.id.action_settings || super.onOptionsItemSelected(item);
//    }

    private void getData() {
        ILink link = RetrofitClient.getILink();
        Call<Data> call = link.getMatchData();
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Response response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_view: {
                MainActivity.this.finish();
                break;
            }
            case R.id.burger_view: {
//                new InfoRequest(MainActivity.this, null).request("http://sloyev.de/android.json");
                break;
            }
            case R.id.weiter_button: {
                Intent myIntent = new Intent(MainActivity.this, RegistrationActivity.class);
                MainActivity.this.startActivity(myIntent);
                break;
            }
        }
    }

    private static final String responseString=
            "{\n" +
            "  \"data\": {\n" +
            "    \"21-04-2017\": [\n" +
            "      \"12:00:00\",\n" +
            "      \"12:10:00\",\n" +
            "      \"12:20:00\",\n" +
            "      \"12:30:00\",\n" +
            "      \"12:40:00\",\n" +
            "      \"12:50:00\",\n" +
            "      \"13:00:00\",\n" +
            "      \"13:10:00\",\n" +
            "      \"13:20:00\",\n" +
            "      \"13:30:00\",\n" +
            "      \"13:40:00\",\n" +
            "      \"13:50:00\",\n" +
            "      \"14:00:00\",\n" +
            "      \"14:10:00\",\n" +
            "      \"14:20:00\",\n" +
            "      \"14:30:00\",\n" +
            "      \"14:40:00\",\n" +
            "      \"14:50:00\",\n" +
            "      \"15:00:00\"\n" +
            "    ],\n" +
            "    \"22-04-2017\": [\n" +
            "      \"15:50:00\",\n" +
            "      \"14:00:00\",\n" +
            "      \"14:10:00\",\n" +
            "      \"14:20:00\",\n" +
            "      \"14:30:00\",\n" +
            "      \"14:40:00\",\n" +
            "      \"14:50:00\",\n" +
            "      \"15:00:00\",\n" +
            "      \"15:10:00\",\n" +
            "      \"15:20:00\",\n" +
            "      \"15:30:00\",\n" +
            "      \"15:40:00\",\n" +
            "      \"15:50:00\",\n" +
            "      \"16:00:00\"\n" +
            "    ],\n" +
            "    \"23-04-2017\": [\n" +
            "      \"09:10:00\",\n" +
            "      \"09:20:00\",\n" +
            "      \"09:30:00\",\n" +
            "      \"09:40:00\",\n" +
            "      \"10:00:00\",\n" +
            "      \"10:20:00\",\n" +
            "      \"10:30:00\"\n" +
            "    ]\n" +
            "  }\n" +
            "}";

}
