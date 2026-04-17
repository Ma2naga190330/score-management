package scoremanager.main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action{
	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		// セッションの取得
		System.out.println("loginAction>OK");
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		// 各種変数の定義
		String entYearStr="";
		String classNum="";
		String isAttendStr="";
		System.out.println("f2>>"+classNum);
		int entYear =0;
		boolean isAttend = false;
		List<Student> students = null;
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		StudentDao sDao = new StudentDao();
		ClassNumDao cNumDao = new ClassNumDao();
		Map<String, String> errors = new HashMap<>();
		// 各種絞り込み条件を取得
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		isAttendStr = req.getParameter("f3");
		// 入学年度が絞り込まれている場合
		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}
		// 在学中の絞り込み
		if (isAttendStr != null) {
			isAttend = true;
		}
		try {
			// ログインユーザーの学校コードをもとにクラスの番号一覧を取得
//			School s = new School();
//			s.setCd("ocm");
//			Teacher teacher = new Teacher();
//			teacher.setSchool(s);
			
			List<String> list = cNumDao.filter(teacher.getSchool());
			// 入学年度とクラス番号を指定
			if (entYear != 0 && !classNum.equals("0")) {
				students = sDao.filter(teacher.getSchool(), entYear,classNum,isAttend);
			// 入学年度のみ
			}else if (entYear != 0 && classNum.equals("0")) {
				students = sDao.filter(teacher.getSchool(),entYear,isAttend);
			// 指定なし
			}else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
				students = sDao.filter(teacher.getSchool(), isAttend);
			// エラー
			}else {
				errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
				req.setAttribute("errors",errors);
				students = sDao.filter(teacher.getSchool(), isAttend);
			}
			if (students != null) {
				System.out.println("studentsにデータはあります");
			}
//			Student sd = new Student();
//			sd.setNo("11111111");
//			sd.setName("サンプル");
//			sd.setEntYear(2000);
//			sd.setClassNum("131");
//			sd.setIsAttend(true);
//			School demos = new School();
//			demos.setCd("oom");
//			sd.setSchool(demos);
//			students.add(sd);
			req.setAttribute("f1",entYear);
			req.setAttribute("f2",classNum);
			if (isAttendStr != null) {
				isAttend = true;
				req.setAttribute("f3", isAttendStr);
			}
			req.setAttribute("students",students);
			req.setAttribute("class_num_set", list);
			req.setAttribute("ent_year_set", entYearSet);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("student_list.jsp").forward(req,res);
	}
}
