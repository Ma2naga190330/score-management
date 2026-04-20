package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
//import dao.SubjectDao;
//import dao.SubjectDao;
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
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		
		ClassNumDao cNumDao = new ClassNumDao();
//		SubjectDao sDao = new SubjectDao();
		TestDao tDao = new TestDao();
		Map<String, String> errors = new HashMap<>();
		
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subjectCd = req.getParameter("f3");
		numStr = req.getParameter("f4");
		
		if (entYearStr != null && !entYearStr.equals("0")) {
			entYear = Integer.parseInt(entYearStr);
		}
		
		if (numStr != null && !numStr.equals("0")) {
			num = Integer.parseInt(numStr);
		}
		
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}
		
//		req.setAttribute("subject_list", );
		
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}
