package scoremanager.main;

import java.io.IOException;
<<<<<<< HEAD
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
=======
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

            List<Integer> entYearList = List.of(2020, 2021, 2022, 2023, 2024, 2025);

            ClassNumDao classDao = new ClassNumDao();
            List<String> classList = classDao.filter(school);

            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjectList = subjectDao.filter(school);

            req.setAttribute("entYearList", entYearList);
>>>>>>> refs/remotes/origin/test_manager2
            req.setAttribute("classList", classList);
            req.setAttribute("subjectList", subjectList);

            req.getRequestDispatcher("test_list.jsp").forward(req, res);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
