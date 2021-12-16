package services;

import model.User;
//import model.nullobjects.NullUser;
import dao.UserDAO;
import dao.DAOFactory;
import dao.ItineraryDAO;

public class LoginService {

	public User login(String username, String password) {
		UserDAO userDao = DAOFactory.getUserDAO();
    	User user = userDao.findByUsername(username);
    	
    	user.receiveItinerary(DAOFactory.getItineraryDAO().findAcceptedPromotions(user));
    	user.receiveItinerary(DAOFactory.getItineraryDAO().findAcceptedAttractions(user));

    	if ( !user.checkPassword(password) ) {
    		user=null; 
    	}
    	return user;
	}
	
}
