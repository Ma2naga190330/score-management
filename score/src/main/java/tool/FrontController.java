package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= ("*.action"))
public class FrontController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		try {
			String path = req.getServletPath().substring(1);
			String name = path.replace(".a", "A").replace('/', '.');
			
			System.out.println("★ servlet path ->"+req.getServletPath());
			System.out.println("★ class name ->"+name);
			
			Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();
			
			action.execute(req, res);
		}catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/error.jsp").forward(req, res);
		}
//		res.getWriter().append("Served at: ").append(req.getContextPath());
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException{
		doGet(req,res);
	}
}
