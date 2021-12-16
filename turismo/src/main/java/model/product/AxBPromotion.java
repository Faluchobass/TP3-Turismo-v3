package model.product;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class AxBPromotion extends Promotion {
	private List<Attraction> extra;
	
	public List<Attraction> getExtra() {
		return extra;
	}
	public AxBPromotion(Integer id,String name, List<Attraction> arrayAttractions,List<Attraction> extra,
			String descripcion,String imgSrc) {
		super(id, name, arrayAttractions,descripcion,imgSrc);
		this.extra=extra;
		//this.setVisitCost(this.calcularDescuento());
	}
	@Override
	public boolean isAcceptedAtraction( List<Attraction> acceptedAttractions) {
		return this.hasAny(acceptedAttractions);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(extra);
		return result;
	}
	

	@Override
	public String toString() {
		String ts=super.toString();
		for(Attraction a: extra)
			ts+="\t+"+a.getName()+" Gratis\t"+a.getTimeRequired() +" horas\n";
		return ts;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AxBPromotion other = (AxBPromotion) obj;
		return Objects.equals(extra, other.extra);
	}
	
	
	@Override
	public boolean hasAny(List<Attraction> attractionList) {
		for(Attraction a: this.extra) {
			if(attractionList.contains(a))
				return true;
		}
		return super.hasAny(attractionList);
	}
	@Override
	public void appendTo(List<Attraction> list) {
		super.appendTo(list);
		for(Attraction attraction:this.extra) {
			list.add(attraction);
		}
	}
	
	@Override
	public double getTimeRequired() {
		double time=super.getTimeRequired();
		for(Attraction a:extra)
			time+=a.getTimeRequired();
		return time;
	}
	@Override
	public boolean checkVacancy() {

		for (Attraction a:this.extra) {
			if (!a.checkVacancy()) {
				return false;
			}
		}
		return super.checkVacancy();
	}
	public LinkedList<PreparedStatement> createUpdateQuotasStatement() {
		LinkedList<PreparedStatement> stmtList=new LinkedList<PreparedStatement>();
		for(Attraction a:this.extra) {
			stmtList.addAll(a.createUpdateQuotasStatement());
		}
		stmtList.addAll(super.createUpdateQuotasStatement());
		return stmtList; 
	}
	HashMap<String,String> errors=new HashMap<String,String>();
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		this.errors = new HashMap<String, String>();


		if (!this.extra.isEmpty()) {
			errors.put("extra", "Sin atracciones extras");
		}

	}
	
	public HashMap<String, String> getErrors() {
		return errors;
	}
	@Override
	public Double getDescuento() {
		
		return null;
	}
	@Override
	public String getTipoDescu() {
	
		return "AxB";
	}
}