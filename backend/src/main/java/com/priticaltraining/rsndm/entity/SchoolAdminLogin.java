package com.priticaltraining.rsndm.entity;

import lombok.Data;

@Data
public class SchoolAdminLogin {
    String account;
    String password;
    int schoolId;
}
