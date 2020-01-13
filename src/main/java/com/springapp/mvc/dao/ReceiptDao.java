package com.springapp.mvc.dao;

import com.springapp.mvc.model.checks.Receipt;

/**
 * Created by oem on 28.11.18.
 */
public interface ReceiptDao {
    void saveReceipt(Receipt receipt);
}
