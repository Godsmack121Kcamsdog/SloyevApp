package com.testapplication.project.kucherenko.dnu.testsloyev.model;

import java.util.List;
import java.util.Map;

public class Container {

    private List<String> datesList;
    private Map<Integer,List<String>> timesMap;

    public Container(List<String> datesList, Map<Integer, List<String>> timesMap) {
        this.datesList = datesList;
        this.timesMap = timesMap;
    }

    public List<String> getDatesList() {
        return datesList;
    }

    public void setDatesList(List<String> datesList) {
        this.datesList = datesList;
    }

    public Map<Integer, List<String>> getTimesMap() {
        return timesMap;
    }

    public void setTimesMap(Map<Integer, List<String>> timesMap) {
        this.timesMap = timesMap;
    }
}
