package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.History;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HistoryMapper {
    List<History> getEsHistory(int esId);
}
