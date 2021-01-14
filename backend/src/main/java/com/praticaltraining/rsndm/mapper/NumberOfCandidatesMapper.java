package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.NumberOfCandidates;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NumberOfCandidatesMapper {
    NumberOfCandidates numberOfCandidatesGet(int eduId);

    int NumberOfCandidatesUpdate(NumberOfCandidates numberOfCandidates);

    NumberOfCandidates edu2GetNum(int eduId);

    NumberOfCandidates edu3GetNum(int eduId);
}
