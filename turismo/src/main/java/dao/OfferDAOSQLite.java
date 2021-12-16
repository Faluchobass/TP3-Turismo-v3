package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.product.Offer;


public class OfferDAOSQLite implements OfferDAO{

	public List<Offer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Offer t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(Offer t) {
		LinkedList<PreparedStatement> stmtList=t.createUpdateQuotasStatement();
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

	public int delete(Offer t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
