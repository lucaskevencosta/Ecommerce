package br.com.ecommerce.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
		return new FabricanteDAO().selectAll("nome");
	}
}
