package model.product;

import java.util.Comparator;

public class OfferComparator implements Comparator<Offer> {

//	@Override
//	public int compare(Offer offer, Offer aux) {
//		if ((offer instanceof Promotion) && (aux instanceof Promotion)) {
//			return -1*((Promotion) offer).compareTo((Promotion)aux);
//		} else 
//			return -1*((Attraction)offer).compareTo((Attraction)aux);
//	}
	
	public int compare(Offer offer, Offer aux) {

		if ((offer instanceof Promotion) && (aux instanceof Promotion)) {
			return -1*((Promotion) offer).compareTo((Promotion)aux);
		} else if ((offer instanceof Attraction) && (aux instanceof Attraction)) {
			return -1*((Attraction)offer).compareTo((Attraction)aux);
		}else if((offer instanceof Promotion) &&  (aux instanceof Attraction)) {
			return -1;
		}else  {
			return 1;
		}
		
	}

}