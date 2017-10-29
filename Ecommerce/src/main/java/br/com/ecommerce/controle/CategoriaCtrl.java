package br.com.ecommerce.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.ecommerce.entidade.Categoria;
import br.com.ecommerce.persistencia.CategoriaDAO;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class CategoriaCtrl implements Serializable {

	private Categoria categoria;
	private List<Categoria> categorias = new ArrayList<Categoria>();

	private CategoriaDAO categoriaDAO = new CategoriaDAO();

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Categoria> getListagem() {
		try {
			return categoriaDAO.selectAll("nome");
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar exibir os dados");
			return null;
		}

	}

	public void novaCategoria() {
		categoria = new Categoria();
	}

	public void actionSalvar() {
		try {
			categoriaDAO.merge(categoria);
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar os dados");
		}
	}

	public void actionEditar(ActionEvent event) {
		categoria = (Categoria) event.getComponent().getAttributes().get("categoriaSelecionada");
		System.out.println("Debuging");
	}

	public void actionExcluir(ActionEvent event) {
		categoria = (Categoria) event.getComponent().getAttributes().get("categoriaSelecionada");	
		
		try {
			categoriaDAO.delete(categoria);
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir os dados");
		}
	}

}
