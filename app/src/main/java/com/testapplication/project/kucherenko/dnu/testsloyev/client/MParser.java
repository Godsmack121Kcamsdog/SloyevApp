package com.testapplication.project.kucherenko.dnu.testsloyev.client;

import com.testapplication.project.kucherenko.dnu.testsloyev.model.Container;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MParser {

    private Map<Integer, List<String>> timeMap;
    private List<String> dateList;
    private JSONObject json;

    public MParser(JSONObject json) throws JSONException {
        this.json = json;
        dateList = new ArrayList<>();
        timeMap = new HashMap<>();
    }

    public Container parse() throws JSONException {
        JSONObject data = json.getJSONObject("data");
        Iterator<String> iter = data.keys();
        while (iter.hasNext())
            dateList.add(iter.next());
        for (int j = 0; j < dateList.size() ; j++) {
            JSONArray arr = data.getJSONArray(dateList.get(j));
            List<String> list = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++)
                list.add(arr.getString(i));
            timeMap.put(j, list);
        }
        return new Container(dateList, timeMap);
    }
}
