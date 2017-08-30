package br.com.ecommerce.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.ecommerce.entidade.Fabricante;
import br.com.ecommerce.entidade.Produto;
import br.com.ecommerce.persistencia.FabricanteDAO;
import br.com.ecommerce.persistencia.ProdutoDAO;

@SessionScoped
@ManagedBean(name = "produtoCtrl")
public class ProdutoCtrl {

	private Produto produto;

	private List<Fabricante> fabricantes = new FabricanteDAO().selectAll("nome");

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public List<Produto> getListagem() {
		return new ProdutoDAO().selectAll("nome");
	}

	public void actionNovo() {
		try {
			produto = new Produto();

			fabricantes = new FabricanteDAO().selectAll("nome");
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

}
