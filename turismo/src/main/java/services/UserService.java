package services;

import java.util.List;


import utils.Crypt;
import model.User;
import model.product.Promotion;
import dao.UserDAO;
import dao.DAOFactory;
import dao.PromotionDAO;


public class UserService {

	public List<User> list() {
		return DAOFactory.getUserDAO().findAll();
	}
	public User edit(Integer id,String name,String password,Integer admin,Double money,Double time,Boolean state) {
		UserDAO ud=DAOFactory.getUserDAO();
		User user=new User(id, name, password, admin, money, time,state);
		ud.update(user);
		return user;
	}
	public User create(String username, String password, Integer coins, Double time,Boolean state) {
		User user = new User(-1, username, Crypt.hash(password),0, coins, time,state);
		//user.setPassword(password);

		if (user.isValid()) {
			DAOFactory.getUserDAO().insert(user);
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return user;
	}
	public User findUserById(Integer id) {
		
		return DAOFactory.getUserDAO().findUserById(id);
	}
	public User edit(Integer id, String name, String password, Boolean admin, Integer money, Double time,Boolean state) {
		UserDAO ud=DAOFactory.getUserDAO();
		User user=new User(id, name, password, admin, money, time,state);
		ud.update(user);
		return user;
		
	}
	public User changeState(Integer id, Boolean state) {
		UserDAO ud=DAOFactory.getUserDAO();
		User user=ud.findUserById(id);
		user.setState(state);
		ud.update(user);
		return user;
		
	}
	

}
