package br.com.marcioferreira.sisvendas.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.marcioferreira.sisvendas.domain.EmbalagemProduto;
import br.com.marcioferreira.sisvendas.dto.EmbalagemProdutoDTO;
import br.com.marcioferreira.sisvendas.services.EmbalagemProdutoServices;

@RestController
@RequestMapping(value="/embalagensprodutos")
public class EmbalagemProdutoResource {
	
	@Autowired
	private EmbalagemProdutoServices service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<EmbalagemProduto> findById(@PathVariable Integer id) {
		EmbalagemProduto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EmbalagemProduto>> findAll() {
		List<EmbalagemProduto> list = service.findAll();
		//List<EmbalagemProdutoDTO> listDto = list.stream().map(obj -> new EmbalagemProdutoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EmbalagemProdutoDTO objDto) {
		EmbalagemProduto obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody EmbalagemProdutoDTO objDTO, @PathVariable Integer id) {
		EmbalagemProduto obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/pagegruposprodutos", method=RequestMethod.GET)
	public ResponseEntity<Page<EmbalagemProdutoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<EmbalagemProduto> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<EmbalagemProdutoDTO> listDto = list.map(obj -> new EmbalagemProdutoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
}
