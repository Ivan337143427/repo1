package com.it.service;

import com.it.domain.SysLog;

import java.util.List;

public interface SysLogService {
    void save(SysLog sysLog);
    List<SysLog> findAll();
}
