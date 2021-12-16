package product;

import java.util.List;
import java.util.Objects;

public class PercentagePromotion extends Promotion {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(PERCENTAGE_DISCOUNT);
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
		PercentagePromotion other = (PercentagePromotion) obj;
		return Double.doubleToLongBits(PERCENTAGE_DISCOUNT) == Double.doubleToLongBits(other.PERCENTAGE_DISCOUNT);
	}

	private double PERCENTAGE_DISCOUNT;

	public PercentagePromotion(Integer id,String name, List<Attraction> attractionsList, double discount) {
		super(id, name, attractionsList);
		this.PERCENTAGE_DISCOUNT = discount;

	}

	@Override
	public double getVisitCost() {
		return Math.round(super.getVisitCost() * (1 - PERCENTAGE_DISCOUNT));

	}

	@Override
	public void printOffer() {
		System.out.println("La promoción ofrecida es: " + this.getName().toUpperCase() + ".");
		System.out.println("Las atracciones incluidas son: \n");
		for (Attraction attraction : this.getArrayAttractions()) {
			System.out.println(". " + attraction.getName().toUpperCase());
		}
		System.out.println("Costo de Promoción: $" + this.getVisitCost() + " pesos.");
		System.out.println("Duración estimada: " + this.getTimeRequired() + " horas.");
		System.out.println("*******************************************************************\n");
	}

}