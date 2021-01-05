package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.ExamStaffBiz;
import com.praticaltraining.rsndm.entity.ExamRoom;
import com.praticaltraining.rsndm.entity.ExamStaff;
import com.praticaltraining.rsndm.exception.ExamRoomException;
import com.praticaltraining.rsndm.exception.ExamStaffException;
import com.praticaltraining.rsndm.mapper.ExamStaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ExamStaff getOneExamStaff(int esId){
        ExamStaff res = examStaffMapper.getOneExStaff(esId);
        if(res == null){
            throw new ExamStaffException("get one ExamStaff error");
        }
        return res;
    }

    @Override
    public void verifyPass(int esId){
        // 此处函数返回值需要int吗?
        int res = examStaffMapper.verifyPass(esId);
        if(res == 0){
            throw new ExamStaffException("verify pass examStaff error");
        }
    }

    @Override
    public void verifyNotPass(int esId){
        // 此处函数返回值需要int吗?
        int res = examStaffMapper.verifyNotPass(esId);
        if(res == 0){
            throw new ExamStaffException("verify not-pass examStaff error");
        }
    }

    @Override
    public int getEsIdByTele(String telephoneNumber) {
        // 龙哥这里自己设计异常哦
        int res = examStaffMapper.getEsIdByTele(telephoneNumber);
        if(res == 0){
            throw new ExamStaffException("get TelephoneNumber error");
        }
        return res;
    }

    @Override
    public void updateExStaff(ExamStaff examStaff){
        int res = examStaffMapper.updateExStaff(examStaff);
        if(res == 0){
            throw new ExamStaffException("update ExamStaff error");
        }
    }

    @Override
    public List<ExamStaff> getExStaffListBySchoolId(int schoolId){
        List<ExamStaff> res = examStaffMapper.getExStaffListBySchoolId(schoolId);
        if(res == null){
            throw new ExamStaffException("getAll ExamStaff error");
        }
        return res;
    }
}
