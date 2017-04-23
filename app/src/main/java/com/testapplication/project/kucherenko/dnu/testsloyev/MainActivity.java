package com.testapplication.project.kucherenko.dnu.testsloyev;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.testapplication.project.kucherenko.dnu.testsloyev.client.InfoRequest;
import com.testapplication.project.kucherenko.dnu.testsloyev.client.RetrofitClient;
import com.testapplication.project.kucherenko.dnu.testsloyev.interfaces.ILink;
import com.testapplication.project.kucherenko.dnu.testsloyev.model.Data;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

        map = getDemoMap();
        WheelView<String> date = (WheelView) findViewById(R.id.date_wheel);
        date.setWheelAdapter(new ArrayWheelAdapter(this));
        date.setSkin(WheelView.Skin.Holo);

        final WheelView<String> time = (WheelView) findViewById(R.id.times_wheel);
        time.setSkin(WheelView.Skin.Holo);
        time.setWheelAdapter(new ArrayWheelAdapter(this));
        date.setWheelData(getDemoList());

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


    public List<String> getDemoList() {
        return Arrays.asList("21-04-2017", "22-04-2017", "23-04-2017");
    }

    public Map<Integer, List<String>> getDemoMap() {
        List<List<String>> times = new ArrayList<>();
        List<String> list1 = Arrays.asList("10:00", "10:30", "10:45");
        List<String> list2 = Arrays.asList("11:00", "11:30", "11:45");
        List<String> list3 = Arrays.asList("12:00", "12:30", "12:45");

        times.add(list1);
        times.add(list2);
        times.add(list3);
        Map<Integer, List<String>> map = new HashMap<>();
        for (int i = 0; i < getDemoList().size(); i++)
            map.put(i, times.get(i));
        return map;
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
                if (response.isSuccess()) {
                    Log.e(TAG, response.body().toString());
                }
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
                new InfoRequest(MainActivity.this, null).request("http://sloyev.de/android.json");
                break;
            }
            case R.id.weiter_button: {
                Intent myIntent = new Intent(MainActivity.this, RegistrationActivity.class);
                MainActivity.this.startActivity(myIntent);
                break;
            }
        }
    }

}
