package scoremanager.main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Teacher;
import dao.ClassNumDao;
import dao.TeacherDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;


public class StudentCreateAction extends Action {
	
	@Override
	public void execute (
		HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {
		try {
		// セッションを取得
		HttpSession session = request.getSession();
		Teacher user = (Teacher) session.getAttribute("user");
		
		ClassNumDao dao = new ClassNumDao();
		List<String> list = dao.filter(user.getSchool());
		
		
		
		String entYearStr="";
		String classNum="";
		System.out.println("f2>>"+classNum);
		int entYear = 0;
		boolean isAttend = false;
		LocalDate todayDate = LocalDate.now();
		int year = todayDate.getYear();
		System.out.println(year);
		TeacherDao tDao = new TeacherDao();
		Map<String,String> errors = new HashMap<>();
		
		
		
		
		List<Integer>entYearSet = new ArrayList<>();
		for (int i = year -10; i < year + 1; i++) {
			entYearSet.add(i);
		}
		request.setAttribute("ent_year_set", entYearSet);
		
		
		
		request.setAttribute("class_num_set", list);
		
		request.getRequestDispatcher("student_create.jsp")
			.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
