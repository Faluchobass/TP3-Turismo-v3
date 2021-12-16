package dao;

import product.Promotion;

public interface PromotionDAO extends GenericDao<Promotion> {
	
	public int setPromotionQuotas();
}
