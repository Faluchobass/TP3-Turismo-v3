package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import connection.ConnectionProvider;
import model.product.AbsolutPromotion;
import model.product.Attraction;
import model.product.AxBPromotion;
import model.product.PercentagePromotion;
import model.product.Promotion;

public class PromotionDAOSQLite implements PromotionDAO {

	AttractionDAO adao = DAOFactory.getAttractionDAO();

	private ArrayList<Attraction> getPromotionAttractionsById(int promoId) {

		String consulta = "SELECT id_atraccion FROM promocion_atraccion "
				+ "	JOIN atracciones ON promocion_atraccion.id_atraccion=atracciones.id AND promocion_atraccion.atraccion_free=0"
				+ "	WHERE promocion_atraccion.id_promocion=?";
		PreparedStatement stmt;
		ArrayList<Attraction> attractions = new ArrayList<Attraction>();
		try {
			Connection conn = connection.ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(consulta);
			stmt.setInt(1, promoId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				attractions.addAll(this.adao.findAttractionById(rs.getInt("id_atraccion")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attractions;
	}

	private AbsolutPromotion createAbsolutPromotion(ResultSet rs) {
		AbsolutPromotion a = null;

		try {
			ArrayList<Attraction> promoAttractions = getPromotionAttractionsById(rs.getInt("id"));
			a = new AbsolutPromotion(rs.getInt("id"), rs.getString("nombre"), promoAttractions,
					rs.getDouble("descuento"), rs.getString("descripcion"), rs.getString("imgSrc"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;

	}

	private ArrayList<AbsolutPromotion> findAbsolutPromotions() {

		String consulta = "SELECT * \r\n" + "FROM promocion \r\n" + "WHERE promocion.tipo_descu='Absoluto'\r\n"
				+ "ORDER BY descuento DESC;";
		Statement stmt;
		ArrayList<AbsolutPromotion> absolutPromotions = new ArrayList<AbsolutPromotion>();
		try {
			Connection conn = connection.ConnectionProvider.getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				AbsolutPromotion a = createAbsolutPromotion(rs);
				absolutPromotions.add(a);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return absolutPromotions;
	}

	private ArrayList<Attraction> findBonusAttractions(int promoId) {

		String consulta = "SELECT promocion_atraccion.id_atraccion\r\n" + "	FROM promocion_atraccion\r\n"
				+ "	JOIN atracciones ON atracciones.id=promocion_atraccion.id_atraccion AND promocion_atraccion.atraccion_free=1\r\n"
				+ "	WHERE promocion_atraccion.id_promocion=?;";
		PreparedStatement stmt;
		ArrayList<Attraction> attractions = new ArrayList<Attraction>();
		try {
			Connection conn = connection.ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(consulta);
			stmt.setInt(1, promoId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				attractions.addAll(this.adao.findAttractionById(rs.getInt("id_atraccion")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attractions;

	}

	private AxBPromotion createAxBPromotion(ResultSet rs) {
		AxBPromotion axbPromotion = null;

		try {
			ArrayList<Attraction> promoAttractions = getPromotionAttractionsById(rs.getInt("id"));
			ArrayList<Attraction> bonusAttractions = findBonusAttractions(rs.getInt("id"));
			axbPromotion = new AxBPromotion(rs.getInt("id"), rs.getString("nombre"), promoAttractions, bonusAttractions,
					rs.getString("descripcion"), rs.getString("imgSrc"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return axbPromotion;
	}

	private ArrayList<AxBPromotion> findAxBPromotions() {

		String consulta = "SELECT *\r\n" + "FROM promocion\r\n" + "WHERE promocion.tipo_descu='AxB';;";
		Statement stmt;
		ArrayList<AxBPromotion> axbpromotions = new ArrayList<AxBPromotion>();
		try {
			Connection conn = connection.ConnectionProvider.getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				AxBPromotion a = createAxBPromotion(rs);
				axbpromotions.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return axbpromotions;
	}

	private PercentagePromotion createPercentagePromotion(ResultSet rs) {
		PercentagePromotion percentagePromotion = null;
		try {
			ArrayList<Attraction> promoAttractions = getPromotionAttractionsById(rs.getInt("id"));
			percentagePromotion = new PercentagePromotion(rs.getInt("id"), rs.getString("nombre"), promoAttractions,
					rs.getDouble("descuento"), rs.getString("descripcion"), rs.getString("imgSrc"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return percentagePromotion;
	}

	private ArrayList<PercentagePromotion> findPercentagePromotions() {

		String consulta = "SELECT *\r\n" + "FROM promocion\r\n" + "WHERE promocion.tipo_descu='Porcentual';";
		Statement stmt;
		ArrayList<PercentagePromotion> percentagePromotions = new ArrayList<PercentagePromotion>();
		try {
			Connection conn = connection.ConnectionProvider.getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				ArrayList<Attraction> promoAttractions = getPromotionAttractionsById(rs.getInt("id"));
				PercentagePromotion percentagePromotion = createPercentagePromotion(rs);
				percentagePromotions.add(percentagePromotion);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return percentagePromotions;
	}

	public ArrayList<Promotion> findAll() {
		ArrayList<Promotion> rtn = new ArrayList<Promotion>();
		rtn.addAll(this.findAbsolutPromotions());
		rtn.addAll(this.findAxBPromotions());
		rtn.addAll(this.findPercentagePromotions());
		return rtn;
	}

	public int countAll() {
		return 0;
	}

	public int insert(Promotion t) {

		String promocionSql = "INSERT INTO promocion (nombre, descuento, tipo_descu, descripcion,imgSrc)"
				+ " VALUES (?, ?, ?, ?,?)";

		int rows = 0;
		PreparedStatement statement;
		try {
			Connection conn = ConnectionProvider.getConnection();
			statement = conn.prepareStatement(promocionSql);
			int i = 1;
			statement.setString(i++, t.getName());
			statement.setDouble(i++, t.getDescuento());
			statement.setString(i++, t.getTipoDescu());
			statement.setString(i++, t.getDescripcion());
			statement.setString(i++, t.getImgSrc());
			rows = statement.executeUpdate();
			insertarAtraccionesDePromocion(t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;

	}

	private void insertarAtraccionesDePromocion(Promotion t) {
		String lasAtraccionesSql = "INSERT INTO promocion_atraccion" + " (id_promocion,id_atraccion,atraccion_free)"
				+ " VALUES (?, ?, ?)";
		PreparedStatement statement;
		for (Attraction a : t.getArrayAttractions()) {

			try {
				Connection conn = ConnectionProvider.getConnection();
				statement = conn.prepareStatement(lasAtraccionesSql);
				int i = 1;
				statement.setInt(i++, t.getId());
				statement.setInt(i++, a.getId());
				statement.setInt(i++, 0);
				statement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (t instanceof AxBPromotion) {
			AxBPromotion axb = (AxBPromotion) t;
			for (Attraction atra : axb.getExtra()) {
				try {
					Connection conn = ConnectionProvider.getConnection();
					statement = conn.prepareStatement(lasAtraccionesSql);
					int i = 1;
					statement.setInt(i++, t.getId());
					statement.setInt(i++, atra.getId());
					statement.setInt(i++, 1);
					statement.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public int updatePromotionQuota(Promotion t) {
		LinkedList<PreparedStatement> stmtList = t.createUpdateQuotasStatement();
		for (PreparedStatement stmt : stmtList) {
			try {
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int update(Promotion t) {
		return 0;
	}

	public int delete(Promotion t) {
		return 0;
	}

	public Promotion createPromotion(ResultSet rs) {
		Promotion promotion = null;
		try {
			System.out.println("tipo_descu " + rs.getString("tipo_descu"));
			if (rs.getString("tipo_descu").equals("AxB")) {
				System.out.println("Entro AxB");
				promotion = this.createAxBPromotion(rs);
			} else if (rs.getString("tipo_descu").equals("Porcentual")) {
				System.out.println("Entro Porcentual");
				promotion = this.createPercentagePromotion(rs);
			} else if (rs.getString("tipo_descu").equals("Absoluto")) {
				System.out.println("Entro Absoluto");
				promotion = this.createAbsolutPromotion(rs);
			} else {
				System.out.println("Ninguna xD");
			}
			System.out.println(promotion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return promotion;
	}

	@Override
	public Promotion findPromotionById(Integer promotionId) {
		// Encontrar la promocion de id promotionId
		// Construir la promocion con las respectivas atracciones
		// Devolver la promocion
		Promotion promotion = null;
		String sql = "SELECT * FROM promocion WHERE id=?;";

		try {
			Connection conn = connection.ConnectionProvider.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, promotionId);
			ResultSet rs = stmt.executeQuery();
			promotion = this.createPromotion(rs);
			if (promotion == null)
				System.out.println("promotion salio null lol");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return promotion;
	}

}
