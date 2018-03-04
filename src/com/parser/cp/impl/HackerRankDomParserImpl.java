package com.parser.cp.impl;

import com.parser.cp.DomParser;
import com.parser.cp.model.Task;

import java.util.logging.Logger;

public class HackerRankDomParserImpl implements DomParser {
    private static final Logger LOGGER = Logger.getLogger(HackerRankDomParserImpl.class.getSimpleName());

    @Override
    public Task parse(String domAsString) {
        LOGGER.info(domAsString);
        return null;
    }
}
