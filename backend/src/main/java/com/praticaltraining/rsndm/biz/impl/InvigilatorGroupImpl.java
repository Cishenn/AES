package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.InvigilatorGroupBiz;
import com.praticaltraining.rsndm.entity.InvigilatorGroup;
import com.praticaltraining.rsndm.exception.InvigilatorGroupException;
import com.praticaltraining.rsndm.mapper.InvigilatorGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvigilatorGroupImpl implements InvigilatorGroupBiz {
    @Autowired
    private InvigilatorGroupMapper invigilatorGroupMapper;

    @Override
    public List<InvigilatorGroup> getAllByEduId(int eduId) {
        List<InvigilatorGroup> res=invigilatorGroupMapper.getAllByEduId(eduId);
        if(res==null){
            throw new InvigilatorGroupException("InvigilatorGroup getAll error");
        }
        return res;
    }

    @Override
    public List<InvigilatorGroup> getAllByHighEduId(int higherEduId) {
        List<InvigilatorGroup> res=invigilatorGroupMapper.getAllByHighEduId(higherEduId);
        if(res==null){
            throw new InvigilatorGroupException("InvigilatorGroup getAllbyHigherEduId error");
        }
        return res;
    }

    @Override
    public void clearIG(int eduId){
        invigilatorGroupMapper.clearIG(eduId);
    }

    @Override
    public void createInvigilatorGroup(int examinerId, int eduId) {
        int res = invigilatorGroupMapper.createInvigilatorGroup(examinerId,eduId);
        if(res == 0){
            throw new InvigilatorGroupException("create IG error");
        }
    }

    @Override
    public void setFirst(int firstInvigilatorId,int invigilatorGroupId) {
        int res = invigilatorGroupMapper.setFirst(firstInvigilatorId,invigilatorGroupId);
        if(res == 0){
            throw new InvigilatorGroupException("set IG first error");
        }
    }

    @Override
    public void setSecond(int secondInvigilatorId,int invigilatorGroupId) {
        int res = invigilatorGroupMapper.setSecond(secondInvigilatorId,invigilatorGroupId);
        if(res == 0){
            throw new InvigilatorGroupException("set IG second error");
        }
    }
}
