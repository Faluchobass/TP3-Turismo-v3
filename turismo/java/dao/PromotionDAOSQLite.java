package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;


import product.AbsolutPromotion;
import product.Attraction;
import product.AxBPromotion;
import product.PercentagePromotion;
import product.Promotion;

public class PromotionDAOSQLite implements PromotionDAO {

	
	private Hashtable<Integer,Attraction> attractionList;

	public PromotionDAOSQLite(Hashtable<Integer,Attraction> attractionList) {
		this.attractionList=attractionList;
	}
	private ArrayList<Attraction> getPromotionAttractionsById(int promoId) {
		Connection conn = connection.ConnectionProvider.getConnection();
		String consulta = "SELECT id_atraccion FROM promocion_atraccion "
				+ "	JOIN atracciones ON promocion_atraccion.id_atraccion=atracciones.id AND promocion_atraccion.atraccion_free=0"
				+ "	WHERE promocion_atraccion.id_promocion=?";
		PreparedStatement stmt;
		ArrayList<Attraction> attractions = new ArrayList<Attraction>();
		try {
			stmt = conn.prepareStatement(consulta);
			stmt.setInt(1, promoId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
//				System.out.println(rs.getInt("id_atraccion"));
				attractions.add(this.attractionList.get(rs.getInt("id_atraccion")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attractions;
	}
	
	private ArrayList<AbsolutPromotion> findAbsolutPromotions() {
		Connection conn = connection.ConnectionProvider.getConnection();
		String consulta = "SELECT * \r\n"
				+ "FROM promocion \r\n"
				+ "WHERE promocion.tipo_descu='Absoluto'\r\n"
				+ "ORDER BY descuento DESC;";
		Statement stmt;
		ArrayList<AbsolutPromotion> absolutPromotions = new ArrayList<AbsolutPromotion>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				ArrayList<Attraction> promoAttractions = getPromotionAttractionsById(rs.getInt("id"));
				AbsolutPromotion a = new AbsolutPromotion(rs.getInt("id"),rs.getString("nombre"), promoAttractions,
						rs.getDouble("descuento"));
				absolutPromotions.add(a);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return absolutPromotions;
	}
	

	private ArrayList<Attraction> getBonusAttractions(int promoId) {
		Connection conn = connection.ConnectionProvider.getConnection();
		String consulta = "SELECT promocion_atraccion.id_atraccion\r\n"
				+ "	FROM promocion_atraccion\r\n"
				+ "	JOIN atracciones ON atracciones.id=promocion_atraccion.id_atraccion AND promocion_atraccion.atraccion_free=1\r\n"
				+ "	WHERE promocion_atraccion.id_promocion=?;";
		PreparedStatement stmt;
		ArrayList<Attraction> attractions = new ArrayList<Attraction>();
		try {
			stmt = conn.prepareStatement(consulta);
			stmt.setInt(1, promoId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				attractions.add(this.attractionList.get(rs.getInt("id_atraccion")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attractions;

	}

	private ArrayList<AxBPromotion> findAxBPromotions() {
		Connection conn = connection.ConnectionProvider.getConnection();
		String consulta = "SELECT *\r\n"
				+ "FROM promocion\r\n"
				+ "WHERE promocion.tipo_descu='AxB';;";
		Statement stmt;
		ArrayList<AxBPromotion> axbpromotions = new ArrayList<AxBPromotion>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				ArrayList<Attraction> promoAttractions = getPromotionAttractionsById(rs.getInt("id"));
				ArrayList<Attraction> bonusAttractions = getBonusAttractions(rs.getInt("id"));
				AxBPromotion a = new AxBPromotion(rs.getInt("id"),rs.getString("nombre"), promoAttractions, bonusAttractions);
				axbpromotions.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return axbpromotions;
	}
	
	private ArrayList<PercentagePromotion> findPercentagePromotions() {
		Connection conn = connection.ConnectionProvider.getConnection();
		String consulta = "SELECT *\r\n"
				+ "FROM promocion\r\n"
				+ "WHERE promocion.tipo_descu='Porcentual';";
		Statement stmt;
		ArrayList<PercentagePromotion> percentagePromotions = new ArrayList<PercentagePromotion>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				ArrayList<Attraction> promoAttractions = getPromotionAttractionsById(rs.getInt("id"));
				PercentagePromotion percentagePromotion = new PercentagePromotion(rs.getInt("id"),rs.getString("nombre"),
						promoAttractions, rs.getDouble("descuento"));
				percentagePromotions.add(percentagePromotion);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return percentagePromotions;
	}
	
	public ArrayList<Promotion> findAll() {
		ArrayList<Promotion> rtn=new ArrayList<Promotion>();
		rtn.addAll(this.findAbsolutPromotions());
		rtn.addAll(this.findAxBPromotions());
		rtn.addAll(this.findPercentagePromotions());
		return rtn;
	}

	public int countAll() {
		return 0;
	}

	public int insert(Promotion t) {
		return 0;
	}

	public int update(Promotion t) {
		LinkedList<PreparedStatement> stmtList=t.createUpdateStatement();
		for(PreparedStatement stmt:stmtList) {
			try {
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int delete(Promotion t) {
		return 0;
	}

	public int setPromotionQuotas() {
		return 0;
	}

}
