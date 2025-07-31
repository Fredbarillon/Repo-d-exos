package org.app.tpdocker.interfaces;

import org.app.tpdocker.model.dto.FartDTO;

import java.util.List;

public interface FartInterface {
    List<FartDTO> getAllFarts();
    FartDTO getFartByID(Long id);
    FartDTO saveFart(FartDTO fartDTO);
    FartDTO updateFart(Long id, FartDTO fartDTO);
    void deleteFart(Long id);


}
