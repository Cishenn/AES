package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.InvigilatorGroup;

import java.util.List;

public interface InvigilatorGroupBiz {
    List<InvigilatorGroup> getAllByEduId(int eduId);

    List<InvigilatorGroup> getAllByHighEduId(int higherEduId);

    void clearIG(int eduId);

    void createInvigilatorGroup(int examinerId,int eduId);

    void setFirst(int firstInvigilatorId,int invigilatorGroupId);

    void setSecond(int secondInvigilatorId,int invigilatorGroupId);
}
