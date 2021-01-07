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

    @Override
    public void updateEduExamine(int esId, int eduExamine){
        // 此处函数返回值需要int吗?
        int res = examStaffMapper.updateEduExamine(esId, eduExamine);
        if(res == 0){
            throw new ExamStaffException("update EduExamine examStaff error");
        }
    }

    @Override
    public List<ExamStaff> getQualifiedExStaffList(int eduId){
        List<ExamStaff> res = examStaffMapper.getQualifiedExStaffList(eduId);
        if(res == null){
            throw new ExamStaffException("get Qualified ExStaff List ExamStaff error");
        }
        return res;
    }

    @Override
    public void clearArrange(int eduId){
        examStaffMapper.clearArrange(eduId);
    }

    @Override
    public void updateToArrange(int esId) {
        int res = examStaffMapper.updateToArrange(esId);
        if(res == 0){
            throw new ExamStaffException("update arrange error");
        }
    }

    @Override
    public List<ExamStaff> allNoArrangeGetByEduId(int eduId) {
        List<ExamStaff> res = examStaffMapper.allNoArrangeGetByEduId(eduId);
        if(res == null){
            throw new ExamStaffException("get allNoArrange ExStaff List error");
        }
        return res;
    }

    @Override
    public int getSchoolId(int esId) {
        return examStaffMapper.getSchoolId(esId);
    }

    @Override
    public List<ExamStaff> getQualifiedExStaffBySchoolId(int schoolId) {
        List<ExamStaff> res = examStaffMapper.getQualifiedExStaffBySchoolId(schoolId);
        if(res == null){
            throw new ExamStaffException("get QualifiedExStaffBySchoolId ExStaff List error");
        }
        return res;
    }

    @Override
    public List<ExamStaff> getExStaffListVerifying(int schoolId){
        List<ExamStaff> res = examStaffMapper.getExStaffListVerifying(schoolId);
        if(res == null){
            throw new ExamStaffException("get ExamStaff Verifying error");
        }
        return res;
    }

    @Override
    public List<ExamStaff> getExStaffListVerified(int schoolId){
        List<ExamStaff> res = examStaffMapper.getExStaffListVerified(schoolId);
        if(res == null){
            throw new ExamStaffException("get ExamStaff Verified error");
        }
        return res;
    }

    @Override
    public void updateSchoolExamine(int esId, int schoolExamine){
        // 此处函数返回值需要int吗?
        int res = examStaffMapper.updateSchoolExamine(esId, schoolExamine);
        if(res == 0){
            throw new ExamStaffException("update SchoolExamine examStaff error");
        }
    }

    @Override
    public void updateRejection(int esId, String finalRejection){
        // 此处函数返回值需要int吗?
        int res = examStaffMapper.updateRejection(esId, finalRejection);
        if(res == 0){
            throw new ExamStaffException("update finalRejection examStaff error");
        }
    }
}
