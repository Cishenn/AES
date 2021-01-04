package com.praticaltraining.rsndm.entity;

import lombok.Data;

@Data
public class InspectionTeam{
    int inspectionTeamId;
    int firstInspectionPersonId;
    int secondInspectionPersonId;
    int eduId;
}