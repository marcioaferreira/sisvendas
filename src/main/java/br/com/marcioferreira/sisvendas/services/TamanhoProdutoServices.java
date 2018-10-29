package br.com.marcioferreira.sisvendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.marcioferreira.sisvendas.domain.TamanhoProduto;
import br.com.marcioferreira.sisvendas.dto.TamanhoProdutoDTO;
import br.com.marcioferreira.sisvendas.repositories.TamanhoProdutoRepository;
import br.com.marcioferreira.sisvendas.services.exceptions.DataIntegrityException;
import br.com.marcioferreira.sisvendas.services.exceptions.ObjectNotFoundException;

@Service
public class TamanhoProdutoServices {
	
	@Autowired
	private TamanhoProdutoRepository repo;
	
	public TamanhoProduto findById(Integer id) {
		Optional<TamanhoProduto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + TamanhoProduto.class.getName()));
	}
	
	public TamanhoProduto insert(TamanhoProduto obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public TamanhoProduto update(TamanhoProduto obj) {
		findById(obj.getId());
		return repo.save(obj);
	}
	
	public List<TamanhoProduto> findAll() {
		return repo.findAll();
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Tamanho que possui produtos");
		}
	}

	public Page<TamanhoProduto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public TamanhoProduto fromDTO(TamanhoProdutoDTO objDto) {
		return new TamanhoProduto(objDto.getId(), objDto.getDescricao());
	}
	
}
