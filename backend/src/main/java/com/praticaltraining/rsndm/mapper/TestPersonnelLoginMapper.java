package com.praticaltraining.rsndm.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestPersonnelLoginMapper {
    String getPwd(String telephoneNumber);
}