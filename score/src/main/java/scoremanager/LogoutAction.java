package scoremanager;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LogoutAction extends Action{
	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
		}catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("logout.jsp").forward(request, response);
	}
}
