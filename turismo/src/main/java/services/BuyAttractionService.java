package services;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Attr;

import model.product.Attraction;
import model.User;
import dao.AttractionDAO;
import dao.UserDAO;
import dao.DAOFactory;

public class BuyAttractionService {

	AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
	UserDAO userDAO = DAOFactory.getUserDAO();

	public Map<String, String> buy(Integer userId, Integer attractionId) {
		Map<String, String> errors = new HashMap<String, String>();

		User user = userDAO.findUserById(userId);
		Attraction attraction = attractionDAO.findAttractionById(attractionId).get(0);

		if (!attraction.checkVacancy()) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.isOffertViable((int) attraction.getVisitCost(),attraction.getTimeRequired())) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.isOffertViable((int) attraction.getVisitCost(),attraction.getTimeRequired())) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			//creo que voy a manejar el itinerario por fuera del user o q se io
			//este codigo se repetira en promociones, o deberia hacer un service de offer ?
			user.acceptSuggestedOffer(attraction);
			attraction.subtractAvailability();
			

			attractionDAO.update(attraction);
			userDAO.update(user);
		}

		return errors;

	}

}
