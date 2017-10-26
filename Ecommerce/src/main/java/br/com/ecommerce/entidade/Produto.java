package br.com.ecommerce.entidade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Table
@Entity
public class Produto extends EntidadeGenerica {
	
	@Column (nullable = false, length = 30)
	private String nome;
	
	@Column (nullable = false, length = 50)
	private String descricao;
	
	@Column (nullable = false, precision = 7, scale = 2)
	private BigDecimal preco;
	
	@ManyToOne
	@JoinColumn (nullable = false)
	private Fabricante fabricante;
	
	@OneToMany (mappedBy = "produto", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<ImagemProduto> imagensProduto = new ArrayList<ImagemProduto>();

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
	
	public BigDecimal getPreco() {
		return preco;
	}
	
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	public List<ImagemProduto> getImagensProduto() {
		return imagensProduto;
	}
	
	public void setImagensProduto(List<ImagemProduto> imagensProduto) {
		this.imagensProduto = imagensProduto;
	}
	
}
