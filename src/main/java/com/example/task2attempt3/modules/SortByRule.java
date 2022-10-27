package com.example.task2attempt3.modules;

import com.example.task2attempt3.utils.JsonComparator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortByRule extends Rule {
    @Override
    public JSONObject applyRule(JSONObject document, JSONObject rule) {
        JSONArray dataFromDocument = (JSONArray) document.get("data");
        List<JSONObject> jsonObjectsFromDocument = dataFromDocument.stream().toList();
        JSONObject sortedDocument = new JSONObject();
        String keyToSortBy = rule.get("sort_by").toString();

        List<JSONObject> modifiableList = new ArrayList<>(jsonObjectsFromDocument);
        if (jsonObjectsFromDocument.get(0).containsKey(keyToSortBy)) {
            Collections.sort(modifiableList, new JsonComparator(keyToSortBy));
        } else {
            LOGGER.warn("SortBy: No such key in the document: " + keyToSortBy + ". Key skipped.");
        }

        sortedDocument.put("data", modifiableList);
        return sortedDocument;
    }
}
