package scoremanager.main;

import java.util.ArrayList;
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
