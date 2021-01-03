package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.SchoolBiz;
import com.praticaltraining.rsndm.entity.School;
import com.praticaltraining.rsndm.exception.ExamStaffException;
import com.praticaltraining.rsndm.exception.SchoolException;
import com.praticaltraining.rsndm.mapper.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SchoolImpl implements SchoolBiz {
    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public List<School> getAll(){
        List<School> res = schoolMapper.getAll();
        if(res == null){
            throw new SchoolException("getAll school error");
        }
        return res;
    }

    @Override
    public void examineExRoom(int schoolId,int exRoomExamine){
        int res = schoolMapper.examineExRoom(schoolId,exRoomExamine);
        if(res==0){
            throw new SchoolException("update school error");
        }
    }

    @Override
    public void submitExRoom(int schoolId){
        int res = schoolMapper.submitExRoom(schoolId);
        if(res==0){
            throw new SchoolException("update school error");
        }
    }

    @Override
    public String getSchoolName(int schoolId) {
        String res=schoolMapper.getSchoolName(schoolId);
        if(res==null){
            throw new SchoolException("School getName error");
        }
        return res;
    }


}
