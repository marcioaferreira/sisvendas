package br.com.marcioferreira.sisvendas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty
	@Column(length=13, unique=true)
	private String codigoBarra;
	
	@NotNull
	@NotEmpty
	@Column(length=50, unique=true)
	private String descricao;
	
	private Double precoVenda;
	private Double precoCusto;
	private Double estoque;
	private Double estoqueMinimo;
	private Double estoqueMaximo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="grupo_produto_id")
	private GrupoProduto grupo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="familia_produto_id")
	private FamiliaProduto familia;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="tamanho_produto_id")
	private TamanhoProduto tamanho;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="cor_produto_id")
	private CorProduto cor;
	
	public Produto() {
	}
	
	public Produto(Integer id, @NotNull @NotEmpty String codigoBarra, @NotNull @NotEmpty String descricao,
			Double precoVenda, Double precoCusto, Double estoque, Double estoqueMinimo, Double estoqueMaximo,
			GrupoProduto grupo, FamiliaProduto familia, TamanhoProduto tamanho, CorProduto cor) {
		super();
		this.id = id;
		this.codigoBarra = codigoBarra;
		this.descricao = descricao;
		this.precoVenda = precoVenda;
		this.precoCusto = precoCusto;
		this.estoque = estoque;
		this.estoqueMinimo = estoqueMinimo;
		this.estoqueMaximo = estoqueMaximo;
		this.grupo = grupo;
		this.familia = familia;
		this.tamanho = tamanho;
		this.cor = cor;
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

	public GrupoProduto getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoProduto grupo) {
		this.grupo = grupo;
	}

	public FamiliaProduto getFamilia() {
		return familia;
	}

	public void setFamilia(FamiliaProduto familia) {
		this.familia = familia;
	}

	public TamanhoProduto getTamanho() {
		return tamanho;
	}

	public void setTamanho(TamanhoProduto tamanho) {
		this.tamanho = tamanho;
	}

	public CorProduto getCor() {
		return cor;
	}

	public void setCor(CorProduto cor) {
		this.cor = cor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
