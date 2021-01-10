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
    public List<InvigilatorGroupArrangement> getAllOfOneSchool(int schoolId) {
        List<InvigilatorGroupArrangement> res=invigilatorGroupArrangementMapper.getAllOfOneSchool(schoolId);
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

    @Override
    public void setRoomSessions(int exRoomId, int sessions,int igArrangeId) {
        int res = invigilatorGroupArrangementMapper.setRoomSessions(exRoomId,sessions,igArrangeId);
        if(res == 0){
            throw new InvigilatorGroupArrangementException("set IGA error");
        }
    }

    @Override
    public void createOther(InvigilatorGroupArrangement invigilatorGroupArrangement) {
        int res = invigilatorGroupArrangementMapper.createOther(invigilatorGroupArrangement);
        if(res == 0){
            throw new InvigilatorGroupArrangementException("creat other IGA error");
        }
    }

    @Override
    public void clearNewIGA(int eduId,int sessions) {
        invigilatorGroupArrangementMapper.clearNewIGA(eduId,sessions);
    }

    @Override
    public void resetRoomSessions(int eduId) {
        invigilatorGroupArrangementMapper.resetRoomSessions(eduId);
    }
}
