package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.InspectionTeam;
import com.praticaltraining.rsndm.entity.InvigilatorGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InvigilatorGroupMapper {
    List<InvigilatorGroup> getAllByEduId(int eduId);
}
