package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.TestPersonnelLoginBiz;
import com.praticaltraining.rsndm.mapper.TestPersonnelLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestPersonnelLoginImpl implements TestPersonnelLoginBiz {
    @Autowired
    private TestPersonnelLoginMapper testPersonnelLoginMapper;

    @Override
    public String getPwd(String telephoneNumber ) {
        return testPersonnelLoginMapper.getPwd(telephoneNumber);
    }
}
