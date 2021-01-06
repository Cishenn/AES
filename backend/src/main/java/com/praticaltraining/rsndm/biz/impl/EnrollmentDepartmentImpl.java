package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.EnrollmentDepartmentBiz;
import com.praticaltraining.rsndm.mapper.EnrollmentDepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentDepartmentImpl implements EnrollmentDepartmentBiz {
    @Autowired
    private EnrollmentDepartmentMapper enrollmentDepartmentMapper;

    @Override
    public List<Integer> eduIdAllBelong(int eduId) {
        return enrollmentDepartmentMapper.eduIdAllBelong(eduId);
    }
}
