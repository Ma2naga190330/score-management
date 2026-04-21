package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action{
	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws Exception{
		// セッションの取得
		System.out.println("loginAction>OK");
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		String entYearStr = "";
		String classNum = "";
		String subjectCd = "";
		String numStr = "";
		int entYear = 0;
		int num = 0;
		
		List<Test> tests = null;

		SubjectDao sDao = new SubjectDao();
		TestDao tDao = new TestDao();
		Map<String, String> errors = new HashMap<>();
		// データの取得
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subjectCd = req.getParameter("f3");
		numStr = req.getParameter("f4");
		System.out.println("検索"+entYearStr+" "+classNum+" "+subjectCd+" "+numStr);
		// 取得してきたデータが1つでもnullならばerror表示
		
		if (entYearStr != null && !entYearStr.equals("0")) {
			entYear = Integer.parseInt(entYearStr);
		}
		
		if (numStr != null && !numStr.equals("0")) {
			num = Integer.parseInt(numStr);
		}
		
		if (entYearStr != null || classNum != null || subjectCd != null || numStr != null) {
			if (entYear == 0 || classNum.equals("0") || subjectCd == null || subjectCd.equals("0") || num == 0) {
				errors.put("filter", "入学年度とクラスと科目と回数を選択してください");
			}else {
				Subject subject = sDao.get(subjectCd, teacher.getSchool());
				
				tests = tDao.filter(entYear, classNum, subject, num, teacher.getSchool());
			}
		}
		
		// クラス
		ClassNumDao cNumDao = new ClassNumDao();
		List<String> class_num = cNumDao.filter(teacher.getSchool());
		
		// 年度
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}
		// 科目リスト
		List<Subject> subject_list = sDao.filter(teacher.getSchool());
		// 回数
		List<Integer> test_count = new ArrayList<>();
		test_count.add(1);test_count.add(2);
		
		req.setAttribute("f1", entYear);
		req.setAttribute("f2", classNum);
		req.setAttribute("f3", subjectCd);
		req.setAttribute("f4", num);
		
		// プルダウン検索データ
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("subject_list", subject_list);
		req.setAttribute("test_count", test_count);
		req.setAttribute("class_num_set", class_num);
		
		req.setAttribute("tests", tests);
		req.setAttribute("errors", errors);
		
		// フォワード
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}
