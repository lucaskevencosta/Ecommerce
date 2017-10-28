package br.com.ecommerce.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table
public class ListaDeDesejos extends EntidadeGenerica {

	@ManyToMany
	@JoinTable(name = "produto_lista_de_desejos_rel", joinColumns = {
			@JoinColumn(name = "listaDeDesejos_id") }, inverseJoinColumns = { @JoinColumn(name = "produto_id") })
	private List<Produto> produtos = new ArrayList<Produto>();

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
