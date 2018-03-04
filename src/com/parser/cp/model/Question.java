package com.parser.cp.model;

public class Question {
    private String input;
    private String output;

    public Question() {

    }

    public Question(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Question{" +
                "input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
