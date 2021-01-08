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
	@Override // ���ذ��� form ����ҳ��
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/regist.jsp").forward(req, resp);
	}
	
	@Override // �������ݿ�Ĳ��룬Ȼ�󷵻سɹ�ҳ��
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setUserType(1);
			
			new UserDAO().insert(user);
			
			req.setAttribute("message", "ע��ɹ�");
			req.getRequestDispatcher("/registResult.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("message", "ע��ʧ��: " + e.getLocalizedMessage());
			req.getRequestDispatcher("/registResult.jsp").forward(req, resp);
		}
	}
}



