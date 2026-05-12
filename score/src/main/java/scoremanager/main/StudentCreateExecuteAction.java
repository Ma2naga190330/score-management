package scoremanager.main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;


public class StudentCreateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		try {
			int ent_year =Integer.parseInt(request.getParameter("ent_year"));
			String no = request.getParameter("no");
			String name = request.getParameter("name");
			String class_num = request.getParameter("class_num");
			HttpSession session = request.getSession();
			Teacher teacher = (Teacher) session.getAttribute("user");
			Student stu = new Student();
			stu.setEntYear(ent_year);
			stu.setNo(no);
			stu.setName(name);
			stu.setClassNum(class_num);
			stu.setSchool(teacher.getSchool());
			stu.setIsAttend(true);
			
			// 学生番号と氏名をセッションに保存
			session.setAttribute("no",no);
			session.setAttribute("name",name);
			System.out.println("entYear>>"+ent_year);
			if(ent_year == 0) {
				LocalDate todayDate = LocalDate.now();
				int year = todayDate.getYear();
				List<Integer>entYearSet = new ArrayList<>();
				for (int i = year -10; i < year + 1; i++) {
					entYearSet.add(i);
				}
				
				Teacher user = (Teacher) session.getAttribute("user");
				ClassNumDao dao = new ClassNumDao();
				List<String> list = dao.filter(user.getSchool());
				
				request.setAttribute("class_num_set", list);
				request.setAttribute("ent_year_set", entYearSet);
				
				request.setAttribute("ent_error", "入学年度を選択してください");
				request.getRequestDispatcher("StudentCreate.action").forward(request, response);
			}
			StudentDao dao = new StudentDao();
			// 学生一覧を見に行き、重複しているものを見つけるために必要
			Student stuNo = dao.get(no);

			// 学生重複エラー
			if (stuNo != null) {
				request.setAttribute("errors", "学生番号が重複しています。");
				request.getRequestDispatcher("StudentCreate.action").forward(request, response);
			}		
			
			boolean daoFlag = dao.save(stu);
			
			
			if (daoFlag) {
				request.getRequestDispatcher("student_create_done.jsp").forward(request,response);
			}else {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
