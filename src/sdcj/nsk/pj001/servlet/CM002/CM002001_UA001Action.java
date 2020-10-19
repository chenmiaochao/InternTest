package sdcj.nsk.pj001.servlet.CM002;

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
 * Servlet implementation class CM002001_UA001Action
 */
@WebServlet("/CM002001_UA001Action")
public class CM002001_UA001Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CM002001_UA001Action() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * メニュー画面用メソッド
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author 近藤
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
			// ページ番号
			int page_num = 1;

			// 売上伝票情報Viewのリストを取得
			var ua001001DetailList = UA001001DetailDao.selectByCondition("000001", "999999", "00010101", "99991231", page_num);

			// データ数を取得
			int size = UridenJViewDao.countByCondition("000001", "999999", "00010101", "99991231");

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
