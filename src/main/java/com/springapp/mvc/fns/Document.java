package com.springapp.mvc.fns;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.springapp.mvc.model.checks.Receipt;

public class Document {

    @SerializedName("receipt")
    @Expose
    private Receipt receipt;

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Document() {
    }

    @Override
    public String toString() {
        return "Document{" +
                "receipt=" + receipt +
                '}';
    }
}