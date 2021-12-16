//package controller.promotions;
//
//import java.io.IOException;
//import java.util.List;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.Servlet;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import model.Attraction;
//import services.AttractionService;
//
//@WebServlet("/promotions/index.do")
//public class ListPromotionServlet extends HttpServlet implements Servlet {
//
//	private static final long serialVersionUID = -8346640902238722429L;
//	private PromotionService promotionService;
//
//	@Override
//	public void init() throws ServletException {
//		super.init();
//		this.promotionService = new PromotionService();
//	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		List<Attraction> promotions = promotionService.list();
//		req.setAttribute("promotions", promotions);
//
//		RequestDispatcher dispatcher = getServletContext()
//				.getRequestDispatcher("/views/attractions/indexAttraction.jsp");
//		dispatcher.forward(req, resp);
//
//	}
//
//}
