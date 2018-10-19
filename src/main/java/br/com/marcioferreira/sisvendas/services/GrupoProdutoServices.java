package br.com.marcioferreira.sisvendas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcioferreira.sisvendas.domain.GrupoProduto;
import br.com.marcioferreira.sisvendas.repositories.GrupoProdutoRepository;

@Service
public class GrupoProdutoServices {
	
	@Autowired
	private GrupoProdutoRepository repo;
	
	public GrupoProduto findById(Integer id) {
		Optional<GrupoProduto> obj = repo.findById(id);
		return obj.orElse(null);
	}

}
