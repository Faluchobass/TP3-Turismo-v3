package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionProvider;
import model.User;

public class UserDAOSQLite implements UserDAO {

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
			return new User(result.getInt("id"), 
					result.getString("name"),
					result.getString("password"),
					result.getInt("admin"), 
					result.getDouble("money"),
					result.getDouble("time"),
					result.getBoolean("alta"));
		} catch (Exception e) {
			throw new RuntimeException(e);// MissingDataException(e);
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
		
		try {
			Connection c = ConnectionProvider.getConnection();
			PreparedStatement stmt = c.prepareStatement("UPDATE Users \r\n" + "SET name=?, password=?, admin=?, money=?,time=?, alta=?\r\n" + "WHERE id=?");
			
			stmt.setString(1, t.getName());
			stmt.setInt(2, t.getPassword().hashCode());
			stmt.setBoolean(3, t.isAdmin());
			stmt.setDouble(4, t.getMoney());
			stmt.setDouble(5, t.getTime());
			stmt.setBoolean(6, t.getState());
			stmt.setInt(7, t.getId());
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

	@Override
	public User findUserById(Integer id) {
		List<User> usuarios = null;
		String sql = "SELECT * FROM Users WHERE id=?";

		try {
			usuarios = new ArrayList<User>();
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			usuarios.add(toUser(result));
			return usuarios.get(0);
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		User usuarios = null;
		String sql = "SELECT * FROM Users WHERE name=?";

		try {
//			usuarios = new User();
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			usuarios=toUser(result);
			return usuarios;
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
		return null;
	}

}
