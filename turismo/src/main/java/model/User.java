package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import dao.DAOFactory;
import model.product.Offer;
import utils.Crypt;

public class User {
	private String name;
	protected double money;
	protected double time;
	private List<Offer> itinerary;
	private Integer id;
	private String password;
	private Boolean admin;
	private Boolean state;
	HashMap<String,String> errors=new HashMap<String,String>();
//	public User(Integer id,String name, double moneyAvailability, double timeRequired,String password) {
//		this.id=id;
//		this.name = name;
//		this.money = moneyAvailability;
//		this.time = timeRequired;
//		this.password=password;
//	}
	public User(int id, String name, String password, int admin, double money, double time,Boolean state) {
		this.id=id;
		this.name = name;
		this.password=password;
		this.admin=admin!=0;
		this.money = money;
		this.time = time;
		this.state=state;
		this.itinerary=new ArrayList<Offer>();
		
	}
	public User(Integer id2, String name2, String password2, Boolean admin2, Integer money2, Double time2,Boolean state) {
		this.id=id2;
		this.name = name2;
		this.password=password2;
		this.admin=admin2;
		this.money = money2;
		this.time = time2;
		this.state=state;
		this.itinerary=new ArrayList<Offer>();
	}
	public boolean isOffertViable(int money,double time) {
		return canAfford(money) && canAttend(time);
				
	}
	public boolean canAttend(double time) {
		return this.time>=time;
	}
	public boolean canAfford(int money) {
		return this.money>=money;
	}
	public String getName() {
		return this.name;
	}

	public double getMoney() {
		return this.money;
	}

	public double getTime() {
		return Math.round(this.time);
	}

	public List<Offer> getItinerary() {
		return this.itinerary;
	}

	public void receiveItinerary(List<Offer> itinerary) {
		this.itinerary = itinerary;
	}

	public void acceptSuggestedOffer(Offer suggest) {
		this.itinerary.add(suggest);
		this.time -= suggest.getTimeRequired();
		this.money -= suggest.getVisitCost();
	}


	public int getId() {
		return this.id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}

	public boolean checkPassword(String candidate) {
		return Crypt.match(candidate, this.password);
	}
	public String getPassword() {
		return this.password;
	}
	public boolean isAdmin() {
		return this.admin;
	}
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		this.errors = new HashMap<String, String>();

		if (this.money < 0) {
			errors.put("coins", "No debe ser negativo");
		}
		if (this.time < 0) {
			errors.put("time", "No debe ser negativo");
		}
	}
	
	public HashMap<String, String> getErrors() {
		return errors;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	} 
}