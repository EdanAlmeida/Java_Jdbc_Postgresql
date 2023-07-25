UserPosDAO.txt
package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.UserPosJava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(UserPosJava userPosJava) {
		try {
			String sql = "insert into userposjava (nome, email) values(?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userPosJava.getNome());
			insert.setString(2, userPosJava.getEmail());
			insert.execute();

			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public List<UserPosJava> listar() throws Exception {
		List<UserPosJava> list = new ArrayList<UserPosJava>();
		String sql = "select * from userposjava";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			UserPosJava userPosJava = new UserPosJava();
			userPosJava.setId(resultSet.getLong("id"));
			userPosJava.setNome(resultSet.getString("nome"));
			userPosJava.setEmail(resultSet.getString("email"));

			list.add(userPosJava);
		}

		return list;
	}

	public UserPosJava buscarId(Long id) throws Exception {
		UserPosJava user = new UserPosJava();
		String sql = "select * from userposjava where id = " + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			UserPosJava userPosJava = new UserPosJava();
			user.setId(resultSet.getLong("id"));
			user.setNome(resultSet.getString("nome"));
			user.setEmail(resultSet.getString("email"));
		}

		return user;
	}

	public void atualizar(UserPosJava userPosJava) {

		try {
			String sql = "update userposjava set nome = ? where id = " + userPosJava.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userPosJava.getNome());

			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void deletar(Long id) {
		try {
			String sql = "delete from userposjava where id = " + id;

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();

			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void salvarTelefone(Telefone telefone) {

		try {
			String sql = "insert into telefoneuser (numero, tipo, usuariopessoa) values(?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());

			insert.execute();

			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public List<BeanUserFone> listaUserFone(Long idUser) {

		List<BeanUserFone> beanUsersFones = new ArrayList<BeanUserFone>();

		String sql = "select nome, numero, email from telefoneuser as fone ";
		sql += " inner join userposjava as userp ";
		sql += " on fone.usuariopessoa = userp.id ";
		sql += " where userp.id = " + idUser;

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				BeanUserFone userFone = new BeanUserFone();
				userFone.setEmail(resultSet.getString("email"));
				userFone.setNome(resultSet.getString("nome"));
				userFone.setNumero(resultSet.getString("numero"));

				beanUsersFones.add(userFone);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanUsersFones;
	}

	public void deleteFonesUser(Long idUser) {

		try {

			String sqlFone = "delete from telefoneuser where usuariopessoa = " + idUser;
			String sqlUser = "delete from userposjava where id = " + idUser;

			PreparedStatement statement = connection.prepareStatement(sqlFone);
			statement.executeUpdate();
			connection.commit();
			
			statement = connection.prepareStatement(sqlUser);
			statement.executeUpdate();
			connection.commit();
			
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}