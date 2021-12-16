package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionProvider;
import product.Attraction;

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
		return 0;
	}

	public int update(Attraction t) {
		PreparedStatement stmt=t.createUpdateStatement().getFirst();
		try {
			stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(Attraction t) {
		return 0;
	}

	public int setAttractionQuotas() {
		return 0;
	}

	private Attraction toAttraction(ResultSet result) {
		try {
			return new Attraction(result.getInt("id"),result.getString("nombre"), 
					result.getDouble("costo"), result.getDouble("tiempo"),
					result.getInt("cupoDisponible"));
		} catch (Exception e) {
			throw new RuntimeException(e);//MissingDataException(e);
		}
	}
}
