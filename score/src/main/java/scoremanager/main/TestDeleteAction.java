package scoremanager.main;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestDeleteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		String studetNo = request.getParameter("student_no");
		String subjectCd = request.getParameter("subject_cd");
		int no = Integer.parseInt(request.getParameter("no"));
		
		Student student = new Student();
		student.setNo(studetNo);
		
		Subject subject = new Subject();
		subject.setCd(subjectCd);
		
		TestDao tDao = new TestDao();
		Test test = tDao.get(student, subject, teacher.getSchool(), no);
		
		request.setAttribute("student_no", test.getStudent().getNo());
		request.setAttribute("subject_cd", test.getSubject().getCd());
		request.setAttribute("no", test.getNo());
		request.setAttribute("point", test.getPoint());
		
		request.getRequestDispatcher("test_delete.jsp").forward(request, response);
	}
}
