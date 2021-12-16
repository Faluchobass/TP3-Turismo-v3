package controller.attractions;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.product.Itinerary;
import model.product.Offer;
import services.ItineraryService;

@WebServlet("/attractions/itinerary.do")
public class ShowItineraryServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -4251228817176648670L;
	private ItineraryService itineraryService;
	@Override
	public void init() throws ServletException {
		super.init();
		this.itineraryService = new ItineraryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User user=(User)req.getSession().getAttribute("user");
		
		List<Offer> offers=this.itineraryService.listPromotions(user);
		offers.addAll(this.itineraryService.listAttractions(user));
//		List<Attraction> attractions = attractionService.list();
		req.setAttribute("offers", offers);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/attractions/itinerary.jsp");
		dispatcher.forward(req, resp);

	}
}
