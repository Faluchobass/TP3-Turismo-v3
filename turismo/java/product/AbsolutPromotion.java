package product;

import java.util.List;
import java.util.Objects;

public class AbsolutPromotion extends Promotion {
	private final double money;

	public AbsolutPromotion(Integer id, String name, List<Attraction> attractionsList, double money) {
		super(id, name, attractionsList);
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

}