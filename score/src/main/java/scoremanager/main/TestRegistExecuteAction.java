package scoremanager.main;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			System.out.println("TestRegistExecuteAction");
			HttpSession session = request.getSession();
			Teacher teacher = (Teacher)session.getAttribute("user");
			School school = teacher.getSchool();
			System.out.println("sessionOK>>");
			String entYearStr = request.getParameter("f1");
			int entYear = Integer.parseInt(entYearStr);
			String subjectCd = request.getParameter("subject_cd");
			String classNum = request.getParameter("class_num");
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("studentNo+point+in");
			String[] studentNo = request.getParameterValues("student_no");
			String[] point = request.getParameterValues("point");
			Subject subject = new Subject();
			subject.setCd(subjectCd);
			subject.setSchool(school);
			
			boolean flag = false;
			HashMap<String,String> pointError = new HashMap<String,String>();
			List<Test> list = new ArrayList<>();
			for (int i = 0; i < studentNo.length; i++) {
				Test test = new Test();
				
				StudentDao sDao = new StudentDao();
				Student student = sDao.get(studentNo[i]);
				System.out.println("TestRegistExecute_student_no"+studentNo[i]);
				
				test.setStudent(student);
				test.setSubject(subject);
				test.setSchool(school);
				test.setClassNum(classNum);
				test.setNo(no);
				
				if (point[i] != null && !point[i].equals("")) {
					int p = Integer.parseInt(point[i]);
					if (p < 0 || p > 100) {
						flag = true;
						pointError.put(studentNo[i],"エラー");
						System.out.println(studentNo[i]+" "+pointError.get(studentNo[i]));
					}
					test.setPoint(p);
				}
					list.add(test);
			}
				
			if (flag) {
				request.setAttribute("f1", entYear);
				request.setAttribute("f2", classNum);
				request.setAttribute("f3", subjectCd);
				request.setAttribute("f4", no);
				request.setAttribute("sub", subject);
				request.setAttribute("pointError", pointError);
				request.setAttribute("tests", list);
				request.getRequestDispatcher("test_regist.jsp").forward(request, response);
				return;
=======
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			System.out.println("TestRegistExecuteAction");
			HttpSession session = request.getSession();
			Teacher teacher = (Teacher)session.getAttribute("user");
			School school = teacher.getSchool();
			System.out.println("sessionOK>>");
			String subjectCd = request.getParameter("subject_cd");
			String classNum = request.getParameter("class_num");
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("studentNo+point+in");
			String[] studentNo = request.getParameterValues("student_no");
			String[] point = request.getParameterValues("point");
			
			Subject subject = new Subject();
			subject.setCd(subjectCd);
			subject.setSchool(school);
			
			List<Test> list = new ArrayList<>();
			for (int i = 0; i < studentNo.length; i++) {
				Test test = new Test();
				
				Student student = new Student();
				student.setNo(studentNo[i]);
				System.out.println("TestRegistExecute_student_no"+studentNo[i]);
				
				test.setStudent(student);
				test.setSubject(subject);
				test.setSchool(school);
				test.setClassNum(classNum);
				test.setNo(no);
				
				if (point[i] != null && !point[i].equals("")) {
					test.setPoint(Integer.parseInt(point[i]));
				}
				list.add(test);
>>>>>>> refs/remotes/origin/test_manager2
			}
			System.out.println("for>end");
			TestDao dao = new TestDao();
			dao.save(list);
			System.out.println("登録OK");
			request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("error", "登録に失敗しました");
			request.getRequestDispatcher("test_regist.jsp").forward(request, response);
		}
	}
}
