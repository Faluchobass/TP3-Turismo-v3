package product;

import java.util.Comparator;

public class CostAttractionComparator implements Comparator<Attraction> {


	public int compare(Attraction o1, Attraction o2) {
		return Double.compare(o2.getVisitCost(), o1.getVisitCost());
	}
		
}
