package sdcj.nsk.pj001.servlet.UA001;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.UA001001DetailDao;
import sdcj.nsk.pj001.dao.UridenJViewDao;
import sdcj.nsk.pj001.dto.AccountTableDto;

/**
 * Servlet implementation class UA001001_PageChangeAction
 */
@WebServlet("/UA001001_PageChangeAction")
public class UA001001_PageChangeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UA001001_PageChangeAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author 近藤
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// フォワード先
		String url = "./jsp/ua001/ua001001.jsp";

		// ログインしているユーザー情報を取得
		var session = request.getSession(true);
		var loginUser = (AccountTableDto)session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getLoginId().isEmpty()) {
			// 未ログイン
			response.sendRedirect("jsp/cm001/cm001001.jsp");
			return;
		}
		// ログイン済み

		// パラメーターを取得
		String _denno_min = request.getParameter("denno_min");
		String _denno_max = request.getParameter("denno_max");
		String _uridate_min = request.getParameter("uridate_min");
		String _uridate_max = request.getParameter("uridate_max");
		int page_num = Integer.parseInt(request.getParameter("page_num"));

		// 空の場合デフォルトの値をセットする
		String denno_min = _denno_min.isEmpty() ? "000000" : _denno_min;
		String denno_max = _denno_max.isEmpty() ? "999999" : _denno_max;
		String uridate_min = _uridate_min.isEmpty() ? "00010101" : _uridate_min;
		String uridate_max = _uridate_max.isEmpty() ? "99991231" : _uridate_max;

		try {
			// 売上伝票情報Viewのリストを取得
			var ua001001DetailList = UA001001DetailDao.selectByCondition(denno_min, denno_max, uridate_min, uridate_max, page_num);

			// データ数を取得
			int size = UridenJViewDao.countByCondition(denno_min, denno_max, uridate_min, uridate_max);

			// リクエストスコープに投げる
			request.setAttribute("ua001001DetailList", ua001001DetailList);
			request.setAttribute("page_num", page_num);
			request.setAttribute("listsize", size);

			// jspをフォワード
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}