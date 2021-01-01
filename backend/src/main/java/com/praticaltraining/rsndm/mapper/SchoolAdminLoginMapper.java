package com.praticaltraining.rsndm.mapper;


import com.praticaltraining.rsndm.entity.SchoolAdminLogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchoolAdminLoginMapper {
    SchoolAdminLogin getPwdSchoolId(String account);
}
