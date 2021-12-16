package multiverseTour;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import product.Offer;

public class User {
	private String name;
	protected double money;
	protected double time;
	private List<Offer> itinerary;
	private Integer id;
	
	public User(Integer id,String name, double moneyAvailability, double timeRequired) {
		this.id=id;
		this.name = name;
		this.money = moneyAvailability;
		this.time = timeRequired;
	}
	public boolean isOffertViable(int money,double time) {
		return this.money>=money && this.time>=time;
				
	}
	public String getName() {
		return this.name;
	}

	public double getMoneyAvailability() {
		return this.money;
	}

	public double getTimeRequired() {
		return Math.round(this.time);
	}

	public List<Offer> getItinerary() {
		return this.itinerary;
	}

	public void receiveItinerary(List<Offer> itinerary) {
		this.itinerary = itinerary;
	}

	public void acceptSuggestedOffer(Offer suggest) {

		this.time -= suggest.getTimeRequired();
		this.money -= suggest.getVisitCost();
	}


	Scanner scan = new Scanner(System.in).useDelimiter("\n");

	public boolean takeOffer(Offer offer) {

		//offer.printOffer();
		System.out.println(offer);
		System.out.println("¿Desea la oferta?");
		String answer,  S = "Si", N = "No";

		do {
			System.out.println("");
			System.out.print("Por favor, ingrese Si o No: ");
			answer = scan.nextLine();
			

			if (answer.equalsIgnoreCase(S)) {
				System.out.println("Ud ha adquirido el producto: " + offer.getName()+"\n");
				System.out.println("*************************************************************");
				
				return true;
			}
			if (answer.equalsIgnoreCase(N)) {
				System.out.println("Usted no adquirió el producto: " + offer.getName()+"\n");
				System.out.println("*************************************************************");
				return false;
			}
		} while (true);
	}
	public int getId() {
		return this.id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(itinerary, money, name, scan, time);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		return Objects.equals(itinerary, other.itinerary) && money == other.money && Objects.equals(name, other.name)
				&& Objects.equals(scan, other.scan)
				&& Double.doubleToLongBits(time) == Double.doubleToLongBits(other.time);
	}

}