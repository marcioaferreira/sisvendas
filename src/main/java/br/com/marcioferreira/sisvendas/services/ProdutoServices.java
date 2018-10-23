package br.com.marcioferreira.sisvendas.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcioferreira.sisvendas.domain.Produto;
import br.com.marcioferreira.sisvendas.repositories.ProdutoRepository;

@Service
public class ProdutoServices {
	
	@Autowired
	private ProdutoRepository repo;
	
	public List<Produto> findAll() {
		List<Produto> obj = repo.findAll();
		return obj;
	}

}
