package sdcj.nsk.pj001.servlet.UA001;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.UA001001DetailDao;
import sdcj.nsk.pj001.dto.AccountTableDto;

/**
 * Servlet implementation class UA001001_ClearAction
 */
@WebServlet("/UA001001_ClearAction")
public class UA001001_ClearAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UA001001_ClearAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// フォワード先
		String url = "./jsp/ua001/ua001001.jsp";

		// ログインしているユーザー情報を取得
		var session = request.getSession();
		var loginUser = (AccountTableDto)session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getLoginId().isEmpty()) {
			// 未ログイン
			response.sendRedirect("jsp/cm001/cm001001.jsp");
			return;
		}

		// ログイン済み
		try {
			// 売上伝票情報Viewのリストを取得
			var ua001001DetailList = UA001001DetailDao.selectByCondition("000001", "999999", "00010101", "99991231", 1);

			// リクエストスコープに投げる
			request.setAttribute("ua001001DetailList", ua001001DetailList);
			request.setAttribute("page_num", 1);

			// jspをフォワード
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
