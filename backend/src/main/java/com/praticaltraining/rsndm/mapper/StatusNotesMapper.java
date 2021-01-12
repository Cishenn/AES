package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.StatusNotes;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface StatusNotesMapper {
    List<StatusNotes> getAllByEsId(int esId);
}
