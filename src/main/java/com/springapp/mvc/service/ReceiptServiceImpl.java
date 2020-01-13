package com.springapp.mvc.service;

import com.springapp.mvc.dao.ReceiptDao;
import com.springapp.mvc.model.checks.Receipt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.SecondaryTable;

/**
 * Created by oem on 28.11.18.
 */
@Service
public class ReceiptServiceImpl implements ReceiptService {

    private ReceiptDao receiptDao;


    public void setReceiptDao(ReceiptDao receiptDao) {
        this.receiptDao = receiptDao;
    }

    @Override
    @Transactional
    public void saveReceipt(Receipt receipt) {
        this.receiptDao.saveReceipt(receipt);

    }
}
