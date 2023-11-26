package br.com.richardson.repository;

import br.com.richardson.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto, UUID> {
    @SuppressWarnings("unchecked")
    @Transactional
    Produto save(Produto produto);

    Optional<Produto> findById(UUID id);
}
