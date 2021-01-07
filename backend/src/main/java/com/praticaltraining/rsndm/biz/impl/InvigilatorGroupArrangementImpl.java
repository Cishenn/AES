package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.InvigilatorGroupArrangementBiz;
import com.praticaltraining.rsndm.entity.InvigilatorGroupArrangement;
import com.praticaltraining.rsndm.exception.InvigilatorGroupArrangementException;
import com.praticaltraining.rsndm.mapper.InvigilatorGroupArrangementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvigilatorGroupArrangementImpl implements InvigilatorGroupArrangementBiz {
    @Autowired
    private InvigilatorGroupArrangementMapper invigilatorGroupArrangementMapper;

    @Override
    public List<InvigilatorGroupArrangement> getAllInvigilatorGroupArrangementOfOneInvigilatorGroup(int invigilatorGroupId) {
        List<InvigilatorGroupArrangement> res=invigilatorGroupArrangementMapper.getAllInvigilatorGroupArrangementOfOneInvigilatorGroup(invigilatorGroupId);
        if(res==null){
            throw new InvigilatorGroupArrangementException("InvigilatorGroupArrangement get error");
        }
        return res;
    }

    @Override
    public void clearIGA(int eduId){
        invigilatorGroupArrangementMapper.clearIGA(eduId);
    }

    @Override
    public void createInvigilatorGroupArr(int invigilatorGroupId, int schoolId) {
        int res = invigilatorGroupArrangementMapper.createInvigilatorGroupArr(invigilatorGroupId,schoolId);
        if(res == 0){
            throw new InvigilatorGroupArrangementException("create IGA error");
        }
    }
}
