package com.example.task2attempt3.service;

import com.example.task2attempt3.utils.ClientJsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * JSON Service is used to accept the JSON file, read it and return the result (JSON data) after applying the rules
 * (if there's any).
 */
@Service
public class JsonService {
    private final static Logger LOGGER = LoggerFactory.getLogger(JsonService.class);
    private ClientJsonParser parser;

    public String parseDataApplyRulesAndGetResult(File file) {
        if (file.getName().endsWith(".json")) {
            JSONParser jsonParser = new JSONParser();
            try {
                parser = new ClientJsonParser((JSONObject) jsonParser.parse(new FileReader(file)));
                return parser.applyRulesToJsonData().toJSONString();
            } catch (IOException e) {
                LOGGER.error("Some error happened while reading the file!");
                return "{'error': 'Some error happened while reading the file!'}";
            } catch (ParseException e) {
                LOGGER.error("Bad JSON data! (data incorrect or corrupted)");
                return "{'error': 'Bad JSON data! (data incorrect or corrupted)}";
            }

        } else {
            LOGGER.error("File's extension isn't .json!");
            return "{'error': 'File's extension isn't .json!'}";
        }
    }
}
