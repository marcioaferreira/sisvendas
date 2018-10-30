package br.com.marcioferreira.sisvendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.marcioferreira.sisvendas.domain.EmbalagemProduto;

@Repository
public interface EmbalagemProdutoRepository extends JpaRepository<EmbalagemProduto, Integer> {

}
