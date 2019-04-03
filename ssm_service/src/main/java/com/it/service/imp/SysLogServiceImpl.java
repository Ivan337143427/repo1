package com.it.service.imp;

import com.it.domain.SysLog;
import com.it.mapper.SysLogMapper;
import com.it.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
@Autowired
private SysLogMapper sysLogMapper;
    @Override
    public void save(SysLog sysLog) {
        sysLogMapper.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogMapper.findAll();
    }
}
