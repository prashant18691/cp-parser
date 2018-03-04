package com.parser.cp.model;

import java.util.List;
import java.util.Map;

public class Task {
    private Map<Integer, List<Question>> questionList;

    public Task(Map<Integer, List<Question>> questionList) {
        this.questionList = questionList;
    }

    public Map<Integer, List<Question>> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(Map<Integer, List<Question>> questionList) {
        this.questionList = questionList;
    }
}
