package br.com.ecommerce.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.ecommerce.entidade.Fabricante;
import br.com.ecommerce.persistencia.FabricanteDAO;

@SessionScoped
@ManagedBean (name = "fabricanteCtrl")
public class FabricanteCtrl {
	
	private Fabricante fabricante;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	public List<Fabricante> getListagem() {
		try {
			return new FabricanteDAO().selectAll("nome");	
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar os dados");
			return null;
		}
	}
	
	public void actionNovo() {
		fabricante = new Fabricante();
	}
	
	public void actionSalvar() {
		try {
			new FabricanteDAO().merge(fabricante);	
			
			Messages.addGlobalInfo("Dados salvos com sucesso");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar os dados.");
		}
	}
	
	public void actionExcluir(ActionEvent event) {
		try {
			fabricante = (Fabricante) event.getComponent().getAttributes().get("fabricante");
			new FabricanteDAO().delete(fabricante);
			
			Messages.addGlobalInfo("Registro excluído com sucesso");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar excluir o registro");
		}
	}
	
	public void actionPrepararEdicao(ActionEvent event) {
		fabricante = (Fabricante) event.getComponent().getAttributes().get("fabricante");
	}
}
