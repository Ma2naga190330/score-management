package scoremanager.main;

import java.io.IOException;

import bean.Student;
import bean.Teacher;
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
			
			StudentDao dao = new StudentDao();
			boolean flag = dao.save(stu);
			System.out.println("flag>>"+flag);
			if (flag) {
				request.getRequestDispatcher("student_create_done.jsp").forward(request,response);
			}else {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
