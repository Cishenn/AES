package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.InvigilatorGroup;

import java.util.List;

public interface InvigilatorGroupBiz {
    List<InvigilatorGroup> getAllByEduId(int eduId);

    void clearIG();
}
