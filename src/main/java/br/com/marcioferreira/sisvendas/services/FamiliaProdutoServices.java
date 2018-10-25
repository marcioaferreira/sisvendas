package br.com.marcioferreira.sisvendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.marcioferreira.sisvendas.domain.FamiliaProduto;
import br.com.marcioferreira.sisvendas.repositories.FamiliaProdutoRepository;
import br.com.marcioferreira.sisvendas.services.exceptions.DataIntegrityException;
import br.com.marcioferreira.sisvendas.services.exceptions.ObjectNotFoundException;

@Service
public class FamiliaProdutoServices {
	
	@Autowired
	private FamiliaProdutoRepository repo;
	
	public FamiliaProduto findById(Integer id) {
		Optional<FamiliaProduto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + FamiliaProduto.class.getName()));
	}
	
	public FamiliaProduto insert(FamiliaProduto obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public FamiliaProduto update(FamiliaProduto obj) {
		findById(obj.getId());
		return repo.save(obj);
	}
	
	public List<FamiliaProduto> findAll() {
		return repo.findAll();
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Família que possui produtos");
		}
	}

	public Page<FamiliaProduto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
}
