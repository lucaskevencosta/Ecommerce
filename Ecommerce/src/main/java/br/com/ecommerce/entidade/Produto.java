package br.com.ecommerce.entidade;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
}
