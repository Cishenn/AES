package com.praticaltraining.rsndm.mapper;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchoolAdminLoginMapper {
    String getPwd(String account);
}
