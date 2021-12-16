package model.product;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AbsolutPromotion extends Promotion {
	private final double money;
	

	public AbsolutPromotion(Integer id, String name, List<Attraction> attractionsList, double money,
			String descripcion,String imgSrc)  {
		super(id, name, attractionsList,descripcion,imgSrc);
		this.money = money;
		this.setVisitCost(this.money);
	}

	@Override
	public double getVisitCost() {
		return money;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(money);
		return result;
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
		AbsolutPromotion other = (AbsolutPromotion) obj;
		return Double.doubleToLongBits(money) == Double.doubleToLongBits(other.money);
	}
	HashMap<String,String> errors=new HashMap<String,String>();
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		this.errors = new HashMap<String, String>();

		if (this.money < 0) {
			errors.put("money", "El costo no debe ser negativo");
		}

	}
	
	public HashMap<String, String> getErrors() {
		return errors;
	}

	@Override
	public Double getDescuento() {
		
		return this.money;
	}

	@Override
	public String getTipoDescu() {
		// TODO Auto-generated method stub
		return "Absoluto";
	}

}