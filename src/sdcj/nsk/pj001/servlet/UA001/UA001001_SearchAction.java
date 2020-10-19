package sdcj.nsk.pj001.servlet.UA001;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.UA001001DetailDao;
import sdcj.nsk.pj001.dao.UridenJViewDao;
import sdcj.nsk.pj001.dto.AccountTableDto;
import sdcj.nsk.pj001.dto.UA001001DetailDto;
import sdcj.nsk.pj001.utils.MessageUtil;
import sdcj.nsk.pj001.utils.ValidateUtil;

/**
 * Servlet implementation class UA001001_SearchActions
 */
@WebServlet("/UA001001_SearchAction")
public class UA001001_SearchAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UA001001_SearchAction() {
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

		///////////   1 項目入力チェック   ///////////

		// 検索可否のbool
		boolean isValid = true;

		// パラメーターを取得
		String denno_min = request.getParameter("denno_min");
		String denno_max = request.getParameter("denno_max");
		String uridate_min = request.getParameter("uridate_min");
		String uridate_max = request.getParameter("uridate_max");
		String page_num = request.getParameter("page_num");

		String denno_min_q = (denno_min.isEmpty()) ? "000001" : denno_min;
		String denno_max_q = (denno_max.isEmpty()) ? "999999" : denno_max;
		String uridate_min_q = (uridate_min.isEmpty()) ? "00010101" : uridate_min;
		String uridate_max_q = (uridate_max.isEmpty()) ? "99991231" : uridate_max;

		int page_num_q = (page_num == null || page_num.isEmpty()) ? 1 : Integer.parseInt(page_num);

		// 伝票番号チェック

		// 伝票番号 半角数字チェック
		if (!ValidateUtil.checkHalfWidthDigit(denno_min_q) || !ValidateUtil.checkHalfWidthDigit(denno_max_q)) {

			 // MSG010
			request.setAttribute(
				"HALFWIDTHDIGITERR",
				MessageUtil.errorMessage(
					"MSG010",
					new String[]{ "伝票番号", "半角数字" }
				)
			);

			isValid = false;

		} else {
			// 伝票番号 範囲チェック (denno_?_q は初期化、チェックにより数値変換可能が保証されているのでExceptionは発生しない)
			int dennoMin = Integer.parseInt(denno_min_q);
			int dennoMax = Integer.parseInt(denno_max_q);
			if (!(1 <= dennoMin && dennoMin <= 999999) || !(1 <= dennoMax && dennoMax <= 999999)) {

				// 0 < shohinCodeFrom.compareTo(shohinCodeTo)

				// MSG011
				request.setAttribute(
					"DENNORANGEERR",
					MessageUtil.errorMessage(
						"MSG011",
						new String[]{ "伝票番号", "000001", "999999" }
					)
				);
				isValid = false;
			} else if (dennoMin > dennoMax) {
				// 伝票番号 項目相関チェック

				// MSG007
				request.setAttribute(
					"DENNOCORRELATIONERR",
					MessageUtil.errorMessage(
						"MSG007",
						new String[]{ "伝票番号" }
					)
				);
				isValid = false;
			}
		}

		// 伝票番号 桁数チェック
		if (denno_min_q.length() != 6 || denno_max_q.length() != 6) {

			// MSG010
			request.setAttribute(
				"DENNOLENGTHERR",
				MessageUtil.errorMessage(
					"MSG010",
					new String[]{ "伝票番号", "6桁" }
				)
			);
			isValid = false;
		}

		// 売上日チェック

		// 売上日 半角数字チェック
		if (!ValidateUtil.checkHalfWidthDigit(uridate_min_q)
				|| !ValidateUtil.checkHalfWidthDigit(uridate_max_q)) {

			 // MSG010
			request.setAttribute(
				"HALFWIDTHDIGITERR",
				MessageUtil.errorMessage(
					"MSG010",
					new String[]{ "売上日", "半角数字" }
				)
			);
			isValid = false;
		}

		// 売上日 桁数チェック
		if (uridate_min_q.length() != 8 || uridate_max_q.length() != 8) {

			// MSG010
			request.setAttribute(
				"URIDATELENGTHERR",
				MessageUtil.errorMessage(
					"MSG010",
					new String[]{ "売上日", "8桁" }
				)
			);
			isValid = false;
		}

		//売上日 項目相関チェック
		try {
			if (!ValidateUtil.checkDateValid(uridate_min_q, uridate_max_q)) {
				// MSG007
				request.setAttribute(
					"URIDATECORRELATIONERR",
					MessageUtil.errorMessage(
						"MSG007",
						new String[]{ "日付" }
					)
				);
				isValid = false;
			}
		} catch (ParseException ex) {
			// MSG014
			request.setAttribute(
				"URIDATEINVALIDERR",
				MessageUtil.errorMessage(
					"MSG014",
					new String[]{ "日付" }
				)
			);
			isValid = false;
		}



		try {
			if (isValid) {
				// 3 業務ロジック

				// ①売上伝票情報view 件数取得
				int size = UridenJViewDao.countByCondition(denno_min_q, denno_max_q, uridate_min_q, uridate_max_q);

				// ②売上伝票情報view 一覧取得
				var ua001001DetailList = UA001001DetailDao.selectByCondition(denno_min_q, denno_max_q, uridate_min_q, uridate_max_q, 1);


				if (size == 0 || ua001001DetailList.size() == 0) {
					// MSG001
					request.setAttribute(
						"NORECORDERR",
						MessageUtil.errorMessage(
							"MSG001",
							new String[]{}
						)
					);
				}

				// ページ番号を渡す
				request.setAttribute("page_num", page_num_q);

				// 明細部
				request.setAttribute("ua001001DetailList", ua001001DetailList);
				request.setAttribute("listsize", size);

			} else {
				// ページ番号を渡す
				request.setAttribute("page_num", page_num_q);

				// 明細部
				request.setAttribute("ua001001DetailList", new ArrayList<UA001001DetailDto>());
				request.setAttribute("listsize", 0);
			}

			// ③画面項目の編集を実施する。
			// 検索条件を項目初期値として渡す
			request.setAttribute("denno_min", denno_min);
			request.setAttribute("denno_max", denno_max);
			request.setAttribute("uridate_min", uridate_min);
			request.setAttribute("uridate_max", uridate_max);

			// フォワード
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception ex) {
			// 予期しないエラー
			ex.printStackTrace();
		}
	}
}
