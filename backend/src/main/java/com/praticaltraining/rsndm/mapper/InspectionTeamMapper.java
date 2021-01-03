package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.InspectionTeam;

import java.util.List;

public interface InspectionTeamMapper {
    InspectionTeam getOneByEsId(int esId);
    List<InspectionTeam> getAllByEduId(int eduId);
}
