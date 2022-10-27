package com.example.task2attempt3.modules;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Rule {
    protected static final Logger LOGGER = LoggerFactory.getLogger(Rule.class);
    protected abstract JSONObject applyRule(JSONObject document, JSONObject rule);
}