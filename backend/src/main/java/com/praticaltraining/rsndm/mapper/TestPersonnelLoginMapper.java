package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.TestPersonnelLogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestPersonnelLoginMapper {
    String getPwd(String telephoneNumber);
    int register(String telephoneNumber,String password);
}
