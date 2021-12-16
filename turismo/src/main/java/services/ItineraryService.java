package services;

import java.util.List;

import dao.DAOFactory;
import model.User;
import model.product.Itinerary;
import model.product.Offer;

public class ItineraryService {
	public List<Offer> listAttractions(User user) {
		
		return DAOFactory.getItineraryDAO().findAcceptedAttractions(user);
	}

	public List<Offer> listPromotions(User user) {
		
		return DAOFactory.getItineraryDAO().findAcceptedPromotions(user);
	}
}
