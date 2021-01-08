package regandlog.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import regandlog.bean.User;
import regandlog.dao.UserDAO;

@WebServlet("/regist")
public class RegistServlet extends HttpServlet {
	@Override // 返回包含 form 表单的页面
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/regist.jsp").forward(req, resp);
	}
	
	@Override // 进行数据库的插入，然后返回成功页面
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setUserType(1);
			
			new UserDAO().insert(user);
			
			req.setAttribute("message", "注册成功");
			req.getRequestDispatcher("/registResult.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("message", "注册失败: " + e.getLocalizedMessage());
			req.getRequestDispatcher("/registResult.jsp").forward(req, resp);
		}
	}
}



