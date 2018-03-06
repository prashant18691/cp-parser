package com.parser.cp.util;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Optional;

public class Common {

    public final static Optional<Elements> getElement(Document document, List<String> filterList) {
        for (String filter : filterList) {
            Elements element = document.select(filter);
            return Optional.of(element);
        }
        return Optional.empty();
    }
}
