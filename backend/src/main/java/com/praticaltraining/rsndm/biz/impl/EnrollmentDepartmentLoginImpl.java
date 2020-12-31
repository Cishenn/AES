package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.EnrollmentDepartmentLoginBiz;
import com.praticaltraining.rsndm.mapper.EnrollmentDepartmentLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentDepartmentLoginImpl implements EnrollmentDepartmentLoginBiz {
    @Autowired
    private EnrollmentDepartmentLoginMapper enrollmentDepartmentLoginMapper;

    @Override
    public String getPwd(String account) {
        return enrollmentDepartmentLoginMapper.getPwd(account);
    }

}
