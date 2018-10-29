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

import br.com.marcioferreira.sisvendas.domain.CorProduto;
import br.com.marcioferreira.sisvendas.dto.CorProdutoDTO;
import br.com.marcioferreira.sisvendas.services.CorProdutoServices;

@RestController
@RequestMapping(value="/coresprodutos")
public class CorProdutoResource {
	
	@Autowired
	private CorProdutoServices service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<CorProduto> findById(@PathVariable Integer id) {
		CorProduto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CorProdutoDTO>> findAll() {
		List<CorProduto> list = service.findAll();
		List<CorProdutoDTO> listDto = list.stream().map(obj -> new CorProdutoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CorProdutoDTO objDto) {
		CorProduto obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody CorProdutoDTO objDTO, @PathVariable Integer id) {
		CorProduto obj = service.fromDTO(objDTO);
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
	public ResponseEntity<Page<CorProdutoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<CorProduto> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CorProdutoDTO> listDto = list.map(obj -> new CorProdutoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
}
