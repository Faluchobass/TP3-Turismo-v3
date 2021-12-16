package product;

import java.util.ArrayList;
import java.util.List;

import multiverseTour.User;

public class Itinerary {

	private int userId;
	private List<Offer> acceptedOffers;

	public Itinerary(User user, Offer offer) {
		this.userId=user.getId();
		acceptedOffers=new ArrayList<Offer>();
		this.acceptedOffers.add(offer);
	}
	public void addOffer(Offer offer) {
		this.acceptedOffers.add(offer);
	}
	public int getUserId() {
		return this.userId;
	}

	public List<Offer> getOffers() {
		// TODO Auto-generated method stub
		return this.acceptedOffers;
	}
	public Offer getLastOffer() {
		return this.acceptedOffers.get(acceptedOffers.size()-1);
	}

}
