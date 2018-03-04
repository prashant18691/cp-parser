package com.parser.cp.model;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Task {
    private List<Question> questions;

    public Task() {

    }

    public Task(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestions(@NotNull Question question) {
        if (questions == null) questions = new LinkedList<>();
        questions.add(question);
    }
}
