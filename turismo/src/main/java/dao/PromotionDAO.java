package dao;

import java.util.Map;

import model.product.Promotion;

public interface PromotionDAO extends GenericDao<Promotion> {
	
	public Promotion findPromotionById(Integer promotionId);
	public int update(Promotion p);
	public int updatePromotionQuota(Promotion p);

}
