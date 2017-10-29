package br.com.ecommerce.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table
public class Fornecedor extends EntidadeGenerica {
	
	@Column (nullable = false, length = 30)
	private String nome;
	
	@Column (length = 120)
	private String observacoes;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
}
