package com.praticaltraining.rsndm.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EnrollmentDepartmentLoginMapper {
    String getPwd(String account);
}
