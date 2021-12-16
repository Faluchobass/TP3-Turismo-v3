package model.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

public interface Offer  {
	
	public String getName();
	
	public double getVisitCost();
	
	public double getTimeRequired();
	
	//Este método trae el tipo de la atracción o promoción.
	//public TipoDeAtraccion getTipo();
	
	public boolean checkVacancy();	
	
	public void subtractAvailability();
	
	public void printOffer();
	
	public boolean isAcceptedAtraction( List<Attraction> acceptedAttractions); 
	public void appendTo(List<Attraction> acceptedAttractions);

	public PreparedStatement createInsertStatement(Connection connection);
	public LinkedList<PreparedStatement> createUpdateQuotasStatement();
	public Integer getId();
	public String getDescripcion();
	public String getImgSrc();

}