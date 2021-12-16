package dao;

import model.User;

public interface UserDAO extends GenericDao<User> {
	
	public int updateDineroDisponible();
	
	public int updateTiempoDisponible();

	public User findUserById(Integer userId);

	public User findByUsername(String username);	
}
