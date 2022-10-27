package com.example.task2attempt3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Task2Attempt3ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void myTest() {
        JSONObject rule1 = new JSONObject();
        rule1.put("firstName", "John");
        JSONObject rule2 = new JSONObject();
        rule2.put("age", "30");

        JSONArray array = new JSONArray();
        array.add(rule1);
        array.add(rule2);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("condition", array);

        Object receivedCondition = jsonObject.get("condition");
        System.out.println(receivedCondition instanceof JSONArray);

        System.out.println(receivedCondition.toString());
    }

}
