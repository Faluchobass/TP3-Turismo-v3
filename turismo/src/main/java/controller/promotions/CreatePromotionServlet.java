package controller.promotions;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.product.Attraction;
import model.product.Promotion;
import services.AttractionService;
import services.PromotionService;

@WebServlet("/promotions/create.do")
public class CreatePromotionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private PromotionService promotionService; 
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
		this.promotionService = new PromotionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Attraction> attractions = attractionService.list();
		req.setAttribute("attractions", attractions);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/promotions/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		
//		Integer cost = Integer.parseInt(req.getParameter("cost"));
//		Double duration = Double.parseDouble(req.getParameter("duration"));
//		Integer capacity = Integer.parseInt(req.getParameter("capacity"));

		//Promotion promotion = promotionService.create(name, 0, 0, 0);
		
//		if (attraction.isValid()) {
			resp.sendRedirect("/turismo/promotions/index.do");
//		} else {
//			req.setAttribute("attraction", attraction);
//
//			RequestDispatcher dispatcher = getServletContext()
//					.getRequestDispatcher("/views/attractions/create.jsp");
//			dispatcher.forward(req, resp);
//		}

	}

}
