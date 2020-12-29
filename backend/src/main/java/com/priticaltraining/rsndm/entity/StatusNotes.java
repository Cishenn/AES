package com.priticaltraining.rsndm.entity;

import lombok.Data;

@Data
public class StatusNotes {
    int stateId;
    int pepId;
    String stateMessage;
    String auditState;
}
