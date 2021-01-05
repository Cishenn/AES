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
    public void clearIG(){
        int res = invigilatorGroupMapper.clearIG();
        if(res == 0){
            throw new InvigilatorGroupException("Clear IG error");
        }
    }
}
