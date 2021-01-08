package regandlog.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import regandlog.bean.User;
import regandlog.dao.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			User user = new UserDAO().get(username, password);

			if (user != null) {
				req.getSession().setAttribute("user", user);
				req.getRequestDispatcher("/loginCG.jsp").forward(req, resp);
			} else {
				req.setAttribute("message", "ÓÃ»§Ãû»òÃÜÂë´íÎó");
				req.getRequestDispatcher("/loginSB.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			req.setAttribute("message", "µÇÂ¼Ê§°Ü: " + e.getLocalizedMessage());
			req.getRequestDispatcher("/loginSB.jsp").forward(req, resp);
		}
	}
}
