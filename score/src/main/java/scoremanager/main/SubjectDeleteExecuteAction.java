package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action{
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		String subject_cd = req.getParameter("subject_cd");
		String subject_name = req.getParameter("subject_name");
		
		SubjectDao sDao = new SubjectDao();
		Subject subject = new Subject();
		subject.setCd(subject_cd);
		subject.setName(subject_name);
		subject.setSchool(teacher.getSchool());
		
		sDao.delete(subject);
		
		req.setAttribute("subject_cd", subject_cd);
		req.setAttribute("subject_name", subject_name);
		
		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
	}
}
