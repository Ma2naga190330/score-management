package scoremanager.main;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassDeleteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		String cd = req.getParameter("cd");
		
		ClassNumDao cDao = new ClassNumDao();
		ClassNum classNum = cDao.get(cd, teacher.getSchool());

		req.setAttribute("delete_class", classNum);
		req.setAttribute("subject_cd", classNum.getClassNum());
		req.setAttribute("class_name", classNum.getClassName());
		req.getRequestDispatcher("class_delete.jsp").forward(req, res);
	}
}
