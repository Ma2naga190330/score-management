package scoremanager;

import java.io.IOException;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String password = req.getParameter("password");

            Teacher t = new Teacher();
            t.setId(id);
            t.setPassword(password);

            TeacherDao tDao = new TeacherDao();
            Teacher user = tDao.loginUser(t);

            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setAttribute("school", user.getSchool());
                req.getRequestDispatcher("main/Menu.action").forward(req, res);
                return;
            } else {
                req.setAttribute("error", "ログインに失敗しました。IDまたはパスワードが正しくありません");
                req.getRequestDispatcher("Login.action").forward(req, res);
                return;
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
