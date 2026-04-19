package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		String cd = req.getParameter("cd");
//		String name = req.getParameter("name");
		
		// DBから科目データを取得する
		SubjectDao sDao = new SubjectDao();
		// 戻り値Subject型を受け取る
		Subject subject = sDao.get(cd, teacher.getSchool());

		req.setAttribute("cd", subject.getCd());
		req.setAttribute("name", subject.getName());
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	}
}
