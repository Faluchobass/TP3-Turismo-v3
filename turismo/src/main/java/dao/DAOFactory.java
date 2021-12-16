package dao;

import java.util.Hashtable;

import model.product.Attraction;

public class DAOFactory {
	
	public static UserDAO getUserDAO() {
		return new UserDAOSQLite();
	}
	
	public static AttractionDAO getAttractionDAO() {
		return new AttractionDAOSQLite();
	}
	
	public static PromotionDAO getPromotionDAO() {
		return new PromotionDAOSQLite(); 
	}
	public static ItineraryDAO getItineraryDAO() {
		return new ItineraryDAOSQLite();
	}
	public static OfferDAO getOfferDAO() {
		return new OfferDAOSQLite();
	}
}
