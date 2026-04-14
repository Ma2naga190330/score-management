package scoremanager;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class LoginAction extends Action {
	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		try {
			String id = "admin";
			req.setAttribute("id", id);
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
