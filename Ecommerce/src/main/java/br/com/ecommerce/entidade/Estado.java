package br.com.ecommerce.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table
public class Estado extends EntidadeGenerica {
	
	@Column (nullable = false, unique = true, length = 30)
	private String nome;
	
	@Column (nullable = false, unique = true, length = 2)
	private String uf;
	
	@OneToMany (mappedBy = "estado", fetch = FetchType.LAZY)
	private List<Cidade> cidades = new ArrayList<Cidade>();
	
	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
}
