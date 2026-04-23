package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action{
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		
		String studentNo = req.getParameter("f4");
		
		StudentDao sDao = new StudentDao();
		Student student = sDao.get(studentNo);
		
		TestListStudentDao tlsDao = new TestListStudentDao();
		List<TestListStudent> list = tlsDao.filter(student);
		
		req.setAttribute("student", student);
		req.setAttribute("test_student", list);
		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}
}
