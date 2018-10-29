package br.com.marcioferreira.sisvendas.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import br.com.marcioferreira.sisvendas.domain.TamanhoProduto;
import br.com.marcioferreira.sisvendas.dto.TamanhoProdutoDTO;
import br.com.marcioferreira.sisvendas.services.TamanhoProdutoServices;

@RestController
@RequestMapping(value="/tamanhosprodutos")
public class TamanhoProdutoResource {
	
	@Autowired
	private TamanhoProdutoServices service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<TamanhoProduto> findById(@PathVariable Integer id) {
		TamanhoProduto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<TamanhoProdutoDTO>> findAll() {
		List<TamanhoProduto> list = service.findAll();
		List<TamanhoProdutoDTO> listDto = list.stream().map(obj -> new TamanhoProdutoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody TamanhoProdutoDTO objDto) {
		TamanhoProduto obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody TamanhoProdutoDTO objDTO, @PathVariable Integer id) {
		TamanhoProduto obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/pagetamanhosprodutos", method=RequestMethod.GET)
	public ResponseEntity<Page<TamanhoProdutoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<TamanhoProduto> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<TamanhoProdutoDTO> listDto = list.map(obj -> new TamanhoProdutoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
}
