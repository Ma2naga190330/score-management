package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		
		String studentNo = req.getParameter("f4");
		StudentDao sDao = new StudentDao();
		Student student = sDao.get(studentNo);
		if (student == null) {
			req.getRequestDispatcher("TestList.action").forward(req, res);
		}
		TestListStudentDao tlsDao = new TestListStudentDao();
		List<TestListStudent> list = tlsDao.filter(student);
		System.out.println("tlsDao.filter>>"+list.size());
		
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		ClassNumDao classDao = new ClassNumDao();
		List<String> classList = classDao.filter(school);
		SubjectDao subjectDao = new SubjectDao();
		List<Subject> subjectList = subjectDao.filter(school);
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}
        req.setAttribute("entYearList", entYearSet);
        req.setAttribute("classList", classList);
        req.setAttribute("subjectList", subjectList);
		
		
        req.setAttribute("student", student);
		req.setAttribute("test_student", list);
		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}
}
