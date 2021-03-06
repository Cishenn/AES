package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.InspectionTeamArrangement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InspectionTeamArrangementMapper {
    List<InspectionTeamArrangement> getAllInspectionTeamArrangementOfOneInspectionTeam(int inspectionTeamId);

    int clearITA(int eduId);

    int createInspectionTeamArrange(int inspectionTeamId,int schoolId,int sessions);

    List<InspectionTeamArrangement> getITAOfSchool(int schoolId);

    int setFloorId(int itArrangeId,int floorId);

    int clearITAmsg(int eduId);
}
