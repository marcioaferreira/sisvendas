package br.com.marcioferreira.sisvendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.marcioferreira.sisvendas.domain.CategoriaProduto;
import br.com.marcioferreira.sisvendas.dto.CategoriaProdutoDTO;
import br.com.marcioferreira.sisvendas.repositories.CategoriaProdutoRepository;
import br.com.marcioferreira.sisvendas.services.exceptions.DataIntegrityException;
import br.com.marcioferreira.sisvendas.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaProdutoServices {
	
	@Autowired
	private CategoriaProdutoRepository repo;
	
	public CategoriaProduto findById(Integer id) {
		Optional<CategoriaProduto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + CategoriaProduto.class.getName()));
	}
	
	public CategoriaProduto insert(CategoriaProduto obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public CategoriaProduto update(CategoriaProduto obj) {
		findById(obj.getId());
		return repo.save(obj);
	}
	
	public List<CategoriaProduto> findAll() {
		return repo.findAll();
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Categoria que possui produtos");
		}
	}

	public Page<CategoriaProduto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public CategoriaProduto fromDTO(CategoriaProdutoDTO objDto) {
		return new CategoriaProduto(objDto.getId(), objDto.getDescricao());
	}
	
}
