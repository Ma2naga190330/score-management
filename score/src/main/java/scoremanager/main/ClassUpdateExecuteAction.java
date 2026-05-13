package scoremanager.main;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassUpdateExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		String num = req.getParameter("cd");
		String name = req.getParameter("name");
		
		ClassNumDao cDao = new ClassNumDao();
		ClassNum classNum = new ClassNum();
		System.out.println(num);
		classNum.setClassNum(num);
		classNum.setClassName(name);
		classNum.setSchool(teacher.getSchool());
		
		boolean flag = cDao.save(classNum);
		if(flag) {
			req.setAttribute("num", num);
			req.setAttribute("name", name);
			req.getRequestDispatcher("class_update_done.jsp").forward(req, res);
		} else {
			req.getRequestDispatcher("/error.jsp").forward(req, res);
		}
		
	}
}
