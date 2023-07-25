TesteBancoJdbc.txt
package pos_java_jdbc.pos_java_jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import junit.framework.TestCase;
import model.BeanUserFone;
import model.Telefone;
import model.UserPosJava;

public class TesteBancoJdbc {

	@Test
	public void initBanco() {
		UserPosDAO userPosDAO = new UserPosDAO();
		UserPosJava userPosJava = new UserPosJava();

		userPosJava.setNome("Lucas Souza");
		userPosJava.setEmail("lucas@email.com");

		userPosDAO.salvar(userPosJava);
	}

	@Test
	public void initListar() {
		UserPosDAO userPosDAO = new UserPosDAO();
		try {
			List<UserPosJava> list = userPosDAO.listar();

			for (UserPosJava userPosJava : list) {
				System.out.println(userPosJava);
				System.out.println("========================================");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initBuscarId() {
		UserPosDAO userDAO = new UserPosDAO();
		try {
			UserPosJava user = userDAO.buscarId(1L);

			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initAtualizar() {
		try {
			UserPosDAO userDAO = new UserPosDAO();
			UserPosJava user = userDAO.buscarId(2L);

			user.setNome("Padilha Didilha");

			userDAO.atualizar(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initDeletar() {
		try {
			UserPosDAO userDAO = new UserPosDAO();
			userDAO.deletar(4L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initInsertTelefone() {
		UserPosDAO userDAO = new UserPosDAO();
		Telefone telefone = new Telefone();

		telefone.setNumero("42984327444");
		telefone.setTipo("celular");
		telefone.setUsuario(1L);

		userDAO.salvarTelefone(telefone);
	}

	@Test
	public void initCarregaFoneUsers() {

		UserPosDAO userDAO = new UserPosDAO();
		List<BeanUserFone> listaFoneUsers = userDAO.listaUserFone(1L);

		for (BeanUserFone beanUserFone : listaFoneUsers) {
			System.out.println(beanUserFone);
			System.out.println("=================================================");
		}

	}
	
	@Test
	public void initDeleteUserFone () {
		UserPosDAO userDAO =  new UserPosDAO();
		
		userDAO.deleteFonesUser(1L);
	}

}