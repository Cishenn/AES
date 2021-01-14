package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.NumberOfCandidates;

import java.util.List;

public interface NumberOfCandidatesBiz {
    NumberOfCandidates numberOfCandidatesGet(int eduId);

    void NumberOfCandidatesUpdate(NumberOfCandidates numberOfCandidates);

    NumberOfCandidates edu2GetNum(int eduId);

    NumberOfCandidates edu3GetNum(int eduId);
}
