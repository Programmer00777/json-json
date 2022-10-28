package com.example.task2attempt3.utils;

import com.example.task2attempt3.modules.ExcludeRule;
import com.example.task2attempt3.modules.IncludeRule;
import com.example.task2attempt3.modules.SortByRule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to parse client's JSON data. It decouples received data on two parts: data itself and condition
 * with rules that have to be applied to that data. Then, condition is being decoupled further on separate types of
 * rules. At this stage, available types of rules are: include, exclude, sort_by.
 * New modules with rules can be added in here.
 */
public class ClientJsonParser {
    private JSONObject condition;
    private Object includeRule;
    private Object excludeRule;
    private Object sortByRule;
    private Map<String, Object> listOfRules = new HashMap<>();
    private JSONObject updatedData = new JSONObject();
    private JSONArray satisfyingData = new JSONArray(); // new
    private JSONObject clientData = new JSONObject();

    public ClientJsonParser(JSONObject clientJsonData) {
        this.condition = (JSONObject) clientJsonData.get("condition");
        this.clientData.put("data", clientJsonData.get("data"));

        if (condition.containsKey("include")) {
            this.includeRule = condition.get("include");
            listOfRules.put("include", includeRule);
        }

        if (condition.containsKey("exclude")) {
            this.excludeRule = condition.get("exclude");
            listOfRules.put("exclude", excludeRule);
        }

        if (condition.containsKey("sort_by")) {
            this.sortByRule = condition.get("sort_by");
            listOfRules.put("sort_by", sortByRule);
        }
    }

    public JSONObject applyRulesToJsonData() {

        if (listOfRules.size() != 0) {
            listOfRules.forEach((ruleName, rule) -> {
                if (ruleName.equals("include")) {
                    IncludeRule include = new IncludeRule();
                    for (Object includeRule : (JSONArray) rule) {
                        updatedData = include.applyRule(clientData, (JSONObject) includeRule);
                        for (Object jsonObject : (JSONArray) updatedData.get("data")) {
                            satisfyingData.add((JSONObject) jsonObject);
                        }
                    }
                }
                if (ruleName.equals("exclude")) {
                    ExcludeRule exclude = new ExcludeRule();
                    for (Object excludeRule : (JSONArray) rule) {
                        updatedData = exclude.applyRule(clientData, (JSONObject) excludeRule);
                        for (Object jsonObject : (JSONArray) updatedData.get("data")) {
                            satisfyingData.add((JSONObject) jsonObject);
                        }
                    }
                }
                if (ruleName.equals("sort_by")) {
                    SortByRule sort = new SortByRule();
                    for (Object sortBy : (JSONArray) rule) {
                        JSONObject dataToSort = new JSONObject();
                        dataToSort.put("data", satisfyingData);
                        JSONObject sortByRuleWrapper = new JSONObject();
                        sortByRuleWrapper.put("sort_by", sortBy);
                        JSONArray jsonArrayWrapper = new JSONArray();
                        for (Object object : (JSONArray) sort.applyRule(dataToSort, sortByRuleWrapper).get("data")) {
                            jsonArrayWrapper.add(object);
                        }
                        satisfyingData = jsonArrayWrapper;
                    }
                }
            });
        }

        JSONObject finalResult = new JSONObject();
        finalResult.put("result", satisfyingData);

        return finalResult;
    }

//    "condition": {
//        "exclude": [{ -json key-value pairs- }],
//        "include": [{ -json key-value pairs- }],
//        "sort_by": [keys...]
//    }
}
