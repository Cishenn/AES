package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.InspectionTeamArrangementBiz;
import com.praticaltraining.rsndm.entity.InspectionTeamArrangement;
import com.praticaltraining.rsndm.exception.InspectionTeamArrangementException;
import com.praticaltraining.rsndm.exception.InspectionTeamException;
import com.praticaltraining.rsndm.exception.InvigilatorGroupArrangementException;
import com.praticaltraining.rsndm.mapper.InspectionTeamArrangementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectionTeamArrangementImpl implements InspectionTeamArrangementBiz {
    @Autowired
    private InspectionTeamArrangementMapper inspectionTeamArrangementMapper;

    @Override
    public List<InspectionTeamArrangement> getAllInspectionTeamArrangementOfOneInspectionTeam(int inspectionTeamId) {
        List<InspectionTeamArrangement> res=inspectionTeamArrangementMapper.getAllInspectionTeamArrangementOfOneInspectionTeam(inspectionTeamId);
        if(res==null){
            throw new InspectionTeamArrangementException("InspectionTeamArrangement get error");
        }
        return res;
    }

    @Override
    public void clearITA(int eduId){
        inspectionTeamArrangementMapper.clearITA(eduId);
    }

    @Override
    public void createInspectionTeamArrange(int inspectionTeamId, int schoolId,int sessions) {
        int res = inspectionTeamArrangementMapper.createInspectionTeamArrange(inspectionTeamId,schoolId,sessions);
        if(res == 0){
            throw new InspectionTeamArrangementException("create ITA error");
        }
    }

    @Override
    public List<InspectionTeamArrangement> getITAOfSchool(int schoolId){
        List<InspectionTeamArrangement> res = inspectionTeamArrangementMapper.getITAOfSchool(schoolId);
        return res;
    }

    @Override
    public void setFloorId(int itArrangeId,int floorId){
        int res = inspectionTeamArrangementMapper.setFloorId(itArrangeId,floorId);
        if(res == 0){
            throw new InspectionTeamArrangementException("set floorId error");
        }
    }

    @Override
    public void clearITAmsg(int eduId){
        int res = inspectionTeamArrangementMapper.clearITAmsg(eduId);
        if(res == -1){
            throw new InspectionTeamArrangementException("clear floorid error");
        }
    }
}
