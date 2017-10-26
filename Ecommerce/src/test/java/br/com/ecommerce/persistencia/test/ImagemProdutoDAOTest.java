package br.com.ecommerce.persistencia.test;

import java.util.List;

import org.junit.Test;

import br.com.ecommerce.entidade.ImagemProduto;
import br.com.ecommerce.persistencia.ImagemProdutoDAO;

public class ImagemProdutoDAOTest {
	
	@Test
	public void listagemImagens() {
		List<ImagemProduto> imagens = new ImagemProdutoDAO().selectWhileProduto(1L);
		
		for (ImagemProduto imagemProduto : imagens) {
			System.out.println(imagemProduto.getCaminho());
		}
	}
	
}
