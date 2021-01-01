package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.SchoolAdminLoginBiz;
import com.praticaltraining.rsndm.entity.SchoolAdminLogin;
import com.praticaltraining.rsndm.mapper.SchoolAdminLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolAdminLoginImpl implements SchoolAdminLoginBiz {
    @Autowired
    private SchoolAdminLoginMapper schoolAdminLoginMapper;

    @Override
    public SchoolAdminLogin getPwdSchoolId(String account) {
        return schoolAdminLoginMapper.getPwdSchoolId(account);
    }
}
