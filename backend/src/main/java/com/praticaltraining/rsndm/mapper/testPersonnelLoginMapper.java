package com.praticaltraining.rsndm.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface testPersonnelLoginMapper {
    String getPwd(String telephoneNumber);
}
