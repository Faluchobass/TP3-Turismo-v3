package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Promotion implements Offer, Comparable<Promotion> {


	protected List<Attraction> includedAttractions = new ArrayList<Attraction>();
	private String name;
	//cambio 
	private double visitCost;
	private Integer id;
	public Promotion(Integer id,String promotionName, List<Attraction> attractionList) {
		this.setName(promotionName);
		this.setArrayAttractions(attractionList);
		this.visitCost = this.calcularCosto();
		this.id=id;
	}
	public Integer getId() {
		return this.id;
	}
	public int compareTo(Promotion otra) {
		return Double.compare(this.getVisitCost(), otra.getVisitCost()) == 0 ? Double.compare(this.getTimeRequired(), otra.getTimeRequired()) : Double.compare(this.getVisitCost(), otra.getVisitCost());
	}
	//funcion que se agrega para retornar el costo de visita
	public double calcularCosto() {
		double totalCost = 0;

		for (Attraction a : this.includedAttractions) {
			totalCost += a.getVisitCost();
		}
		return Math.round(totalCost);
	}
	
	public void setVisitCost(double visitCost) {
		this.visitCost = visitCost;
	}
	@Override
	public String toString() {
		String ts=this.name+"\n";
		for(Attraction a: includedAttractions)
			ts+="\t+"+a+"\n";
		ts += "\tPrecio Final: " + this.getVisitCost() + "\n";
		return ts;
	}
	public boolean hasAny(List<Attraction> attractionList) {
		for(Attraction a: this.includedAttractions) {
			if(attractionList.contains(a))
				return true;
		}
		return false;
	}
	
	public String getName() {
		return this.name;
	}

	public void subtractAvailability() {

		for (int i = 0; i < includedAttractions.size(); i++) {
			includedAttractions.get(i).subtractAvailability();
		}
	}

	public boolean checkVacancy() {

		for (Attraction a:this.includedAttractions) {
			if (!a.checkVacancy()) {
				return false;
			}
		}
		return true;
	}
	public double getTimeRequired() {
		double totalTime = 0;

		for (int i = 0; i < includedAttractions.size(); i++) {
			totalTime += includedAttractions.get(i).getTimeRequired();
		}
		return totalTime;
	}

	public void appendTo(List<Attraction> list) {
		for(Attraction attraction:this.includedAttractions) {
			list.add(attraction);
		}
	}

	public void printOffer() {

		System.out.println("La promocion ofrecida es: " + this.getName().toUpperCase() + ".");
		System.out.println("Las atracciones incluidas son: \n");

		for (int i = 0; i < this.getArrayAttractions().size(); i++) {
			System.out
					.println((i + 1) + ". " + this.getNameAttraction(this.getArrayAttractions().get(i)).toUpperCase());
		}
		System.out.println("Costo de Promoción: $" + this.getVisitCost() + " pesos.");
		System.out.println("Duracionn estimada: " + this.getTimeRequired() + " horas.");
		System.out.println("*******************************************************************\n");
	}

	public String getNameAttraction(Attraction attraction) {
		return attraction.getName();
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Attraction> getArrayAttractions() {
		return this.includedAttractions;
	}

	// Paso por parámetro las atracciones que estarán incluídas en la promoción.

	public void setArrayAttractions(List<Attraction> includedAttractions) {
		this.includedAttractions = includedAttractions;
	}
	
	public double getVisitCost() {
		return this.visitCost;
	}
	public boolean hasAttraction(Attraction a) {
		return this.includedAttractions.contains(a);
		
	}
	public PreparedStatement createInsertStatement(Connection connection) {
		PreparedStatement stmt=null;
		try {
			stmt = connection.prepareStatement("INSERT INTO Itinerario (userId,promocionId)\r\n"
					+ "VALUES\r\n"
					+ "(?,?)");
			stmt.setInt(2, this.id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;
	}
	public boolean isAcceptedAtraction( List<Attraction> acceptedAttractions) {
		return this.hasAny(acceptedAttractions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(includedAttractions, name);
	}
	public LinkedList<PreparedStatement> createUpdateStatement() {
		LinkedList<PreparedStatement> stmtList=new LinkedList<PreparedStatement>();
		for(Attraction a:this.includedAttractions) {
			stmtList.addAll(a.createUpdateStatement());
		}
		return stmtList;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promotion other = (Promotion) obj;
		return Objects.equals(includedAttractions, other.includedAttractions) && Objects.equals(name, other.name);
	}
	
}