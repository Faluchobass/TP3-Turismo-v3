package dao;

import java.util.Hashtable;
import product.Attraction;

public class DAOFactory {
	
	public static UserDAO getUserDAO() {
		return new UserDAOSQLite();
	}
	
	public static AttractionDAO getAtraccionDAO() {
		return new AttractionDAOSQLite();
	}
	
	public static PromotionDAO getPromotionDAO(Hashtable<Integer,Attraction> htAttracciones) {
		return new PromotionDAOSQLite(htAttracciones); 
	}
	public static ItineraryDAO getItineraryDAO() {
		return new ItineraryDAOSQLite();
	}
	public static OfferDAO getOfferDAO() {
		return new OfferDAOSQLite();
	}
}
