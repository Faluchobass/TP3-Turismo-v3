package dao;

import java.io.LineNumberInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import connection.ConnectionProvider;
import model.User;
import model.product.Itinerary;
import model.product.Offer;

public class ItineraryDAOSQLite implements ItineraryDAO {

	public List<Itinerary> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Itinerary t) {
		try {
			PreparedStatement stmt = t.getLastOffer().createInsertStatement(ConnectionProvider.getConnection());
			stmt.setInt(1, t.getUserId());
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int update(Itinerary t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Itinerary t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Offer> findAcceptedAttractions(User u) {
		List<Offer> offer = new LinkedList<Offer>();
		String sql = "SELECT atraccionId FROM Itinerario WHERE userId=? AND atraccionId NOT NULL";
		PreparedStatement stmt;
		try {
			Connection conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, u.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				offer.addAll(DAOFactory.getAttractionDAO().
						findAttractionById(rs.getInt("atraccionId")));
				//offer.add(ofertas.get(rs.getInt("atraccionId")));
			}
			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
		return offer;
	}
	public List<Offer> findAcceptedPromotions(User u) {
		List<Offer> offer = new LinkedList<Offer>();
		String sql = "SELECT promocionId FROM Itinerario WHERE userId=? AND promocionId NOT NULL;";
		PreparedStatement stmt;
		try {
			Connection conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, u.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				offer.add(DAOFactory.getPromotionDAO().
						findPromotionById(rs.getInt("promocionId")));
				//offer.add(ofertas.get(rs.getInt("promocionId")));
//				System.out.println(rs.getInt("promocionId"));
			}
			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
		return offer;
	}

	@Override
	public int agregarCompra(Integer userId, Integer offerId) {
		String sql="INSERT INTO Itinerario (userId,atraccionId)\r\n"
				+ "VALUES (?,?)";
		
		try {
			PreparedStatement stmt =ConnectionProvider.getConnection().prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setInt(2, offerId);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}


}
