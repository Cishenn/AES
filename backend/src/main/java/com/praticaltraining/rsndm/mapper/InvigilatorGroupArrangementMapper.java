package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.InvigilatorGroupArrangement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InvigilatorGroupArrangementMapper {
    List<InvigilatorGroupArrangement> getAllInvigilatorGroupArrangementOfOneInvigilatorGroup(int invigilatorGroupId);

    int clearIGA();
}
