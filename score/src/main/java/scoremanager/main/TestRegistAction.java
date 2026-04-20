package scoremanager.main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action{
	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		
		String entYearInt = req.getParameter("f1");
		String classNum = req.getParameter("f2");
		String subjectCd = req.getParameter("f3");
		String noInt = req.getParameter("f4");
				
		int entYear = 0;
		int num = 0;
		boolean isAttend = false;
		List<Test> tests = null;
		LocalDate tobaysDate = LocalDate.now();
		int year = tobaysDate.getYear();
		
		Map<String, String> errors = new HashMap<>();
		
		SubjectDao sDao = new SubjectDao();
		ClassNumDao cDao = new ClassNumDao();
		TestDao tDao = new TestDao();
		
		if (entYearInt != null) {
			entYear = Integer.parseInt(entYearInt);
			num = Integer.parseInt(noInt);
		}
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i <= year; i++) {
			entYearSet.add(i);
		}
		
		List<String> classNumSet = cDao.filter(teacher.getSchool());
		List<Subject> subjectSet = sDao.filter(teacher.getSchool());
		
		try {;
		// 入学年度とクラス番号を指定
		if (entYearInt != null && !entYearInt.equals("0")) {
			tests = tDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		// 入学年度のみ
		} else if (classNum != null && !classNum.equals("0")) {
			tests = tDao.filter(teacher.getSchool(), entYear, isAttend);
		// 科目のみ
		} else if (subjectCd == null && !subjectCd.equals("0")) {
			tests = tDao.filter(entYear, classNum, null, year, school);
		// 回数のみ
		} else if (noInt == null && !noInt.equals("0")) {
			tests = tDao.filter(year, noInt, null, year, school);
		// エラー
		} else {
			errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
			req.setAttribute("errors", errors);
		}
		
		req.setAttribute("ent_year_set", entYearSet);
		
		req.setAttribute("class_num_set", classNumSet);
		
		req.setAttribute("subject_list", sDao);
		
		req.setAttribute("f1", entYearInt);
		
		req.setAttribute("f2", classNum);
		
		req.setAttribute("f3", subjectSet);
		
		req.setAttribute("f4", noInt);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}
