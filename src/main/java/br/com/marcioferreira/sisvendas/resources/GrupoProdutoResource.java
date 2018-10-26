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

import br.com.marcioferreira.sisvendas.domain.GrupoProduto;
import br.com.marcioferreira.sisvendas.dto.GrupoProdutoDTO;
import br.com.marcioferreira.sisvendas.services.GrupoProdutoServices;

@RestController
@RequestMapping(value="/gruposprodutos")
public class GrupoProdutoResource {
	
	@Autowired
	private GrupoProdutoServices service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<GrupoProduto> findById(@PathVariable Integer id) {
		GrupoProduto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<GrupoProdutoDTO>> findAll() {
		List<GrupoProduto> list = service.findAll();
		List<GrupoProdutoDTO> listDto = list.stream().map(obj -> new GrupoProdutoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody GrupoProdutoDTO objDto) {
		GrupoProduto obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody GrupoProdutoDTO objDTO, @PathVariable Integer id) {
		GrupoProduto obj = service.fromDTO(objDTO);
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
	public ResponseEntity<Page<GrupoProdutoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<GrupoProduto> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<GrupoProdutoDTO> listDto = list.map(obj -> new GrupoProdutoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
}
