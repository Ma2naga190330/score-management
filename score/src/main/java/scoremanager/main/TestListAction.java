package scoremanager.main;

import java.io.IOException;
import java.util.List;

import bean.School;
import bean.Subject;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            School school = (School) req.getSession().getAttribute("school");
            if (school == null) {
                req.getRequestDispatcher("Login.action").forward(req, res);
                return;
            }

            List<Integer> entYearList = List.of(2020, 2021, 2022, 2023, 2024, 2025);

            ClassNumDao classDao = new ClassNumDao();
            List<String> classList = classDao.filter(school);

            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjectList = subjectDao.filter(school);

            req.setAttribute("entYearList", entYearList);
            req.setAttribute("classList", classList);
            req.setAttribute("subjectList", subjectList);

            req.getRequestDispatcher("test_list.jsp").forward(req, res);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
