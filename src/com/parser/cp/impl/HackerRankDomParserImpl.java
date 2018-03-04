package com.parser.cp.impl;

import com.parser.cp.DomParser;
import com.parser.cp.exception.ImpartialException;
import com.parser.cp.model.Question;
import com.parser.cp.model.Task;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class HackerRankDomParserImpl implements DomParser {
    private static final Logger LOGGER = Logger.getLogger(HackerRankDomParserImpl.class.getSimpleName());
    private static final List<String> INPUT_SELECTORS = new LinkedList<>();
    private static final List<String> OUTPUT_SELECTORS = new LinkedList<>();
    private Document document;

    static {
        INPUT_SELECTORS.add("#content div.challenge_sample_input_body");
        OUTPUT_SELECTORS.add("#content div.challenge_sample_output_body");
    }

    public HackerRankDomParserImpl() {

    }

    public HackerRankDomParserImpl(Document document) {
        this.document = document;
    }

    @Override
    public Task parse(String domAsString) throws ImpartialException {
        Task task = new Task();
        Question question = new Question();
        Document document = Jsoup.parse(domAsString);

        Optional<Elements> inputElement = getInputElement(document);
        if (inputElement.isPresent()) {
            question.setInput(inputElement.get().text());
        } else {
            throw new ImpartialException("Sample input was unidentified.");
        }

        Optional<Elements> outputElement = getOutputElement(document);
        if (outputElement.isPresent()) {
            question.setOutput(outputElement.get().text());
        } else {
            throw new ImpartialException("Sample output was unidentified.");
        }
        task.addQuestions(question);
        return task;
    }

    private static Optional<Elements> getInputElement(Document document) {
        for (String filter : INPUT_SELECTORS) {
            Elements element = document.select(filter);
            return Optional.of(element);
        }
        return Optional.empty();
    }

    private static Optional<Elements> getOutputElement(Document document) {
        for (String filter : OUTPUT_SELECTORS) {
            Elements element = document.select(filter);
            return Optional.of(element);
        }
        return Optional.empty();
    }

    public Document getDocument() {
        return document;
    }
}
