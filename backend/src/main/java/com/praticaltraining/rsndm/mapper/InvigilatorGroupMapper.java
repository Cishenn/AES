package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.InvigilatorGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InvigilatorGroupMapper {
    List<InvigilatorGroup> getAllByEduId(int eduId);

    List<InvigilatorGroup> getAllByHighEduId(int higherEduId);

    int clearIG(int eduId);

    int createInvigilatorGroup(int examinerId,int eduId);

    int setFirst(int firstInvigilatorId,int invigilatorGroupId);

    int setSecond(int secondInvigilatorId,int invigilatorGroupId);

    InvigilatorGroup getOneByEsId(int esId);
}
