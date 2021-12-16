package model.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class Attraction implements Comparable<Attraction>, Offer{

	private String name;
	private double visitCosts;
	private double timeRequired;
	private int quota;
	private Integer id;
	private String descripcion;
	private String imgSrc;
	private HashMap<String, String> errors;

	public Attraction(Integer id,String name, Double visitCost, Double time, Integer quota,
			String description,String imgSrc) throws Exception {
		
		Attraction.validateAttraction(id, name,visitCost, time, quota);
		this.id=id;
		this.name = name;
		this.visitCosts = visitCost;
		this.timeRequired = time;
		this.quota = quota;
		this.descripcion=description;
		this.imgSrc=imgSrc;

	}
	public boolean isAcceptedAtraction( List<Attraction> acceptedAttractions) {
		return acceptedAttractions.contains(this);
	}
	@Override
	public String toString() {
		return "" + name + "\t$" + visitCosts + "\t" + timeRequired + " horas\n";
	}

	public String getName() {
		return this.name;
	}

	public double getVisitCost() {
		return visitCosts;
	}

	public boolean checkVacancy() {
		return this.quota > 0;
	}

	public double getTimeRequired() {
		return this.timeRequired;
	}

	public void subtractAvailability() {
		this.quota--;
	}

	/*
	 * @Override public boolean itsPromotion() { return false; }
	 */

	public void appendTo(List<Attraction> list) {
		list.add(this);
	}
	public void putIn(Hashtable<Integer,Attraction> hashtable) {
		hashtable.put(id, this);
	}
	public void printOffer() {

		System.out.println("Oferta de Atraccion: " + this.getName().toUpperCase() + ".");
		System.out.println("Costo de visita: $" + this.getVisitCost() + " pesos.");
		System.out.println("Duración estimada: " + this.getTimeRequired() + " horas.");
		System.out.println("********************************************************\n");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + quota;
		long temp;
		temp = Double.doubleToLongBits(timeRequired);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(visitCosts);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
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
		Attraction other = (Attraction) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (quota != other.quota) {
			return false;
		}
		if (Double.doubleToLongBits(timeRequired) != Double.doubleToLongBits(other.timeRequired)) {
			return false;
		}
		if (Double.doubleToLongBits(visitCosts) != Double.doubleToLongBits(other.visitCosts)) {
			return false;
		}
		return true;
	}

	public int compareTo(Attraction otra) {
		
		return Double.compare(this.getVisitCost(), otra.getVisitCost()) == 0 ? 
				Double.compare(this.timeRequired, otra.getTimeRequired()) : 
					Double.compare(this.getVisitCost(), otra.getVisitCost());
	}
	
	public LinkedList<PreparedStatement> createUpdateQuotasStatement() {
		PreparedStatement stmt=null;
		LinkedList<PreparedStatement> stmtList=new LinkedList<PreparedStatement>();
		try {
			stmt = connection.ConnectionProvider.getConnection()
					.prepareStatement("UPDATE atracciones\r\n"
							+ "SET cupoDisponible=?\r\n"
							+"WHERE atracciones.id=?");
			stmt.setInt(1, this.quota);
			stmt.setInt(2, this.id);
			stmtList.add(stmt);
		} catch (SQLException e) {
			System.err.println("Error al actualizar el cupo disponible de la atraccion");
			e.printStackTrace();
		}

		return stmtList;
	}

	public PreparedStatement createInsertStatement(Connection connection) {
		PreparedStatement stmt=null;
		try {
			stmt = connection.prepareStatement("INSERT INTO Itinerario (userId,atraccionId)\r\n"
							+ "VALUES\r\n"
							+ "(?,?)");
			stmt.setInt(2, this.id);
		} catch (SQLException e) {
			System.err.println("Error al Insertar atraccion en itinerario en la db");
			e.printStackTrace();
		}
		return stmt;
	}

	public Integer getId() {
		return this.id;
	}
	static public void validateAttraction(Integer id,String name,Double cost,Double duration,Integer capacity) throws Exception {
		if(cost<0)
			throw new Exception("Costo menor a 0");
		if(duration<0)
			throw new Exception("Tiempo menor a 0");
		if(capacity<0)
			throw new Exception("Capacidad total menor a 0");
		if(name=="")
			throw new Exception("Nombre Vacio");
		
	}
	public double getDuration() {
		// TODO Auto-generated method stub
		return this.timeRequired;
	}
	public int getQuota() {
		
		return this.quota;
	}
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (this.visitCosts <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (this.timeRequired <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
		if (this.timeRequired > 60) {
			errors.put("duration", "Excede el tiempo máximo");
		}
		if (this.quota <= 0) {
			errors.put("capacity", "Debe ser positivo");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

}