package com.springapp.mvc.model;



/**
 * Created by oem on 12.12.17.
 */


public class Message {

    private String text;

    private String subject;

    private boolean copyToManager;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isCopyToManager() {
        return copyToManager;
    }

    public void setCopyToManager(boolean copyToManager) {
        this.copyToManager = copyToManager;
    }

    public Message() {
    }
}
