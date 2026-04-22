package scoremanager.main;

import java.io.IOException;

import bean.Student;
import dao.StudentDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String no = request.getParameter("no");
            String name = request.getParameter("name");
            int entYear = Integer.parseInt(request.getParameter("ent_year"));
            String classNum = request.getParameter("class_num");
            boolean isAttend = request.getParameter("is_attend") != null;

            StudentDao studentDao = new StudentDao();
            Student student = studentDao.get(no);

            student.setName(name);
            student.setEntYear(entYear);
            student.setClassNum(classNum);
            student.setIsAttend(isAttend);

            studentDao.save(student);

            request.getRequestDispatcher("student_update_done.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}