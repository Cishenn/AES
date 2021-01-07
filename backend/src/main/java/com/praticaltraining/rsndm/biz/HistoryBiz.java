package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.History;

import java.util.List;

public interface HistoryBiz {
    List<History> getEsHistory(int esId);
}
