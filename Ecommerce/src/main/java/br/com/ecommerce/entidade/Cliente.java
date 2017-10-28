package br.com.ecommerce.entidade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table
public class Cliente extends EntidadeGenerica {
	
	@OneToOne
	@JoinColumn (nullable = false)
	private Pessoa pessoa;
	
	@OneToOne
	private ListaDeDesejos listaDeDesejos;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public ListaDeDesejos getListaDeDesejos() {
		return listaDeDesejos;
	}

	public void setListaDeDesejos(ListaDeDesejos listaDeDesejos) {
		this.listaDeDesejos = listaDeDesejos;
	}
	
}
