package dao;

import java.util.List;

import model.product.Attraction;

public interface AttractionDAO extends GenericDao<Attraction> {
	
	public int setAttractionQuotas(Attraction t);

	public List<Attraction> findAttractionById(Integer id);
}
