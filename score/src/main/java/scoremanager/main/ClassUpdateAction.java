package scoremanager.main;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassUpdateAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		String num = req.getParameter("cd");
//		String name = req.getParameter("name");
		
		ClassNumDao cDao = new ClassNumDao();
		
		ClassNum classNum = cDao.get(num, teacher.getSchool());
		if (classNum != null) {
			req.setAttribute("cd", classNum.getClassNum());
			req.setAttribute("name", classNum.getClassName());
			req.getRequestDispatcher("class_update.jsp").forward(req,res);
		} else {
			req.getRequestDispatcher("/error.jsp").forward(req, res);
		}
	}
}
