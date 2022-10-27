package com.example.task2attempt3.utils;

import com.example.task2attempt3.modules.ExcludeRule;
import com.example.task2attempt3.modules.IncludeRule;
import com.example.task2attempt3.modules.SortByRule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
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
    private JSONObject updatedData;

    public ClientJsonParser(JSONObject clientJsonData) {
        this.condition = (JSONObject) clientJsonData.get("condition");
        this.updatedData = (JSONObject) clientJsonData.get("data");

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
                        updatedData = include.applyRule(updatedData, (JSONObject) includeRule);
                    }
                }
                if (ruleName.equals("exclude")) {
                    ExcludeRule exclude = new ExcludeRule();
                    for (Object excludeRule : (JSONArray) rule) {
                        updatedData = exclude.applyRule(updatedData, (JSONObject) excludeRule);
                    }
                }
                if (ruleName.equals("sort_by")) {
                    SortByRule sort = new SortByRule();
                    for (Object sortBy : (JSONArray) rule) {
                        JSONObject sortByRuleWrapper = new JSONObject();
                        sortByRuleWrapper.put("sort_by", rule);
                        updatedData = sort.applyRule(updatedData, sortByRuleWrapper);
                    }
                }
            });
        }

        JSONObject finalResult = new JSONObject();
        finalResult.put("result", updatedData);

        return finalResult;
    }

//    "condition": {
//        "exclude": [{ -json key-value pairs- }],
//        "include": [{ -json key-value pairs- }],
//        "sort_by": [keys...]
//    }
}
