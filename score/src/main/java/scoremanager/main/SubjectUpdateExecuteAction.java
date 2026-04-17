package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");
		
		SubjectDao sDao = new SubjectDao();
		Subject subject = new Subject();
		
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(teacher.getSchool());
		
		boolean flag = sDao.save(subject);
		if (flag) {
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);

		}else {
			req.getRequestDispatcher("/error.jsp").forward(req,res);
		}
			}
}
