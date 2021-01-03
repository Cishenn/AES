package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.InspectionTeamArrangement;
import com.praticaltraining.rsndm.mapper.InspectionTeamArrangementMapper;

import java.util.List;

public interface InspectionTeamArrangementBiz {
    List<InspectionTeamArrangement> getAllInspectionTeamArrangementOfOneInspectionTeam(int inspectionTeamId);
}
