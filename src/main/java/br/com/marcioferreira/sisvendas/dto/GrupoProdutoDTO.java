package br.com.marcioferreira.sisvendas.dto;

import java.io.Serializable;

import br.com.marcioferreira.sisvendas.domain.GrupoProduto;

public class GrupoProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	
	public GrupoProdutoDTO() {
	}

	public GrupoProdutoDTO(GrupoProduto obj) {
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
