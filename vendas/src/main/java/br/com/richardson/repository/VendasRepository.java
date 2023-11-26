package br.com.richardson.repository;

import br.com.richardson.model.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VendasRepository extends JpaRepository<Venda, UUID> {
    Optional<Venda> findById(UUID id);
}
