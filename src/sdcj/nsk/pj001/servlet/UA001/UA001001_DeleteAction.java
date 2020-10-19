package sdcj.nsk.pj001.servlet.UA001;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.UridenJViewDao;
import sdcj.nsk.pj001.dto.AccountTableDto;
import sdcj.nsk.pj001.utils.MessageUtil;

/**
 * Servlet implementation class UA001001_DeleteAction
 */
@WebServlet("/UA001001_DeleteAction")

public class UA001001_DeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UA001001_DeleteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *
     * @author shutsuno
     * @implNote UA001001_売上伝票一覧の削除ボタンを押下した際にUA001003_削除確認画面へ遷移する
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ログインしているユーザー情報を取得
		var session = request.getSession();
		var loginUser = (AccountTableDto)session.getAttribute("loginUser");

		//ログイン判定実装
		if (loginUser == null || loginUser.getLoginId().isEmpty()) {
			// 未ログイン
			response.sendRedirect("jsp/cm001/cm001001.jsp");
			return;
		}

		String url = "/jsp/ua001/ua001003.jsp";

		//UA001001_売上伝票一覧から伝票Noを取得
		String denNo = request.getParameter("denNo");

		//エラーメッセージの置換用の配列
		String msg[] = {"伝票番号", denNo};

		try {
			// UridenJViewからレコード取得
			var uridenJViewList = UridenJViewDao.selectByDenNo(denNo);

			// リクエストスコープに投げる
			request.setAttribute("uridenJViewList", uridenJViewList);
			request.setAttribute("denNo", denNo);


			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);

			//データが取得できなかった場合、削除確認に遷移し、エラーメッセージを表示する
			}catch (Exception e) {
				request.setAttribute("message",MessageUtil.errorMessage("MSG003", msg));
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}

	}
}