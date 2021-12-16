package dao;

import java.util.Hashtable;
import java.util.List;

import multiverseTour.User;
import product.Itinerary;
import product.Offer;

public interface ItineraryDAO extends GenericDao<Itinerary> {

	List<Offer> findAcceptedAttractions(User user,Hashtable<Integer,Offer> ofertas);
	public List<Offer> findAcceptedPromotions(User u, Hashtable<Integer, Offer> ofertas);
}
