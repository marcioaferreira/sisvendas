package br.com.marcioferreira.sisvendas.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.marcioferreira.sisvendas.domain.FieldConfigure;
import br.com.marcioferreira.sisvendas.domain.CorProduto;

public class CorProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty()
	@Length(min=4, max=FieldConfigure.TAMANHO_DESCRICAO)
	private String descricao;
	
	public CorProdutoDTO() {
	}

	public CorProdutoDTO(CorProduto obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
