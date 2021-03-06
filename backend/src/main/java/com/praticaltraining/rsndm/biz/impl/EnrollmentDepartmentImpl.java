package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.EnrollmentDepartmentBiz;
import com.praticaltraining.rsndm.entity.EnrollmentDepartment;
import com.praticaltraining.rsndm.exception.EnrollmentDepartmentException;
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

    @Override
    public void setArrangeLevel(int eduId,int level){
        int res = enrollmentDepartmentMapper.setArrangeLevel(eduId,level);
        if(res == 0){
            throw new EnrollmentDepartmentException("set arrangelevel error");
        }
    }

    @Override
    public Integer getArrangeLevel(int eduId){
        int res = enrollmentDepartmentMapper.getArrangeLevel(eduId);
        if(res == -1){
            throw new EnrollmentDepartmentException("get arrangelevel error");
        }
        return res;
    }
    @Override
    public EnrollmentDepartment getOne(int eduId) {
        EnrollmentDepartment res = enrollmentDepartmentMapper.getOne(eduId);
        if(res == null){
            throw new EnrollmentDepartmentException("getOne EnrollmentDepartment error");
        }
        return res;
    }

    @Override
    public void clearArrangeLevel(int eduId){
        int res = enrollmentDepartmentMapper.clearArrangeLevel(eduId);
        if(res == 0){
            throw new EnrollmentDepartmentException("clear arrangeLevel error");
        }
    }
}
