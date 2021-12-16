package multiverseTour;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import dao.AttractionDAO;
import dao.DAOFactory;
import dao.ItineraryDAO;
import dao.OfferDAO;
import dao.PromotionDAO;
import dao.UserDAO;
import product.Attraction;
import product.CostAttractionComparator;
import product.CostPromotionComparator;
import product.Itinerary;
import product.Offer;
import product.Promotion;

public class TourSystem {

	private List<Promotion> currentPacks = new ArrayList<Promotion>();
	private List<Attraction> attractions = new ArrayList<Attraction>();
	private List<User> users = new ArrayList<User>();
	private List<Offer> offers = new ArrayList<Offer>();// (new OfferComparator());

	public void addUser(User user) {
		this.users.add(user);
	}

	public void addAttraction(Attraction attraction) {
		this.attractions.add(attraction);
	}

	public void addPromotion(Promotion promotion) {
		this.currentPacks.add(promotion);
	}

	public boolean isAccepted(Promotion promotion, List<Attraction> acceptedAttractions) {
		return promotion.hasAny(acceptedAttractions);
	}

	public boolean isAccepted(Offer offer, List<Attraction> acceptedAttractions) {
		if (!(offer instanceof Attraction))
			return isAccepted((Promotion) offer, acceptedAttractions);
		else
			return acceptedAttractions.contains((Attraction) offer);
	}

	public void offerAccordingUser(User user) {

		List<Attraction> acceptedAttractions = new ArrayList<Attraction>();
		List<Offer> itinerary = new ArrayList<Offer>();
//Arma las ofertas a ofrecer
		this.offers.addAll(this.currentPacks);
		this.offers.addAll(this.attractions);
		itinerary = this.loadItinerarYFor(user);
		for (Offer o : itinerary) {
			o.appendTo(acceptedAttractions);
		}
		System.out.println("");
		System.out.println("<<<<<<<<<<<<<<<< Bienvenid@ al Multiverso! >>>>>>>>>>>>>>>>>>\n");
		System.out.println("Estas son las promociones que tenemos para vos en este dia!\n");
		System.out.println("*************************************************************");
//itera por las ofertas para luego, ver si ya las tiene o no y si son aceptables u ofrecibles
		// . y llama a offeraccepted
		for (Offer offer : offers) {
			if (!offer.isAcceptedAtraction(acceptedAttractions) && !itinerary.contains(offer)
					&& user.isOffertViable((int) offer.getVisitCost(), offer.getTimeRequired())
					&& offer.checkVacancy()) {

				offerAccepted(user, acceptedAttractions, itinerary, offer);
			}
		}
		// teniamos un null en itinerary, queriamos removerlo. Ya solucionamos ese bug
		itinerary.remove(null);
		// Seteamos el itinerario al user
		user.receiveItinerary(itinerary);
		// sacamos el itinerario por pantalla
		this.printItinerary(user);
	}

	private List<Offer> loadItinerarYFor(User user) {
		// Se cargan todas las promociones y atracciones que compro el usuario
		// develve una lista con todas las ofertas aceptadas por el usuario
		ItineraryDAO itineraryDao = DAOFactory.getItineraryDAO();
		Hashtable<Integer, Offer> offersHashtable = new Hashtable<Integer, Offer>();
		LinkedList<Offer> offersList = new LinkedList<Offer>();
		for (Attraction a : this.attractions) {
			offersHashtable.put(a.getId(), a);
		}
		offersList.addAll(itineraryDao.findAcceptedAttractions(user, offersHashtable));
		offersHashtable.clear();
		for (Promotion p : this.currentPacks) {
			offersHashtable.put(p.getId(), p);
		}
		offersList.addAll(itineraryDao.findAcceptedPromotions(user, offersHashtable));
		return offersList;
	}

	private void offerAccepted(User user, List<Attraction> acceptedAttractions, List<Offer> itinerary, Offer offer) {
		// Si acepta la oferta
		if (user.takeOffer(offer)) {
//cambia el cupo en la oferta
			offer.subtractAvailability();
			// Usuario resta su dinero y tiempo
			user.acceptSuggestedOffer(offer);
			// se agrega la oferta al itinerario
			itinerary.add(offer);
			System.out.println("Su saldo disponible es de: " + user.getMoneyAvailability());
			System.out.println("Su tiempo disponible es de: " + user.getTimeRequired());
			System.out.println("*************************************************************\n");
			// Se agrega las atraciones aceptadas en accepted attractions
			offer.appendTo(acceptedAttractions);
			// Se actualiza la base de datos
			ItineraryDAO itienraryDao = DAOFactory.getItineraryDAO();
			itienraryDao.insert(new Itinerary(user, offer));
			UserDAO userDao = DAOFactory.getUserDAO();
			userDao.update(user);
			OfferDAO offerDAO = DAOFactory.getOfferDAO();
			offerDAO.update(offer);
		}
	}

	public void OfferAccordingUsers() {
		Collections.sort(this.currentPacks, new CostPromotionComparator());
		Collections.sort(this.attractions, new CostAttractionComparator());

		for (User user : this.users) {
			System.out.println("Estimad@: " + user.getName().toUpperCase());
			offerAccordingUser(user);
			offers.clear();
		}
	}

	public void loadAttractions() throws FileNotFoundException {
		AttractionDAO attractionDao = DAOFactory.getAtraccionDAO();
		this.attractions = attractionDao.findAll();
	}

	public void loadUsers() throws FileNotFoundException {
		UserDAO userDao = DAOFactory.getUserDAO();
		this.users = userDao.findAll();
		System.out.println(this.users);
	}

	public void loadPromotions() throws FileNotFoundException {
		Hashtable<Integer, Attraction> htAttracciones = new Hashtable<Integer, Attraction>();
		for (Attraction a : this.attractions) {
			a.putIn(htAttracciones);
		}
		PromotionDAO pdao = DAOFactory.getPromotionDAO(htAttracciones);
		this.currentPacks = pdao.findAll();
	}

	public String itineraytoString(User user) {
		int totalCost = 0;
		double totalTime = 0.0;
		String itineraryString = "Estimado Sr/a: " + user.getName() + "\n" + "Ud. ha adquirido: \n";
		for (Offer offer : user.getItinerary()) {
			if (offer != null) {
				totalCost += offer.getVisitCost();
				totalTime += offer.getTimeRequired();
				itineraryString += offer;
			} else {
				itineraryString += "null";
			}
		}
		itineraryString += "\nEl costo total de su itinerario es: " + totalCost + " pesos.\n";
		itineraryString += "El tiempo necesario para realizar su itinerario es: " + totalTime + " horas.\n";
		itineraryString += "Muchas gracias por haber elegido nuestros servicios.¡Esperamos que disfrute su recorrido!\n\n\n\n\n\n\n\n\n\n\n\n";
		return itineraryString;
	}

	public void printItinerary(User user) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		PrintWriter outFile;
		try {
			outFile = new PrintWriter(new FileWriter(dtf.format(now) + " " + user.getName() + " Ticket.out"));
			String systemOutput = this.itineraytoString(user);
			outFile.println(systemOutput);
			System.out.println(systemOutput);
			outFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}