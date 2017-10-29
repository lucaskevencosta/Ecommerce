package br.com.ecommerce.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.ecommerce.entidade.Fornecedor;
import br.com.ecommerce.entidade.Produto;
import br.com.ecommerce.persistencia.FornecedorDAO;
import br.com.ecommerce.persistencia.ProdutoDAO;
import br.com.ecommerce.util.SessionUtil;

@SessionScoped
@ManagedBean(name = "produtoCtrl")
public class ProdutoCtrl {

	private Produto produto;

	private List<Fornecedor> fornecedores = new FornecedorDAO().selectAll("nome");

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<Produto> getListagem() {
		try {
			return new ProdutoDAO().selectAll("nome");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao carregar os dados");
			return null;
		}
	}

	public void actionNovo() {
		try {
			produto = new Produto();

			fornecedores = new FornecedorDAO().selectAll("nome");
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao carregar alguns dados");
		}

	}

	public void actionSalvar() {
		try {
			new ProdutoDAO().merge(produto);
			Messages.addGlobalInfo("Produto salvo com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar salvar os dados");
		}
	}

	public void actionPreparaEdição(ActionEvent event) {
		produto = (Produto) event.getComponent().getAttributes().get("produtoSelecionado");
	}

	public void actionExcluir(ActionEvent event) {
		try {
			produto = (Produto) event.getComponent().getAttributes().get("produtoSelecionado");

			new ProdutoDAO().delete(produto);

			Messages.addGlobalInfo("Registro excluído com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Falha ao tentar excluir o registro");
		}
	}

	public String actionImagens() {
		SessionUtil.setParam("produto", produto);
		return "galeria_produto";
	}
	
}
