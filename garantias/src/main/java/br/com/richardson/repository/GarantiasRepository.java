package br.com.richardson.repository;

import br.com.richardson.model.entity.Garantia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface GarantiasRepository extends JpaRepository<Garantia, UUID> {
    @SuppressWarnings("unchecked")
    Garantia save(Garantia garantia);

    Optional<Garantia> findById(UUID id);
}
