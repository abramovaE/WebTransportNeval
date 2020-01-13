package com.springapp.mvc.service;

import com.springapp.mvc.dao.ChangesLogDao;
import com.springapp.mvc.model.ChangesLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by oem on 22.05.18.
 */

@Service
public class ChangesLogServiceImpl implements ChangesLogService {

    private ChangesLogDao changesLogDao;

    public void setChangesLogDao(ChangesLogDao changesLogDao) {
        this.changesLogDao = changesLogDao;
    }

    @Override
    @Transactional
    public void save(ChangesLog changesLog) {
        this.changesLogDao.save(changesLog);
    }
}
