package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassListAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		ClassNumDao dao = new ClassNumDao();
		List<String> list = dao.filter(school);
		request.setAttribute("class", list);
		request.getRequestDispatcher("class_list.jsp").forward(request, response);
	}
}


