package com.parser.cp.impl;

import com.parser.cp.DomParser;
import com.parser.cp.exception.ImpartialException;
import com.parser.cp.model.Question;
import com.parser.cp.model.Task;
import com.parser.cp.util.Common;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

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

        Optional<Elements> inputElement = Common.getElement(document, INPUT_SELECTORS);
        if (inputElement.isPresent()) {
            question.setInput(inputElement.get().text());
        } else {
            throw new ImpartialException("Sample input was unidentified.");
        }

        Optional<Elements> outputElement = Common.getElement(document, OUTPUT_SELECTORS);
        if (outputElement.isPresent()) {
            question.setOutput(outputElement.get().text());
        } else {
            throw new ImpartialException("Sample output was unidentified.");
        }
        task.addQuestions(question);
        return task;
    }

    public Document getDocument() {
        return document;
    }
}
