package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.InvigilatorGroupArrangement;

import java.util.List;

public interface InvigilatorGroupArrangementBiz {
    List<InvigilatorGroupArrangement> getAllInvigilatorGroupArrangementOfOneInvigilatorGroup(int invigilatorGroupId);

    void clearIGA();
}
