package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.UserPosJava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(UserPosJava userPosJava) {
		try {
			String sql = "insert into userposjava (id, nome, email) values(?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(1, userPosJava.getId());
			insert.setString(2, userPosJava.getNome());
			insert.setString(3, userPosJava.getEmail());
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
	
	public List<UserPosJava> listar () throws Exception {
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
	
	public UserPosJava buscarId (Long id) throws Exception {
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
	
}
