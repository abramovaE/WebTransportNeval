package com.springapp.mvc.fns;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FnsCheck {

    @SerializedName("document")
    @Expose
    private Document document;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public FnsCheck() {
    }

    @Override
    public String toString() {
        return "FnsCheck{" +
                "document=" +
                '}';
    }
}

