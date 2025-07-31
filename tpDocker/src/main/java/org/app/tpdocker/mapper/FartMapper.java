package org.app.tpdocker.mapper;

import org.app.tpdocker.model.entity.Fart;
import org.app.tpdocker.model.dto.FartDTO;

public class FartMapper {

    public static FartDTO entitytoDTO(Fart fart) {
        if (fart == null) return null;
        return new FartDTO(
                fart.getId(),
                fart.getSmell(),
                fart.getDissipation(),
                fart.getSoundVolume(),
                fart.getOrigin()
        );
    }

    public static Fart DTOtoEntity(FartDTO dto) {
        if (dto == null) return null;
        Fart fart = new Fart();
        fart.setId(dto.id());
        fart.setSmell(dto.smell());
        fart.setDissipation(dto.dissipation());
        fart.setSoundVolume(dto.soundVolume());
        fart.setOrigin(dto.origin());
        return fart;
    }
}
