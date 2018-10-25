package br.com.marcioferreira.sisvendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.marcioferreira.sisvendas.domain.GrupoProduto;
import br.com.marcioferreira.sisvendas.dto.GrupoProdutoDTO;
import br.com.marcioferreira.sisvendas.repositories.GrupoProdutoRepository;
import br.com.marcioferreira.sisvendas.services.exceptions.DataIntegrityException;
import br.com.marcioferreira.sisvendas.services.exceptions.ObjectNotFoundException;

@Service
public class GrupoProdutoServices {
	
	@Autowired
	private GrupoProdutoRepository repo;
	
	public GrupoProduto findById(Integer id) {
		Optional<GrupoProduto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + GrupoProduto.class.getName()));
	}
	
	public GrupoProduto insert(GrupoProduto obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public GrupoProduto update(GrupoProduto obj) {
		findById(obj.getId());
		return repo.save(obj);
	}
	
	public List<GrupoProduto> findAll() {
		return repo.findAll();
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Grupo que possui produtos");
		}
	}

	public Page<GrupoProduto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public GrupoProduto fromDTO(GrupoProdutoDTO objDto) {
		return new GrupoProduto(objDto.getId(), objDto.getDescricao());
	}
	
}
