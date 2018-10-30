package br.com.marcioferreira.sisvendas.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.marcioferreira.sisvendas.domain.EmbalagemProduto;

public class EmbalagemProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty()
	@Length(min=5, max=30)
	private String descricao;
	
	@NotEmpty()
	@Length(min=2, max=4)
	private String sigla;
	
	public EmbalagemProdutoDTO() {
	}
	
	public EmbalagemProdutoDTO(EmbalagemProduto obj) {
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
