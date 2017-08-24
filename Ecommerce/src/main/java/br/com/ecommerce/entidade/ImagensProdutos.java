package br.com.ecommerce.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@SuppressWarnings("serial")
@Table
@Entity
public class ImagensProdutos extends EntidadeGenerica {
	
	@Column (nullable = false)
	private String caminho;
	
	@Column (nullable = false)
	@Type (type = "org.hibernate.type.NumericBooleanType")
	private Boolean principal;
	
	@ManyToOne
	@JoinColumn (nullable = false)
	private Produto produto;

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
