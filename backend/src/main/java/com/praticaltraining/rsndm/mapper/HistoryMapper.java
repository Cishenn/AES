package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.History;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryMapper {
    History getEsHistory(int esId);
}
