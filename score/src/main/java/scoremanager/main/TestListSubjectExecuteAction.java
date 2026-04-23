package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		
		int entYear = Integer.parseInt(req.getParameter("f1"));
		String classNum = req.getParameter("f2");
		String subjectCd = req.getParameter("f3");
		
		SubjectDao sDao = new SubjectDao();
		Subject subject = sDao.get(subjectCd, school);
		TestListSubjectDao tsbDao = new TestListSubjectDao();
		System.out.println("filter>>"+entYear+" "+classNum+" "+subject+" "+school);
		List<TestListSubject> list = tsbDao.filter(entYear, classNum, subject, school);
		for (TestListSubject tls : list) {
			if (tls.getPoint(1) != null) {
				System.out.println("1回目"+tls.getPoint(1));
			}
			if (tls.getPoint(2) != null) {
				System.out.println("2回目"+tls.getPoint(2));
			}
		}
		System.out.println("list>>"+list.size());
		req.setAttribute("subject", subject);
		req.setAttribute("subjectResults", list);
		req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
	}
}
