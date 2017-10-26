package br.com.ecommerce.controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.omnifaces.util.Messages;
import org.primefaces.model.StreamedContent;

import br.com.ecommerce.entidade.ImagemProduto;
import br.com.ecommerce.entidade.Produto;
import br.com.ecommerce.persistencia.ImagemProdutoDAO;
import br.com.ecommerce.util.SessionUtil;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
public class ImagemProdutoCtrl implements Serializable {

	private Produto produto;

	private ImagemProduto imagem = new ImagemProduto();
	private List<ImagemProduto> imagens;

	private ImagemProdutoDAO imagemDao = new ImagemProdutoDAO();

	private StreamedContent foto;
	
	private String tituloImagem;

	public ImagemProdutoCtrl() {
		produto = (Produto) SessionUtil.getParam("produto");
		listarFotosProduto();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ImagemProduto getImagem() {
		return imagem;
	}

	public void setImagem(ImagemProduto imagem) {
		this.imagem = imagem;
	}

	public List<ImagemProduto> getImagens() {
		return imagens;
	}

	public void setImagens(List<ImagemProduto> imagens) {
		this.imagens = imagens;
	}

	public StreamedContent getFoto() {
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
	
	public String getTituloImagem() {
		return tituloImagem;
	}
	
	public void setTituloImagem(String tituloImagem) {
		this.tituloImagem = tituloImagem;
	}

	private void listarImagens() {
		imagens = imagemDao.selectWhileProduto(produto.getId());
	}

	public void actionNovaImagem() {
		imagem = new ImagemProduto();
	}

	public String actionVoltar() {
		return "produtos";
	}

	/*
	 * Este método salva o objeto imagem no banco
	 */
	public void actionSalvarImagem() {
		try {
			imagem = (ImagemProduto) SessionUtil.getParam("imagem");
			
			String caminhoTemporario = imagem.getTemp();
			
			String caminho = "E:\\Ecommerce-uploads\\produtos\\" + produto.getId();
			
			imagem.setTitulo(tituloImagem);
			imagem.setProduto(produto);
			imagem = imagemDao.mergeImagem(imagem);

			String arquivo = caminho + "\\" + "img" + imagem.getId() + ".jpg";

			criarArquivo(caminho, arquivo, caminhoTemporario);
			 
			imagem = imagemDao.mergeImagem(imagem);

			Messages.addGlobalInfo("Imagem salva com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Não foi possível salvar a imagem");
		} finally {
			listarFotosProduto();
		}
	}

	/*
	 * Este método cria os arquivos e pastas necessários para armazenar as
	 * imagens
	 */
	private void criarArquivo(String pasta, String arquivo, String enderecoTemp) throws IOException {
		Path pastaPath = Paths.get(pasta);
		Path arqPath = Paths.get(arquivo);

		if (Files.notExists(pastaPath, LinkOption.NOFOLLOW_LINKS))
			Files.createDirectories(pastaPath);

		Path tempPath = Paths.get(enderecoTemp);

		Files.copy(tempPath, arqPath, StandardCopyOption.REPLACE_EXISTING);
		

		imagem.setCaminho(arquivo);
	}

	private void criarArquivo(byte[] bytes, String arquivo) throws Exception {
		FileOutputStream fos;

		fos = new FileOutputStream(arquivo);
		fos.write(bytes);

		fos.flush();
		fos.close();
	}

	public void listarFotosProduto() {
		try {
			// Cria um objeto que acessa o contexto da aplicação
			ServletContext sContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();

			listarImagens();

			// Cria a pasta /temp no contexto
			File folder = new File(sContext.getRealPath("/temp"));
			if (folder.exists()) 
				removerArquivos(folder);

			// Monta as imagens na pasta /temp, onde serão buscadas para
			// exibição
			for (ImagemProduto imagem : imagens) {
				String nomeArquivo = imagem.getId() + ".jpg";
				String arquivo = sContext.getRealPath("/temp") + File.separator + nomeArquivo;

				File file = new File(imagem.getCaminho());

				// Cria o arquivo (imagem) dentro da pasta /temp
				criarArquivo(fileToByte(file), arquivo);
				
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao carregar as imagens para exibição");
		}
	}

	/*
	 * Este método converte o objeto file recebido, retornando um array de byte
	 */
	@SuppressWarnings("resource")
	private byte[] fileToByte(File file) throws Exception {
		int len = (int) file.length();
		byte[] sendBuf = new byte[len];
		FileInputStream inFile = null;

		inFile = new FileInputStream(file);
		inFile.read(sendBuf, 0, len);
		
		file.delete();

		return sendBuf;
	}

	public void removerArquivos(File f) {
		// Se o arquivo passado for um diretório
		if (f.isDirectory()) {
			/*
			 * Lista todos os arquivos do diretório em um array de objetos File
			 */
			File[] files = f.listFiles();
			// Identa a lista (foreach) e deleta um por um
			for (File file : files) {
				file.delete();
			}
		}
	}

}
