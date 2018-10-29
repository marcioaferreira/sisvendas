package br.com.marcioferreira.sisvendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.marcioferreira.sisvendas.domain.CorProduto;

@Repository
public interface CorProdutoRepository extends JpaRepository<CorProduto, Integer> {

}
