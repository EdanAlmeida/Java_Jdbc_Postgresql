package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import junit.framework.TestCase;
import model.UserPosJava;

public class TesteBancoJdbc {

//	@Test
//	public void initBanco() {
//		UserPosDAO userPosDAO = new UserPosDAO();
//		UserPosJava userPosJava = new UserPosJava();
//		
//		userPosJava.setId(2L);
//		userPosJava.setNome("Padilha Didilha");
//		userPosJava.setEmail("didilha@email.com");
//		
//		userPosDAO.salvar(userPosJava);
//	}
	
//	@Test
//	public void initListar() {
//		UserPosDAO userPosDAO = new UserPosDAO();
//		try {
//			List<UserPosJava> list = userPosDAO.listar();
//			
//			for (UserPosJava userPosJava : list) {
//				System.out.println(userPosJava);
//				System.out.println("========================================");
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
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
	
}
