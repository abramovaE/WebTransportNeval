package com.springapp.mvc.service;

import com.springapp.mvc.dao.BlockedReportDataDao;
import com.springapp.mvc.model.BlockedReportData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oem on 24.05.18.
 */

@Service
public class BlockedReportDataServiceImpl implements BlockedReportDataService {

    private BlockedReportDataDao blockedReportDataDao;

    public void setBlockedReportDataDao(BlockedReportDataDao blockedReportDataDao) {
        this.blockedReportDataDao = blockedReportDataDao;
    }

    @Override
    @Transactional
    public void save(BlockedReportData blockedReportData) {
        this.blockedReportDataDao.save(blockedReportData);
    }

    @Override
    @Transactional
    public void update(BlockedReportData blockedReportData) {
        this.blockedReportDataDao.update(blockedReportData);
    }

    @Override
    @Transactional
    public List<BlockedReportData> getAllBRD() {
        return this.blockedReportDataDao.getAllBRD();

    }

    @Override
    @Transactional
    public void deleteBRD(long id) {
        this.blockedReportDataDao.deleteBRD(id);
    }
}
