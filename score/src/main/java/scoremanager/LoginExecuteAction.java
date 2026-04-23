package scoremanager;

import java.io.IOException;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException{
		try {
			String id = req.getParameter("id");
			String password = req.getParameter("password");
			Teacher t = new Teacher();
			t.setId(id);
			t.setPassword(password);
			TeacherDao tDao = new TeacherDao();
			Teacher user = tDao.loginUser(t);
			if (user != null) {
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				req.getRequestDispatcher("main/Menu.action").forward(req, res);
			}else {
				String error = "IDまたはPasswordが間違っています";
				req.setAttribute("error", error);
				req.getRequestDispatcher("Login.action").forward(req, res);
			}
			req.getRequestDispatcher("/error.jsp").forward(req, res);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
