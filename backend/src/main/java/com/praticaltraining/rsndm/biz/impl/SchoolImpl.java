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
    public List<School> getByEduId(int eduId){
        List<School> res = schoolMapper.getByEduId(eduId);
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

    @Override
    public void setType(int schoolId,String typeOfExaminationSite){
        int res = schoolMapper.setType(schoolId,typeOfExaminationSite);
        if(res == 0){
            throw new SchoolException("set type error");
        }
    }

    @Override
    public int getState(int schoolId){
        int res = schoolMapper.getState(schoolId);
        if(res == -1){
            throw new SchoolException("get state error");
        }
        return res;
    }

    @Override
    public List<School> getArrangedSchool(int eduId) {
        List<School> res = schoolMapper.getArrangedSchool(eduId);
        return res;
    }

    @Override
    public int getEduId(int schoolId) {
        return schoolMapper.getEduId(schoolId);
    }

}
