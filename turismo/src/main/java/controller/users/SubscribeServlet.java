package controller.users;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.product.Attraction;
import services.UserService;

@WebServlet("/users/alta.do")
public class SubscribeServlet  extends HttpServlet {

	private static final long serialVersionUID = -1655843151169054989L;
	private UserService userService;
	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		User user = userService.changeState( id,true);
		
	resp.sendRedirect("/turismo/users/index.do");
	}
	

}
