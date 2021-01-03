package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.InspectionTeamArrangementBiz;
import com.praticaltraining.rsndm.entity.InspectionTeamArrangement;
import com.praticaltraining.rsndm.exception.InspectionTeamArrangementException;
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
}
