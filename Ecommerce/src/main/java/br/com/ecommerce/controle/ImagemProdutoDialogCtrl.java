package br.com.ecommerce.controle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.ecommerce.entidade.ImagemProduto;
import br.com.ecommerce.entidade.Produto;
import br.com.ecommerce.persistencia.ImagemProdutoDAO;
import br.com.ecommerce.util.SessionUtil;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ImagemProdutoDialogCtrl implements Serializable {
	
private UploadedFile upFile;
	
	private ImagemProdutoDAO imagemDao = new ImagemProdutoDAO();
	
	private Produto produto;
	
	private ImagemProduto imagem;
	
	private InputStream stream;
	
	private StreamedContent foto;
	
	public StreamedContent getFoto() {
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

	/*
	 * Este método faz o upload do arquivo e salva numa pasta temporária do SO
	 */
	public void actionUploadImagem(FileUploadEvent event) {
		
		imagem = new ImagemProduto();
		
		produto = (Produto) SessionUtil.getParam("produto");
		upFile = event.getFile();

		if (imagemDao.selectWhileProduto(produto.getId()).size() < 10) {
			try {

				Path path = Files.createTempFile(null, null);
				Files.copy(upFile.getInputstream(), path, StandardCopyOption.REPLACE_EXISTING);

				imagem.setTemp(path.toString());
			} catch (IOException e) {
				Messages.addGlobalError("Falha ao carregar a imagem.");
				e.printStackTrace();
			}
		} else
			Messages.addGlobalError("O número máximo de imagens para este produto já foi atingido");
		
		SessionUtil.setParam("imagem", imagem);

	}
	
	public void actionAmpliarImagem(ActionEvent event) {
		try {
			imagem = (ImagemProduto) event.getComponent().getAttributes().get("imagem");

			Path path = Paths.get(imagem.getCaminho());
			stream = Files.newInputStream(path);
			foto = new DefaultStreamedContent(stream);
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a imagem");
		}
	}
	
	public void actionExcluirImagem() {
		try {
			// comando necessário para fechar o input stream aberto no método
			// actionAmpliarImagem
			stream.close();
			
			File f = new File(imagem.getCaminho());
			f.delete();

			imagemDao.delete(imagem);
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir a imagem");
		}
	}
}
