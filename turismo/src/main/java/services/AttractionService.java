package services;

import java.util.List;

import dao.AttractionDAO;
import dao.DAOFactory;
import model.product.Attraction;

public class AttractionService {
	
	public List<Attraction> list() {
		
		return DAOFactory.getAttractionDAO().findAll();
	}

	public Attraction create(String name, Double cost, Double duration, Integer capacity,String descripcion,
			String urlSrc) throws Exception {

		Attraction attraction = new Attraction(-1,name, cost, duration, capacity,
				descripcion,urlSrc);
		
		if (attraction.isValid()) {
			AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
			attractionDAO.insert(attraction);
			//XXX: si no devuelve "1", es que hubo más errores
		}

		return attraction;
	}

	public Attraction update(Integer id, String name, Double cost, Double duration, Integer capacity,
			String descripcion, String urlSrc) throws Exception {

		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		Attraction attraction=new Attraction(id,name,cost,duration,capacity,descripcion,urlSrc);
		if (attraction.isValid()) {
			attractionDAO.update(attraction);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return attraction;
	}

	public void delete(Integer id) throws Exception {
		Attraction attraction = new Attraction(id, null, null, null, null,null,null);

		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		attractionDAO.delete(attraction);
	}

	public Attraction find(Integer id) {
		//Para hacer verificar que devuelva algo
		return DAOFactory.getAttractionDAO().findAttractionById(id).get(0);
	}

}
