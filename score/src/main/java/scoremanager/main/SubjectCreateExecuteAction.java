package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");
		boolean flag = false;
		
		if (cd.length() != 3) {
			request.setAttribute("error", "科目コードは3文字で入力してください");
			request.getRequestDispatcher("subject_create.jsp").forward(request, response);
			flag = true;
		}
		
		SubjectDao dao = new SubjectDao();
		
		Subject subject = dao.get(cd, school);
		
		if (subject != null) {
			request.setAttribute("error", "科目コードが重複しています");
			request.getRequestDispatcher("subject_create.jsp").forward(request, response);
			flag = true;
		}
		
		if (flag == false) {
			subject = new Subject();
			subject.setCd(cd);
			subject.setName(name);
			subject.setSchool(school);
			
			dao.save(subject);
			
			request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
		}
	}
}
