package scoremanager.main;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class MenuAction extends Action{
	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
		request.getRequestDispatcher("../scoremanager/main/menu.jsp").forward(request, response);
	}
}
