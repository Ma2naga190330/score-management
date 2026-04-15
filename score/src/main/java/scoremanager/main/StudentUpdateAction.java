package scoremanager.main;

import java.io.IOException;
import java.util.List;

import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String studentId = request.getParameter("no");
            
            StudentDao studentDao = new StudentDao();
            Student student = studentDao.get(studentId);
            
            //studentのschoolを取得
            
            // 取得したschoolを使ってClassNumDaoのfilterメソッドを使いクラスリストを取得
            ClassNumDao classDao = new ClassNumDao();
            List<String> classList = classDao.filter(student.getSchool());

            request.setAttribute("student", student);
            request.setAttribute("classList", classList);

            request.getRequestDispatcher("student_update.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}


