package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.EnrollmentDepartmentLoginBiz;
import com.praticaltraining.rsndm.entity.EnrollmentDepartmentLogin;
import com.praticaltraining.rsndm.mapper.EnrollmentDepartmentLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentDepartmentLoginImpl implements EnrollmentDepartmentLoginBiz {
    @Autowired
    private EnrollmentDepartmentLoginMapper enrollmentDepartmentLoginMapper;

    @Override
    public EnrollmentDepartmentLogin getPwdEduId(String account) {
        return enrollmentDepartmentLoginMapper.getPwdEduId(account);
    }

}
