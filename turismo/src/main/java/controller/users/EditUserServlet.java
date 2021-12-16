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

@WebServlet("/users/edit.do")
public class EditUserServlet  extends HttpServlet {

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

		User user = userService.findUserById(id);
		req.setAttribute("tmp_user", user);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/users/edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(req.getParameter("user_id"));
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		Boolean admin=Boolean.parseBoolean(req.getParameter("admin"));
		Integer money = Integer.parseInt(req.getParameter("coins"));
		Double time = Double.parseDouble(req.getParameter("time"));
		Boolean state=Boolean.parseBoolean(req.getParameter("state"));
		User new_user;
		try {
			new_user=userService.edit( id,name, password, admin, money, time,state);
			if (new_user.isValid()) {
				resp.sendRedirect("/turismo/users/index.do");
			} else {
				req.setAttribute("new_user", new_user);

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/users/edit.jsp");
				dispatcher.forward(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
