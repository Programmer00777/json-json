package com.example.task2attempt3;

import com.example.task2attempt3.modules.SortByRule;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

public class SortByRuleTest {
    @Test
    void testSortByRuleSuccessAge() throws IOException, ParseException  {
        SortByRule sortByRule = new SortByRule();
        JSONParser jsonParser = new JSONParser();
        JSONObject document = (JSONObject) jsonParser.parse(new FileReader("./src/main/resources/static/jsonDocsForTests/sample4.json"));
        JSONObject correctResult = (JSONObject) jsonParser.parse(new FileReader("./src/main/resources/static/jsonDocsForTests/sample4SortBySuccessResult.json"));
        JSONObject rule = new JSONObject();
        rule.put("sort_by", "age");
        Assertions.assertTrue(sortByRule.applyRule(document, rule).equals(correctResult));
    }
}
