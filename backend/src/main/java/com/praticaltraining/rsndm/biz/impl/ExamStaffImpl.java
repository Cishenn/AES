package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.ExamStaffBiz;
import com.praticaltraining.rsndm.entity.ExamStaff;
import com.praticaltraining.rsndm.exception.ExamStaffException;
import com.praticaltraining.rsndm.mapper.ExamStaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamStaffImpl implements ExamStaffBiz {
    @Autowired
    private ExamStaffMapper examStaffMapper;
    @Override
    public void register(ExamStaff examStaff) {
        int res=examStaffMapper.register(examStaff);
        if(res==0){
            throw new ExamStaffException("ExamStaff register error");
        }
    }

    @Override
    public String getName(int esId) {
        String res=examStaffMapper.getName(esId);
        if(res==null){
            throw new ExamStaffException("ExamStaff getName error");
        }
        return res;
    }
}
