package dao;

import multiverseTour.User;

public interface UserDAO extends GenericDao<User> {
	
	public int updateDineroDisponible();
	
	public int updateTiempoDisponible();
		
}
