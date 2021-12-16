package services;
import java.util.List;
import dao.DAOFactory;
import dao.PromotionDAO;
import model.product.AbsolutPromotion;
import model.product.Attraction;
import model.product.AxBPromotion;
import model.product.PercentagePromotion;
import model.product.Promotion;
public class PromotionService {
	
	public List<Promotion> list(){
		return DAOFactory.getPromotionDAO().findAll();
	}
	
	public Promotion createPorcentagePromotion(String promotionName, List<Attraction> attractions, double discount,
			String descripcion,String imgSrc) throws Exception {
		Promotion promotion = new PercentagePromotion(-1, promotionName, attractions, discount,descripcion,imgSrc);
		
		if(promotion.isValid()) {
			PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
			promotionDAO.insert(promotion);
		}
		
		return promotion;
	}
	public Promotion createAxBPromotion(String promotionName, List<Attraction> attractions, List<Attraction> extraAttractions,
			String descripcion,String imgSrc) throws Exception {
		Promotion promotion = new AxBPromotion(-1,promotionName, attractions, extraAttractions,descripcion,imgSrc);
		
		if(promotion.isValid()) {
			PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
			promotionDAO.insert(promotion);
		}
		return promotion;
	}
	
	public Promotion createAbsolutPromotion(String promotionName, List<Attraction> attractions, Double cost,
			String descripcion,String imgSrc) throws Exception {
		 Promotion promotion = new AbsolutPromotion(-1, promotionName, attractions, cost,descripcion,imgSrc);
		
		 if(promotion.isValid()) {
			 PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
			 promotionDAO.insert(promotion);
		 }
		
		 return promotion;
	}
	
	public Promotion updatePercentagePromotion(Integer id, String promotionName, List<Attraction> attractions, Double discount,
			String descripcion,String imgSrc) throws Exception {
		PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
		Promotion promotion = new PercentagePromotion(id, promotionName, attractions, discount,descripcion,imgSrc);
		
		if(promotion.isValid()) {
			promotionDAO.update(promotion);
		}
		
		return promotion;
	}
	
	public Promotion updateAbsolutPromotion(Integer id, String namePromotion, List<Attraction> attractions, Double cost,
			String descripcion,String imgSrc) throws Exception {
		PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
		Promotion promotion = new AbsolutPromotion(id, namePromotion, attractions, cost,descripcion,imgSrc);
		
		if(promotion.isValid()) {
			promotionDAO.update(promotion);
		}
		
		return promotion;
	}
	
	public Promotion updateAxBPromotion(Integer id,String promotionName, List<Attraction> attractions, List<Attraction> extraAttractions,
			String descripcion,String imgSrc) throws Exception {
		PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
		Promotion promotion = new AxBPromotion(id, promotionName,attractions, extraAttractions,descripcion,imgSrc);
		
		if(promotion.isValid()) {
			promotionDAO.update(promotion);
		}
		return promotion;
	}
	
	public void delete(Integer id) {
		PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
		Promotion promotion = promotionDAO.findPromotionById(id);
		promotionDAO.delete(promotion);
	}
	
	public Promotion find(Integer id) {
		return DAOFactory.getPromotionDAO().findPromotionById(id);
	}
}
