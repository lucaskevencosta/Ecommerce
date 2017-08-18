package br.com.ecommerce.entidade;

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
@Entity
@Table
public class Cidade extends EntidadeGenerica {
	
	@ManyToOne
	@JoinColumn (name = "estado")
	private Estado estado;
	
	@Column (length = 2)
	private String uf;
	
	@Column (nullable = false)
	private String nome;
	
	@OneToMany (mappedBy = "cidade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
