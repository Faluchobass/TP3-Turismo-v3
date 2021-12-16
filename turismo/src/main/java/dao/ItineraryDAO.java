package dao;

import java.util.Hashtable;
import java.util.List;

import model.User;
import model.product.Itinerary;
import model.product.Offer;

public interface ItineraryDAO extends GenericDao<Itinerary> {

	List<Offer> findAcceptedAttractions(User user);
	public List<Offer> findAcceptedPromotions(User u);
	int agregarCompra(Integer userId, Integer offerId);

}
