package dao;

import product.Attraction;

public interface AttractionDAO extends GenericDao<Attraction> {
	
	public int setAttractionQuotas();
}
