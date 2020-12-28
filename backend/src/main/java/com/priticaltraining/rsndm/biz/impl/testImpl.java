package com.priticaltraining.rsndm.biz.impl;

import com.priticaltraining.rsndm.biz.testBiz;
import com.priticaltraining.rsndm.entity.test;
import com.priticaltraining.rsndm.mapper.testMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class testImpl implements testBiz {

    @Autowired
    private testMapper testMapper;

    @Override
    public List<test> getTest() {
        return testMapper.getTest();
    }
}
