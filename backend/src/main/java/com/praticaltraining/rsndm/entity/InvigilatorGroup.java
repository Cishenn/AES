package com.praticaltraining.rsndm.entity;

import lombok.Data;

@Data
public class InvigilatorGroup{
    int invigilatorGroupId;
    int examinerId;
    int firstInvigilatorId;
    int secondInvigilatorId;
    int eduId;
}