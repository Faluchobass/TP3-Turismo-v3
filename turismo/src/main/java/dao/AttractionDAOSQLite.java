package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import connection.ConnectionProvider;
import model.product.Attraction;

public class AttractionDAOSQLite implements AttractionDAO {

	public List<Attraction> findAll() {
		List<Attraction> attractions = new ArrayList<Attraction>();
		String sql = "SELECT * FROM atracciones";

		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				attractions.add(toAttraction(result));
			}

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
		return attractions;
	}

	public int countAll() {
		return 0;
	}

	public int insert(Attraction t) {

		String sql = "INSERT INTO atracciones (name, costo, cupo, tiempo,cupoDisponible) VALUES (?, ?, ?, ?,?)";
		
		int rows = 0;
		PreparedStatement statement;
		try {
			Connection conn = ConnectionProvider.getConnection();
			statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, t.getName());
			statement.setDouble(i++, t.getVisitCost());
			statement.setInt(i++, t.getQuota());
			statement.setDouble(i++, t.getDuration());
			statement.setInt(i++, t.getQuota());
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

	public int update(Attraction t) {
		PreparedStatement stmt = t.createUpdateQuotasStatement().getFirst();
		try {
			stmt = connection.ConnectionProvider.getConnection().
					prepareStatement("UPDATE atracciones\r\n"
					+ "SET nombre=?,costo=?,tiempo=?," 
					+ "cupoDisponible=? " + "WHERE atracciones.id=?");
			stmt.setString(1, t.getName());//nombre
			stmt.setDouble(2, t.getVisitCost());//costo
			stmt.setDouble(3, t.getTimeRequired());//tiempo
			stmt.setInt(4, t.getQuota());//cupoDisponible
			stmt.setInt(5, t.getId());//id
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(Attraction t) {

		String sql = "DELETE FROM atracciones WHERE id = ?";
		
		int rows = 0;
		PreparedStatement statement;
		try {
			Connection conn = ConnectionProvider.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, t.getId());
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

	public int setAttractionQuotas(Attraction t) {
		PreparedStatement stmt = t.createUpdateQuotasStatement().getFirst();
		try {
			stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	private Attraction toAttraction(ResultSet result) {
		try {
			return new Attraction(result.getInt("id"), result.getString("nombre"), result.getDouble("costo"),
					result.getDouble("tiempo"), result.getInt("cupoDisponible"),
					result.getString("descripcion"),result.getString("imgSrc"));
		} catch (Exception e) {
			throw new RuntimeException(e);// MissingDataException(e);
		}
	}

	@Override
	public List<Attraction> findAttractionById(Integer id) {
		List<Attraction> attractions = null;
		String sql = "SELECT * FROM atracciones WHERE id=?";

		try {
			attractions = new ArrayList<Attraction>();
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				attractions.add(toAttraction(result));
			}
			return attractions;
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
		return attractions;
	}
}
