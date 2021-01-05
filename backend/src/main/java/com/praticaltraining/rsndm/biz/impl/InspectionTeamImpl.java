package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.InspectionTeamBiz;
import com.praticaltraining.rsndm.entity.InspectionTeam;
import com.praticaltraining.rsndm.exception.InspectionTeamArrangementException;
import com.praticaltraining.rsndm.exception.InspectionTeamException;
import com.praticaltraining.rsndm.mapper.InspectionTeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectionTeamImpl implements InspectionTeamBiz {
    @Autowired
    private InspectionTeamMapper inspectionTeamMapper;

    @Override
    public InspectionTeam getOneByEsId(int esId) {
        InspectionTeam res=inspectionTeamMapper.getOneByEsId(esId);
        if(res==null){
            throw new InspectionTeamArrangementException("InspectionTeam getOne error");
        }
        return res;
    }


    @Override
    public List<InspectionTeam> getAllByEduId(int eduId) {
        List<InspectionTeam> res=inspectionTeamMapper.getAllByEduId(eduId);
        if(res==null){
            throw new InspectionTeamException("InspectionTeam getAll error");
        }
        return res;
    }

    @Override
    public void clearIT(){
        int res = inspectionTeamMapper.clearIT();
        if(res == 0){
            throw new InspectionTeamException("clear IT error");
        }
    }
}
