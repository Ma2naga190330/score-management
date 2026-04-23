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

public class TestDeleteExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		String studentNo = request.getParameter("tudent_no");
		String subjectCd = request.getParameter("subject_cd");
		int no = Integer.parseInt(request.getParameter("no"));
		
		Student student = new Student();
		student.setNo(studentNo);
		
		Subject subject = new Subject();
		subject.setCd(subjectCd);
		
		Test test = new Test();
		test.setStudent(student);
		test.setSubject(subject);
		test.setSchool(teacher.getSchool());
		test.setNo(no);
		
		TestDao tDao = new TestDao();
		tDao.delete(test);
		
		request.setAttribute("student_no", studentNo);
		request.setAttribute("subject_cd", subjectCd);
		request.setAttribute("no", no);
		request.getRequestDispatcher("test_delete_done.jsp").forward(request, response);
	}
}
