package scoremanager.main;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassDeleteExecuteAction extends Action{
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		String class_num = req.getParameter("class_num");
		String class_name = req.getParameter("class_name");
		
		ClassNumDao cDao = new ClassNumDao();
		ClassNum classNum = new ClassNum();
		classNum.setClassNum(class_num);
		classNum.setClassName(class_name);
		classNum.setSchool(teacher.getSchool());
		if (cDao.delete(classNum)) {
			req.setAttribute("subject_cd", class_num);
			req.setAttribute("class_name", class_name);
			req.getRequestDispatcher("class_delete_done.jsp").forward(req, res);;
		}else {
			req.getRequestDispatcher("/error.jsp");
		}
	}
}
