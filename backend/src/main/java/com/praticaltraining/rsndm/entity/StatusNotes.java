package com.praticaltraining.rsndm.entity;

import lombok.Data;

@Data
public class StatusNotes {
    int stateId;
    int esId;
    String stateMessage;
    String auditState;
    String fromName;
}
