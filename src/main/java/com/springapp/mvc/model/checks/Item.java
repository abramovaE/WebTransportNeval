package com.springapp.mvc.model.checks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.springapp.mvc.model.Model;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

/**
 * Created by oem on 28.11.18.
 */

@Entity
@Table(name = "item")
public class Item extends Model {






        @Column(name = "name")
        @SerializedName("name")
        @Expose
        private String name;

        @Column(name = "nds")
        @SerializedName("nds")
        @Expose
        private int nds;

        @Column(name = "sum")
        @SerializedName("sum")
        @Expose
        private int sum;

        @Column(name = "quantity")
        @SerializedName("quantity")
        @Expose
        private int quantity;


        @Column(name = "paymentType")
        @SerializedName("paymentType")
        @Expose
        private int paymentType;

        @Column(name = "price")
        @SerializedName("price")
        @Expose
        private int price;



        @ManyToOne
        @JoinColumn(name = "receipt_id")
        @LazyCollection(LazyCollectionOption.FALSE)
        private Receipt receipt;

    public Item() {
        super();
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

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}




