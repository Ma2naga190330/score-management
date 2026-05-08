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
			// エラーフラグ
			boolean flag = false;
			if (ent_year == 0) {
				flag = true;
				request.setAttribute("ent_error","入学年度を設定してください");
			}
			if (class_num.equals("0")) {
				flag = true;
				request.setAttribute("class_num_error", "クラスを設定してください");
			}
			if (flag) {
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
				request.getRequestDispatcher("student_create.jsp").forward(request,response);
				return;
			}
			Student stu = new Student();
			stu.setEntYear(ent_year);
			stu.setNo(no);
			stu.setName(name);
			stu.setClassNum(class_num);
			stu.setSchool(teacher.getSchool());
			stu.setIsAttend(true);
			
			if(ent_year == 0) {
				request.setAttribute("errors", "入学年度を選択してください");
				request.getRequestDispatcher("student_create.jsp").forward(request, response);
			}
			
			if(class_num == null) {
				request.setAttribute("errors", "クラスを選択してください");
				request.getRequestDispatcher("student_create.jsp").forward(request, response);
			}
			
			
			StudentDao dao = new StudentDao();
			boolean daoFlag = dao.save(stu);
			System.out.println("flag>>"+flag);
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
