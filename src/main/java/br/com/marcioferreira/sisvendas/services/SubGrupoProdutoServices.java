package br.com.marcioferreira.sisvendas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcioferreira.sisvendas.domain.SubGrupoProduto;
import br.com.marcioferreira.sisvendas.repositories.SubGrupoProdutoRepository;

@Service
public class SubGrupoProdutoServices {
	
	@Autowired
	private SubGrupoProdutoRepository repo;
	
	public SubGrupoProduto findById(Integer id) {
		Optional<SubGrupoProduto> obj = repo.findById(id);
		return obj.orElse(null);
	}

}
