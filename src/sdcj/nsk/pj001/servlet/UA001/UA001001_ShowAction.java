package sdcj.nsk.pj001.servlet.UA001;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.UridenJViewDao;
import sdcj.nsk.pj001.dto.AccountTableDto;
import sdcj.nsk.pj001.utils.MessageUtil;

/**
 * Servlet implementation class UA001001_ShowAction
 */
@WebServlet("/UA001001_ShowAction")
public class UA001001_ShowAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UA001001_ShowAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// フォワード先
		String url = "./jsp/ua001/ua001002.jsp";

		// ログインしているユーザー情報を取得
		var session = request.getSession(true);
		var loginUser = (AccountTableDto)session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getLoginId().isEmpty()) {
			// 未ログイン
			response.sendRedirect("jsp/cm001/cm001001.jsp");
			return;
		}

		// 伝票番号
		String denNo = request.getParameter("denNo");

		try {
			// UridenJViewからレコード取得
			var uridenJViewList = UridenJViewDao.selectByDenNo(denNo);

			if (uridenJViewList.size() == 0) {
				// MSG003
				request.setAttribute(
					"NORECORDSERR",
					MessageUtil.errorMessage(
						"MSG003",
						new String[]{ "伝票番号", denNo }
					)
				);
			}

			// リクエストスコープに投げる
			request.setAttribute("uridenJViewList", uridenJViewList);
			request.setAttribute("denNo", denNo);

			// jspをフォワード
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
