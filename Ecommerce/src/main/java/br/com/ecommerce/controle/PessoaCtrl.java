package br.com.ecommerce.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.ecommerce.entidade.Cidade;
import br.com.ecommerce.entidade.Estado;
import br.com.ecommerce.entidade.Pessoa;
import br.com.ecommerce.persistencia.CidadeDAO;
import br.com.ecommerce.persistencia.EstadoDAO;
import br.com.ecommerce.persistencia.PessoaDAO;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "pessoaCtrl")
public class PessoaCtrl implements Serializable {
		
	private Pessoa pessoa;
	
	private List<Cidade> cidades;
	
	private Estado estado;
	private List<Estado> estados;

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public List<Pessoa> getListagem() {
		return new PessoaDAO().selectAll();
	}
	
	public void actionNovo() {
		pessoa = new Pessoa();
		estado = new Estado();
		cidades = new ArrayList<Cidade>();
		
		try {
			estados = new EstadoDAO().selectAll();
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao carregar dados.");
		}
	}
	
	public void actionSalvar() {
		try {
			new PessoaDAO().merge(pessoa);
			actionNovo();
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar salvar os dados");
		}
	}
	
	public void actionExcluir(ActionEvent event) {
		try {
			pessoa = (Pessoa) event.getComponent().getAttributes().get("usuarioTabela");
			new PessoaDAO().delete(pessoa);
			
			Messages.addGlobalInfo("Usuário excluído com sucesso.");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao realizar a operação.");
		}
	}
	
	public void actionPrepararEdicao(ActionEvent event) {		
		try {
			pessoa = (Pessoa) event.getComponent().getAttributes().get("usuarioTabela");
			
			estado = pessoa.getCidade().getEstado();
			estados = new EstadoDAO().selectAll();
			
			System.out.println(estado.getNome());
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao carregar dados.");
		}
	}
	
	public void actionPopularCidades() {
		if(estado != null) {
			try {
				cidades = new CidadeDAO().selectInnerJoinEstado(estado.getId());
			} catch (RuntimeException e) {
				e.printStackTrace();
				Messages.addGlobalError("Erro ao carregar dados.");
			}
		}
	}
	
}
