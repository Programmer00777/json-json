package com.example.task2attempt3.modules;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * Module called ExcludeRule. Extends an abstract class Rule.
 * Used to exclude JSON data by key-value pairs.
 */
public class ExcludeRule extends Rule {
    @Override
    public JSONObject applyRule(JSONObject document, JSONObject rule) {
        List<String> keys = rule.keySet().stream().toList();
        List<String> values = rule.values().stream().toList();
        JSONArray dataFromDocument = (JSONArray) document.get("data");
        JSONObject newDocument = new JSONObject();
        JSONArray jsonObjectsOfNewDocument = new JSONArray();

        for (int i = 0; i < keys.size(); i++) {
            String currentKey = keys.get(i);
            String currentValue = values.get(i);

            if (((JSONObject) dataFromDocument.get(0)).containsKey(currentKey)) {
                for (Object jsonObject : dataFromDocument) {
                    if (!(((JSONObject) jsonObject).get(currentKey).equals(currentValue))) {
                        jsonObjectsOfNewDocument.add(jsonObject);
                    }
                }
            } else {
                LOGGER.warn("Exclude: There is no such key in the document: " + currentKey + ". Rule skipped.");
            }
        }

        newDocument.put("data", jsonObjectsOfNewDocument);
        return newDocument;
    }
}
