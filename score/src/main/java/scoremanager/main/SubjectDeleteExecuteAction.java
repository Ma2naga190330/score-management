package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
	}
}
