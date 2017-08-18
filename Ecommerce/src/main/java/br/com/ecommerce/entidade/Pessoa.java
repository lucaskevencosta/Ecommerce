package br.com.ecommerce.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@SuppressWarnings("serial")
@Table
@Entity
public class Pessoa extends EntidadeGenerica {
	
	@Column (nullable = false, length = 40)
	private String nome;
	
	@Column (nullable = false, length = 15, unique = true)
	private String cpf;
	
	@Column (nullable = false, length = 25)
	private String rg;
	
	@Column (nullable = false)
	private Date dataNasc;
	
	@Column (nullable = false, length = 40)
	private String rua;
	
	@Column (nullable = false, length = 40)
	private String bairro;
	
	@Column (nullable = false)
	private Integer numero;
	
	@Column (nullable = false, length = 60)
	private String complemento;
	
	@Column (nullable = false, length = 10)
	private String cep;
	
	@ManyToOne
	@JoinColumn (nullable = false)
	private Cidade cidade;
	
	@Column (nullable = false, length = 50)
	private String email;
	
	@Column (nullable = false, length = 30)
	private String senha;
	
	@Column (nullable = false, length = 15)
	private String tipo;
	
	@Column (nullable = false)
	@Type (type = "org.hibernate.type.NumericBooleanType")
	private boolean ativo; 

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
