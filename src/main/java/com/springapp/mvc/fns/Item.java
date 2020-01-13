package com.springapp.mvc.fns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Item {

    public Item() {
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nds")
    @Expose
    private int nds;
    @SerializedName("sum")
    @Expose
    private int sum;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("paymentType")
    @Expose
    private int paymentType;
    @SerializedName("price")
    @Expose
    private int price;

    @ManyToOne(optional = true)
    @JoinColumn(name = "receipt_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Receipt receipt;

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNds() {
        return nds;
    }

    public void setNds(int nds) {
        this.nds = nds;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", nds=" + nds +
                ", sum=" + sum +
                ", quantity=" + quantity +
                ", paymentType=" + paymentType +
                ", price=" + price +
                '}';
    }
}