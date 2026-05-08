package scoremanager;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginAction extends Action {
	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		try {
			HttpSession session = req.getSession();
			String id = (String) session.getAttribute("id");
			
			if (id == null) {
				id = "";
			}
			req.setAttribute("id", id);
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
