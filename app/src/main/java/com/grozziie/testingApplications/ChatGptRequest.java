package com.grozziie.testingApplications;

public class ChatGptRequest {
    private String inputText;

    public ChatGptRequest(String inputText) {
        this.inputText = inputText;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }
}