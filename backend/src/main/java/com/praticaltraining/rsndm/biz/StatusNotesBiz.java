package com.praticaltraining.rsndm.biz;


import com.praticaltraining.rsndm.entity.StatusNotes;

import java.util.List;

public interface StatusNotesBiz {
    List<StatusNotes> getAllByEsId(int esId);

    List<StatusNotes> getAllBySchool(int schoolId);

    void updateAuditState(int stateId,int auditState);
}
