package dao;

import java.sql.SQLException;
import java.util.List;

import product.Attraction;

public class App {

	public static void main(String[] args) throws SQLException {
		AttractionDAO dao = DAOFactory.getAtraccionDAO();
		List<Attraction> attractions = dao.findAll();

	}

}
