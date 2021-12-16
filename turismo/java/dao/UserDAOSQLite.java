package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionProvider;
import multiverseTour.User;

public class UserDAOSQLite implements UserDAO{

	public List<User> findAll() {
		List<User> usuarios = new ArrayList<User>();
		String sql = "SELECT * FROM Users";
		
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				usuarios.add(toUser(result));
			}
			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
		return usuarios;
	}

	private User toUser(ResultSet result) {
		try {
			return new User(result.getInt("id"),result.getString("name"), result.getDouble("money"), result.getDouble("time"));
		} catch (Exception e) {
			throw new RuntimeException(e);//MissingDataException(e);
		}
	}

	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(User t) {
		Connection c=ConnectionProvider.getConnection();
		try {
			PreparedStatement stmt=c.prepareStatement("UPDATE Users \r\n"
					+ "SET money=?,time=?\r\n"
					+ "WHERE id=?");
			stmt.setDouble(1, t.getMoneyAvailability());
			stmt.setDouble(2, t.getTimeRequired());
			stmt.setInt(3, t.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateDineroDisponible() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateTiempoDisponible() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
