package com.example.task2attempt3;

import com.example.task2attempt3.modules.IncludeRule;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileReader;
import java.io.IOException;

public class IncludeRuleTest {

    @Test
    void testApplyRuleSuccess() throws IOException, ParseException {
        IncludeRule includeRule = new IncludeRule();
        JSONParser jsonParser = new JSONParser();
        JSONObject document = (JSONObject) jsonParser.parse(new FileReader("./src/main/resources/static/jsonDocsForTests/sample4.json"));
        JSONObject rule = new JSONObject();
        JSONObject correctResult = (JSONObject) jsonParser.parse(new FileReader("./src/main/resources/static/jsonDocsForTests/sample4IncludeSuccessResult.json"));
        rule.put("firstName", "Joe");
        Assertions.assertTrue(includeRule.applyRule(document, rule).equals(correctResult));
    }
}
