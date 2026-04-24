package scoremanager.main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
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
		
		LocalDate todayDate = LocalDate.now();
		int year = todayDate.getYear();
		List<Integer>entYearSet = new ArrayList<>();
		for (int i = year -10; i < year + 1; i++) {
			entYearSet.add(i);
		}
		request.setAttribute("ent_year_set", entYearSet);
		
		
		ClassNumDao dao = new ClassNumDao();
		List<String> list = dao.filter(user.getSchool());
		request.setAttribute("class_num_set", list);
		
		request.getRequestDispatcher("student_create.jsp")
			.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
