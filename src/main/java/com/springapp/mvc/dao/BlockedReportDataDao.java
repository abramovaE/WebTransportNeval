package com.springapp.mvc.dao;

import com.springapp.mvc.model.BlockedReportData;

import java.util.List;

/**
 * Created by oem on 24.05.18.
 */
public interface BlockedReportDataDao {

    void save(BlockedReportData blockedReportData);
    void update(BlockedReportData blockedReportData);
    void deleteBRD(long id);

    List<BlockedReportData> getAllBRD();
}
