package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.NumberOfCandidates;

public interface NumberOfCandidatesBiz {
    NumberOfCandidates numberOfCandidatesGet(int eduId);
    void NumberOfCandidatesUpdate(NumberOfCandidates numberOfCandidates);
}
