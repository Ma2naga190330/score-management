package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
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
		
		SubjectDao subDao = new SubjectDao();
		Subject sub = subDao.get(subjectCd, school);
		
		req.setAttribute("f1", entYear);
		req.setAttribute("f2", classNum);
		req.setAttribute("f3", sub);
		
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}
        ClassNumDao classDao = new ClassNumDao();
        List<String> classList = classDao.filter(school);

        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(school);

        req.setAttribute("entYearList", entYearSet);
        req.setAttribute("classList", classList);
        req.setAttribute("subjectList", subjectList);
		
		if (entYear == 0 || classNum.equals("0") || subjectCd.equals("0")) {
			req.setAttribute("error", "入学年度とクラスと科目を選択してください");
			System.out.println("年度・クラス・科目なし");
			req.getRequestDispatcher("test_list.jsp").forward(req, res);
			return;
		}
		
		SubjectDao sDao = new SubjectDao();
		Subject subject = sDao.get(subjectCd, school);
		TestListSubjectDao tsbDao = new TestListSubjectDao();
		System.out.println("filter>>"+entYear+" "+classNum+" "+subject+" "+school);
		List<TestListSubject> list = tsbDao.filter(entYear, classNum, subject, school);
		System.out.println("list>>"+list);
		if  (list.size()==0) {
			req.setAttribute("error", "学生情報が存在しませんでした");
			System.out.println("学生情報存在しない");
			req.getRequestDispatcher("test_list.jsp").forward(req, res);
			return;
		}
		
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
		System.out.println("正常処理");
		req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
	}
}
