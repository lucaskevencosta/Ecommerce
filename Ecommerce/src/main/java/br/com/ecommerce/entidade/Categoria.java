package br.com.ecommerce.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table
public class Categoria extends EntidadeGenerica {

	@Column(length = 30, nullable = false)
	private String nome;

	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(name = "produto_categoria_rel", joinColumns = { @JoinColumn(name = "categoria_id") }, inverseJoinColumns = {
			@JoinColumn(name = "produto_id") })
	private List<Produto> produtos = new ArrayList<Produto>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
