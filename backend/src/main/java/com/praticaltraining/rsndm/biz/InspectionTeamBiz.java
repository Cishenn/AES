package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.InspectionTeam;

import java.util.List;

public interface InspectionTeamBiz {
    InspectionTeam getOneByEsId(int esId);

    List<InspectionTeam> getAllByEduId(int eduId);

    void clearIT(int eduId);

    void createInspectionTeam(int firstInspectionPersonId,int secondInspectionPersonId,int eduId);
}
