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

import br.com.marcioferreira.sisvendas.domain.CategoriaProduto;
import br.com.marcioferreira.sisvendas.dto.CategoriaProdutoDTO;
import br.com.marcioferreira.sisvendas.services.CategoriaProdutoServices;

@RestController
@RequestMapping(value="/categoriasprodutos")
public class CategoriaProdutoResource {
	
	@Autowired
	private CategoriaProdutoServices service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<CategoriaProduto> findById(@PathVariable Integer id) {
		CategoriaProduto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaProduto>> findAll() {
		List<CategoriaProduto> list = service.findAll();
		//List<CategoriaProdutoDTO> listDto = list.stream().map(obj -> new CategoriaProdutoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaProdutoDTO objDto) {
		CategoriaProduto obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody CategoriaProdutoDTO objDTO, @PathVariable Integer id) {
		CategoriaProduto obj = service.fromDTO(objDTO);
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
	public ResponseEntity<Page<CategoriaProdutoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<CategoriaProduto> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaProdutoDTO> listDto = list.map(obj -> new CategoriaProdutoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
}
