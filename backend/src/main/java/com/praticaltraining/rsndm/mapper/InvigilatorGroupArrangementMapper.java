package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.InvigilatorGroupArrangement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InvigilatorGroupArrangementMapper {
    List<InvigilatorGroupArrangement> getAllInvigilatorGroupArrangementOfOneInvigilatorGroup(int invigilatorGroupId);

    List<InvigilatorGroupArrangement> getAllOfOneSchool(int schoolId);

    int clearIGA(int eduId);

    int createInvigilatorGroupArr(int invigilatorGroupId,int schoolId);

    int setRoomSessions(int exRoomId,int sessions,int igArrangeId);

    int createOther(InvigilatorGroupArrangement invigilatorGroupArrangement);

    int clearNewIGA(int eduId,int sessions);

    int resetRoomSessions(int eduId);
}
