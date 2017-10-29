package br.com.ecommerce.controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.ecommerce.entidade.Fornecedor;
import br.com.ecommerce.persistencia.FornecedorDAO;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class FornecedorCtrl implements Serializable {

	private FornecedorDAO fornecedorDAO = new FornecedorDAO();

	private Fornecedor fornecedor;
	private List<Fornecedor> fornecedores = fornecedorDAO.selectAll("nome");

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public void actionNovo() {
		fornecedor = new Fornecedor();
	}

	public void actionSalvar() {
		try {
			fornecedorDAO.merge(fornecedor);
			fornecedores = fornecedorDAO.selectAll("nome");
			Messages.addGlobalInfo("Fornecedor salvo com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar os dados");
		}
	}

	public void actionEditar(ActionEvent event) {
		fornecedor = (Fornecedor) event.getComponent().getAttributes().get("fornecedor");
	}
	
	public void actionExcluir(ActionEvent event) {
		try {
			fornecedor = (Fornecedor) event.getComponent().getAttributes().get("fornecedor");
			fornecedorDAO.delete(fornecedor);
			fornecedores = fornecedorDAO.selectAll("nome");
			
			Messages.addGlobalInfo("Os dados foram excluídos com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir os dados");
		}
	}

}
