package scoremanager.main;

import bean.ClassNum;
import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCreateExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		School school = teacher.getSchool();
		
		String num = req.getParameter("cd");
		String name = req.getParameter("name");
		boolean flag = false;
		
		ClassNumDao dao = new ClassNumDao();
		ClassNum classNum = dao.get(num, school);
		
		if(classNum != null) {
			req.setAttribute("error", "クラス番号が重複しています");
			req.getRequestDispatcher("class_create.jsp").forward(req, res);
			flag = true;
		}
		
		if(flag == false) {
			classNum = new ClassNum();
			classNum.setClassNum(num);
			classNum.setClassName(name);
			classNum.setSchool(school);
			dao.save(classNum);
			req.getRequestDispatcher("class_create_done.jsp").forward(req, res);
		}
	}
}
