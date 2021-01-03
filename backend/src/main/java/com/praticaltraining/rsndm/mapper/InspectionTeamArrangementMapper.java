package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.InspectionTeamArrangement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InspectionTeamArrangementMapper {
    List<InspectionTeamArrangement> getAllInspectionTeamArrangementOfOneInspectionTeam(int inspectionTeamId);
}
