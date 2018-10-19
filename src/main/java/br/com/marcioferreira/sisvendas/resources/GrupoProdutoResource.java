package br.com.marcioferreira.sisvendas.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/gruposprodutos")
public class GrupoProdutoResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar() {
		
		return "REST está funcionando";
	}

}
