package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.testBiz;
import com.praticaltraining.rsndm.entity.test;
import com.praticaltraining.rsndm.mapper.testMapper;
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
