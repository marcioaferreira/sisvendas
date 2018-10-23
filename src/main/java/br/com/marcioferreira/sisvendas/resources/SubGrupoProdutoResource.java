package br.com.marcioferreira.sisvendas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcioferreira.sisvendas.domain.SubGrupoProduto;
import br.com.marcioferreira.sisvendas.services.SubGrupoProdutoServices;

@RestController
@RequestMapping(value="/subgruposprodutos")
public class SubGrupoProdutoResource {
	
	@Autowired
	private SubGrupoProdutoServices service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		SubGrupoProduto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
