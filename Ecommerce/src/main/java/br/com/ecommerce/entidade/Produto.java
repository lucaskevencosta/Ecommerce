package br.com.ecommerce.entidade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Table
@Entity
public class Produto extends EntidadeGenerica {
	
	@Column (nullable = false, length = 30)
	private String nome;
	
	@Column (nullable = false, length = 120)
	private String descricao;
	
	@Column (nullable = false, precision = 7, scale = 2)
	private BigDecimal precoDeCompra;
	
	@Column (nullable = false, precision = 7, scale = 2)
	private BigDecimal precoDeVenda;
	
	@ManyToOne
	@JoinColumn (nullable = false)
	private Fornecedor fornecedor;
	
	@OneToMany (mappedBy = "produto", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<ImagemProduto> imagensProduto = new ArrayList<ImagemProduto>();
	
	@ManyToMany(mappedBy="produtos", cascade = CascadeType.ALL)
	private List<Categoria> categorias = new ArrayList<Categoria>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public BigDecimal getPrecoDeCompra() {
		return precoDeCompra;
	}
	
	public void setPrecoDeCompra(BigDecimal precoDeCompra) {
		this.precoDeCompra = precoDeCompra;
	}
	
	public BigDecimal getPrecoDeVenda() {
		return precoDeVenda;
	}
	
	public void setPrecoDeVenda(BigDecimal precoDeVenda) {
		this.precoDeVenda = precoDeVenda;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public List<ImagemProduto> getImagensProduto() {
		return imagensProduto;
	}
	
	public void setImagensProduto(List<ImagemProduto> imagensProduto) {
		this.imagensProduto = imagensProduto;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
}
