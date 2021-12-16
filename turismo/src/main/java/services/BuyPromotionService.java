package services;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import dao.AttractionDAO;
import dao.DAOFactory;
import dao.PromotionDAO;
import dao.UserDAO;
import model.User;
import model.product.Attraction;
import model.product.Promotion;

public class BuyPromotionService {
	
	PromotionDAO promotionDAO;
	UserDAO userDAO ;
	public BuyPromotionService() {
		promotionDAO = DAOFactory.getPromotionDAO();
		userDAO = DAOFactory.getUserDAO();
	}
	
	
	public Map<String, String> buy(Integer userId, Integer promotionId) {
		
		Map<String, String> errors = new HashMap<String, String>();
		User user = userDAO.findUserById(userId);
		Promotion promotion = promotionDAO.findPromotionById(promotionId);

		if (!promotion.checkVacancy()) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.isOffertViable((int) promotion.getVisitCost(),promotion.getTimeRequired())) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.isOffertViable((int) promotion.getVisitCost(),promotion.getTimeRequired())) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			//creo que voy a manejar el itinerario por fuera del user o q se io
			//este codigo se repetira en promociones, o deberia hacer un service de offer ?
			user.acceptSuggestedOffer(promotion);
			promotion.subtractAvailability();
			
			
			promotionDAO.updatePromotionQuota(promotion);
			userDAO.update(user);
		}

		return errors;

	}
}
