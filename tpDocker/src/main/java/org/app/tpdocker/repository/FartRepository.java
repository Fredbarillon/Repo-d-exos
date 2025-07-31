package org.app.tpdocker.repository;

import org.app.tpdocker.model.entity.Fart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FartRepository extends JpaRepository<Fart, Long> {
}
