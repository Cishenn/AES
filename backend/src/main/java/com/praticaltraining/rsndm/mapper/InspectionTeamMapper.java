package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.InspectionTeam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InspectionTeamMapper {
    InspectionTeam getOneByEsId(int esId);

    List<InspectionTeam> getAllByEduId(int eduId);

    int clearIT();
}
