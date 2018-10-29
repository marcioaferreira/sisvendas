package br.com.marcioferreira.sisvendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.marcioferreira.sisvendas.domain.CorProduto;
import br.com.marcioferreira.sisvendas.dto.CorProdutoDTO;
import br.com.marcioferreira.sisvendas.repositories.CorProdutoRepository;
import br.com.marcioferreira.sisvendas.services.exceptions.DataIntegrityException;
import br.com.marcioferreira.sisvendas.services.exceptions.ObjectNotFoundException;

@Service
public class CorProdutoServices {
	
	@Autowired
	private CorProdutoRepository repo;
	
	public CorProduto findById(Integer id) {
		Optional<CorProduto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + CorProduto.class.getName()));
	}
	
	public CorProduto insert(CorProduto obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public CorProduto update(CorProduto obj) {
		findById(obj.getId());
		return repo.save(obj);
	}
	
	public List<CorProduto> findAll() {
		return repo.findAll();
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Cor que possui produtos");
		}
	}

	public Page<CorProduto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public CorProduto fromDTO(CorProdutoDTO objDto) {
		return new CorProduto(objDto.getId(), objDto.getDescricao());
	}
	
}
