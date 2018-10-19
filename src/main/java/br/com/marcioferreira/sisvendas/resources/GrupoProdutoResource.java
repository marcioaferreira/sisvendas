package br.com.marcioferreira.sisvendas.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcioferreira.sisvendas.domain.GrupoProduto;

@RestController
@RequestMapping(value="/gruposprodutos")
public class GrupoProdutoResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<GrupoProduto> listar() {
		
		GrupoProduto p1 = new GrupoProduto(1,"SSD");
		GrupoProduto p2 = new GrupoProduto(2,"Still");
		GrupoProduto p3 = new GrupoProduto(3,"Cerveja");
		
		List<GrupoProduto> lista = new ArrayList<>();
		lista.add(p1);
		lista.add(p2);
		lista.add(p3);
		
		return lista;
	}

}
