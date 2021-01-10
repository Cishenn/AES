package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.InspectionTeamArrangement;
import com.praticaltraining.rsndm.mapper.InspectionTeamArrangementMapper;

import java.util.List;

public interface InspectionTeamArrangementBiz {
    List<InspectionTeamArrangement> getAllInspectionTeamArrangementOfOneInspectionTeam(int inspectionTeamId);

    void clearITA(int eduId);

    void createInspectionTeamArrange(int inspectionTeamId,int schoolId,int sessions);

    List<InspectionTeamArrangement> getITAOfSchool(int schoolId);

    void setFloorId(int itArrangeId,int floorId);

    void clearITAmsg(int eduId);
}
