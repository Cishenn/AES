package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.InvigilatorGroupArrangement;

import java.util.List;

public interface InvigilatorGroupArrangementBiz {
    List<InvigilatorGroupArrangement> getAllInvigilatorGroupArrangementOfOneInvigilatorGroup(int invigilatorGroupId);

    List<InvigilatorGroupArrangement> getAllOfOneSchool(int schoolId);

    void clearIGA(int eduId);

    void createInvigilatorGroupArr(int invigilatorGroupId,int schoolId);

    void setRoomSessions(int exRoomId,int sessions,int igArrangeId);

    void createOther(InvigilatorGroupArrangement invigilatorGroupArrangement);

    void clearNewIGA(int eduId,int sessions);

    void resetRoomSessions(int eduId);
}
