package com.example.task2attempt3.utils;

import org.json.simple.JSONObject;

import java.util.Comparator;

/**
 * JSON Comparator is used to compare JSON objects by keys.
 */
public class JsonComparator implements Comparator<JSONObject> {
    private String keyToSortBy;

    public JsonComparator(String keyToSortBy) {
        this.keyToSortBy = keyToSortBy;
    }

    @Override
    public int compare(JSONObject o1, JSONObject o2) {
        String ob1PropValue = o1.get(keyToSortBy).toString();
        String ob2PropValue = o2.get(keyToSortBy).toString();

        return ob1PropValue.compareTo(ob2PropValue);
    }
}
