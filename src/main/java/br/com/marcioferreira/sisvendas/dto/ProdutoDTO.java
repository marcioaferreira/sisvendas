package br.com.marcioferreira.sisvendas.dto;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import br.com.marcioferreira.sisvendas.domain.Produto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty()
	@Length(min=13, max=13)
	private String codigoBarra;
	
	@NotEmpty()
	@Length(min=5, max=50)
	private String descricao;
	
	@PositiveOrZero()
	@Digits (integer = 6, fraction = 2)
	private Double precoVenda;
	
	@PositiveOrZero()
	@Digits (integer = 6, fraction = 2)
	private Double precoCusto;
	
	@PositiveOrZero()
	@Digits (integer = 6, fraction = 2)
	private Double estoque;
	
	@PositiveOrZero()
	@Digits (integer = 6, fraction = 2)
	private Double estoqueMinimo;
	
	@PositiveOrZero()
	@Digits (integer = 6, fraction = 2)
	private Double estoqueMaximo;
	
	private Integer grupo;
	private Integer familia;
	private Integer tamanho;
	private Integer cor;
	
	public ProdutoDTO() {
	}

	public ProdutoDTO(Produto obj) {
		super();
		this.id = obj.getId();
		this.codigoBarra = obj.getCodigoBarra();
		this.descricao = obj.getDescricao();
		this.precoVenda = obj.getPrecoVenda();
		this.precoCusto = obj.getPrecoCusto();
		this.estoque = obj.getEstoque();
		this.estoqueMinimo = obj.getEstoqueMinimo();
		this.estoqueMaximo = obj.getEstoqueMinimo();
		this.grupo = obj.getGrupo().getId();
		this.familia = obj.getFamilia().getId();
		this.tamanho = obj.getTamanho().getId();
		this.cor = obj.getCor().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(Double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public Double getEstoque() {
		return estoque;
	}

	public void setEstoque(Double estoque) {
		this.estoque = estoque;
	}

	public Double getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(Double estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public Double getEstoqueMaximo() {
		return estoqueMaximo;
	}

	public void setEstoqueMaximo(Double estoqueMaximo) {
		this.estoqueMaximo = estoqueMaximo;
	}

	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	public Integer getFamilia() {
		return familia;
	}

	public void setFamilia(Integer familia) {
		this.familia = familia;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public Integer getCor() {
		return cor;
	}

	public void setCor(Integer cor) {
		this.cor = cor;
	}
	
}
