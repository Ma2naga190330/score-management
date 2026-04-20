package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

	@Override

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// セッションからログインユーザー情報を取得

		HttpSession session = req.getSession();

		Teacher teacher = (Teacher) session.getAttribute("user");

		// パラメータの取得

		String subjectCd = req.getParameter("subject_cd");

		String numStr = req.getParameter("no");

		String[] studentNoSet = req.getParameterValues("student_no_set"); // 学生番号の配列

		TestDao tDao = new TestDao();

		SubjectDao subDao = new SubjectDao();

		StudentDao stuDao = new StudentDao();

		Map<String, String> errors = new HashMap<>();

		try {


			int num = Integer.parseInt(numStr);

			Subject subject = subDao.get(subjectCd, teacher.getSchool());

			if (studentNoSet != null) {

				for (String studentNo : studentNoSet) {


					String pointStr = req.getParameter("point_" + studentNo);


					if (pointStr != null && !pointStr.isEmpty()) {

						int point = Integer.parseInt(pointStr);

						// 0〜100点

						if (point >= 0 && point <= 100) {

							Test test = new Test();

							Student student = stuDao.get(studentNo);

							test.setStudent(student);

							test.setSubject(subject);

							test.setSchool(teacher.getSchool());

							test.setNo(num);

							test.setPoint(point);


							test.setClassNum(student.getClassNum());


							tDao.save(test);

						} else {

							errors.put("point", "点数は0〜100の範囲で入力してください");

						}

					}

				}

			}

			// エラーがあればリクエスト属性にセット（完了画面で表示、または元の画面に戻す用）

			if (!errors.isEmpty()) {

				req.setAttribute("errors", errors);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		// 登録完了画面（test_regist_done.jsp）へフォワード

		req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);

	}

}
 