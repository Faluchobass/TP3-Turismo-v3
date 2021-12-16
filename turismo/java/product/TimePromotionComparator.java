package product;

import java.util.Comparator;

public class TimePromotionComparator implements Comparator<Promotion> {

	public int compare(Promotion o1, Promotion o2) {
		return Double.compare(o1.getTimeRequired(), o2.getTimeRequired());
	}
	
}
