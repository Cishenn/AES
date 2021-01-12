package com.praticaltraining.rsndm.biz;


import com.praticaltraining.rsndm.entity.StatusNotes;

import java.util.List;

public interface StatusNotesBiz {
    List<StatusNotes> getAllByEsId(int esId);
}
