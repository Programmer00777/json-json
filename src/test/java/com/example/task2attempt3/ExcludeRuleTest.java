package com.example.task2attempt3;

import com.example.task2attempt3.modules.ExcludeRule;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

public class ExcludeRuleTest {
    @Test
    public void TestApplyRuleSuccess() throws IOException, ParseException {
        ExcludeRule excludeRule = new ExcludeRule();
        JSONParser jsonParser = new JSONParser();
        JSONObject document = (JSONObject) jsonParser.parse(new FileReader("./src/main/resources/static/jsonDocsForTests/sample4.json"));
        JSONObject rule = new JSONObject();
        JSONObject correctResult = (JSONObject) jsonParser.parse(new FileReader("./src/main/resources/static/jsonDocsForTests/sample4ExcludeSuccessResult.json"));
        rule.put("gender", "male");
        Assertions.assertTrue(excludeRule.applyRule(document, rule).equals(correctResult));
    }
}
