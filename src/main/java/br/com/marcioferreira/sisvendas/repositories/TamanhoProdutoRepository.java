package br.com.marcioferreira.sisvendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.marcioferreira.sisvendas.domain.TamanhoProduto;

@Repository
public interface TamanhoProdutoRepository extends JpaRepository<TamanhoProduto, Integer> {

}
