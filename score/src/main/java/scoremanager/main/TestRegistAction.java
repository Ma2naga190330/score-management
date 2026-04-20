package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action{
	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws Exception{
		
		System.out.println("subject_cd=" + req.getParameter("subject_cd"));
        System.out.println("class_num=" + req.getParameter("class_num"));
        System.out.println("no=" + req.getParameter("no"));
		// セッションの取得
		System.out.println("loginAction>OK");
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		
		String entYear = req.getParameter("ent_year");
		String classNum = req.getParameter("class_num");
		String subjectCd = req.getParameter("subject_cd");
		String no = req.getParameter("no");
		
		// 今年から見て過去10年分のlistを送る
		req.setAttribute("ent_year", entYear);
		// classNumDaoを使用してクラス一覧を取得送付
		req.setAttribute("class_num", classNum);
		// 1,2のデータが入ったlistを送る
		req.setAttribute("no", no);
		
		SubjectDao subjectDao = new SubjectDao();
		List<Subject> subjectList = subjectDao.filter(school);
		
		req.setAttribute("subject_list", subjectList);
		
		if (entYear != null && classNum != null && subjectCd != null && no != null) {
			Subject subject = new Subject();
			subject.setCd(subjectCd);
			
			TestDao testDao = new TestDao();
			List<Test> tests = testDao.filter(Integer.parseInt(entYear), classNum, subject, Integer.parseInt(no), school);
			req.setAttribute("tests", tests);
		}
		
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}
