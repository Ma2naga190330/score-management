package scoremanager.main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
        	HttpSession session = req.getSession();
        	Teacher teacher = (Teacher) session.getAttribute("user");
            School school = teacher.getSchool();
            if (school == null) {
                req.getRequestDispatcher("Login.action").forward(req, res);
                return;
            }
            
            LocalDate todaysDate = LocalDate.now();
    		int year = todaysDate.getYear();
    		List<Integer> entYearSet = new ArrayList<>();
    		for (int i = year - 10; i < year + 1; i++) {
    			entYearSet.add(i);
    		}
            ClassNumDao classDao = new ClassNumDao();
            List<String> classList = classDao.filter(school);

            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjectList = subjectDao.filter(school);

            req.setAttribute("entYearList", entYearSet);
            req.setAttribute("classList", classList);
            req.setAttribute("subjectList", subjectList);
            req.getRequestDispatcher("test_list.jsp").forward(req, res);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
