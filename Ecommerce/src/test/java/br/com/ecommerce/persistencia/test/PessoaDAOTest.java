package br.com.ecommerce.persistencia.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.ecommerce.entidade.Cidade;
import br.com.ecommerce.entidade.Pessoa;
import br.com.ecommerce.persistencia.CidadeDAO;
import br.com.ecommerce.persistencia.PessoaDAO;

public class PessoaDAOTest {

	Pessoa p = new Pessoa();
	PessoaDAO pDao = new PessoaDAO();
	
	@Test
	public void salvar() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = (Date)df.parse("11/07/1995");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Cidade cidade = new CidadeDAO().selectToId(2120L);
		
		p.setNome("Lucas Keven");
		p.setBairro("Jardim Guanabara");
		p.setCep("74.675-200");
		p.setComplemento("Qd. 69 Lt. 03");
		p.setCpf("701.229.091-94");
		p.setEmail("lucas@lucas");
		p.setNumero(0);
		p.setRg("6017266");
		p.setRua("Cariri");
		p.setSenha("123");
		p.setTipo("ROLE_ADMIN");
		p.setDataNasc(date);
		p.setAtivo(true);
		p.setCidade(cidade);

		pDao.insert(p);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	@Ignore
	public void listar() {
		List<Pessoa> pessoas = new ArrayList();
		pessoas = pDao.selectAll();

		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long id = 3L;

		p = pDao.selectToId(id);

		System.out.println("Nome: " + p.getNome() + " Tipo: " + p.getTipo());
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long id = 2L;
		p = pDao.selectToId(id);
		pDao.delete(p);
	}
	
	@Test
	@Ignore
	public void alterar() {
		Long id = 3L;
		p = pDao.selectToId(id);
		p.setNome("Xanaína da Silva");
		pDao.update(p);
	}
}
