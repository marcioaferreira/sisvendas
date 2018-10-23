package br.com.marcioferreira.sisvendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.marcioferreira.sisvendas.domain.SubGrupoProduto;

@Repository
public interface SubGrupoProdutoRepository extends JpaRepository<SubGrupoProduto, Integer> {

}
