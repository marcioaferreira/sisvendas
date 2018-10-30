package br.com.marcioferreira.sisvendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.marcioferreira.sisvendas.domain.EmbalagemProduto;
import br.com.marcioferreira.sisvendas.dto.EmbalagemProdutoDTO;
import br.com.marcioferreira.sisvendas.repositories.EmbalagemProdutoRepository;
import br.com.marcioferreira.sisvendas.services.exceptions.DataIntegrityException;
import br.com.marcioferreira.sisvendas.services.exceptions.ObjectNotFoundException;

@Service
public class EmbalagemProdutoServices {
	
	@Autowired
	private EmbalagemProdutoRepository repo;
	
	public EmbalagemProduto findById(Integer id) {
		Optional<EmbalagemProduto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + EmbalagemProduto.class.getName()));
	}
	
	public EmbalagemProduto insert(EmbalagemProduto obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public EmbalagemProduto update(EmbalagemProduto obj) {
		findById(obj.getId());
		return repo.save(obj);
	}
	
	public List<EmbalagemProduto> findAll() {
		return repo.findAll();
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Embalagem que possui produtos");
		}
	}

	public Page<EmbalagemProduto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public EmbalagemProduto fromDTO(EmbalagemProdutoDTO objDto) {
		return new EmbalagemProduto(objDto.getId(), objDto.getDescricao(), objDto.getSigla());
	}
	
}
