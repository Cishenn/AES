package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.EnrollmentDepartmentLogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EnrollmentDepartmentLoginMapper {
    EnrollmentDepartmentLogin getPwdEduId(String account);
}
