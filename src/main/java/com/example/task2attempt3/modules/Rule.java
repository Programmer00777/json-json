package com.example.task2attempt3.modules;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An abstract classes that must be extended by all the modules with concrete rules which wil be applied
 * to JSON data.
 */
public abstract class Rule {
    protected static final Logger LOGGER = LoggerFactory.getLogger(Rule.class);
    protected abstract JSONObject applyRule(JSONObject document, JSONObject rule);
}