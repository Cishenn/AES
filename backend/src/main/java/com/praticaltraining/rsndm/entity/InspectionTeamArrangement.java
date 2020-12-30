package com.praticaltraining.rsndm.entity;

import lombok.Data;

@Data
public class InspectionTeamArrangement{
    int itArrangeId;
    int inspectionTeamId;
    int schoolId;
    int floorId;
    int sessions;
}