package com.pms.ext;

import com.pms.utils.SnowflakeIdGenerator;


public class IdGenerator implements org.activiti.engine.impl.cfg.IdGenerator {

    @Override
    public String getNextId() {
        return SnowflakeIdGenerator.getInstance().nextId() + "";
    }
}
