package sdcj.nsk.pj001.servlet.MM002;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.ShouhinTableDao;
import sdcj.nsk.pj001.utils.CsvUtil;
import sdcj.nsk.pj001.utils.MessageUtil;
import sdcj.nsk.pj001.utils.ValidateUtil;

/**
 * Servlet implementation class CSV_Download
 */
@WebServlet("/MM002001_CSV_DLAction")
public class MM002001_CSV_DLAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MM002001_CSV_DLAction() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 以降使用する文字コード
		final String CHARSET_UTF8 = "UTF-8";
		final String CHARSET_SHIFTJIS = "Shift-JIS";

		// パラメーターを取得
		var shohinCodeFrom = request.getParameter("shohinCodeFrom");
		shohinCodeFrom = !shohinCodeFrom.isEmpty() ? shohinCodeFrom : "0000000000000";
		var shohinCodeTo = request.getParameter("shohinCodeTo");
		shohinCodeTo = !shohinCodeTo.isEmpty() ? shohinCodeTo : "9999999999999";

		// 出力処理の可否
		boolean isValid = true;

		//// 1 項目入力チェック ////

		// ①項目チェック
		// a 最大行チェック
		if (13 < shohinCodeFrom.length() || 13 < shohinCodeTo.length()) {
			// 「○○○○*」は××××*桁以内で入力してください。　=> MSG009
			request.setAttribute(
				"SHOHINCODELENGTHERR",
				MessageUtil.errorMessage(
					"MSG009",
					new String[]{ "商品コード", "13" }
				)
			);
			isValid = false;
		}

		// b 全半角チェック
		if (!ValidateUtil.checkHalfWidth(shohinCodeFrom) || !ValidateUtil.checkHalfWidth(shohinCodeTo)) {
			// 「○○○○*」は××××*で入力してください。 => MSG010
			request.setAttribute(
				"SHOHINCODEHALFWIDTHERR",
				MessageUtil.errorMessage(
					"MSG010",
					new String[]{ "商品コード", "半角" }
				)
			);
			isValid = false;
		}

		//// 2 項目相関チェック ////

		// ①商品コード 大小比較
		if (0 < shohinCodeFrom.compareTo(shohinCodeTo)) {
			// MSG007
			request.setAttribute(
				"SHOHINCODECORRELATIONERR",
				MessageUtil.errorMessage(
					"MSG007",
					new String[]{ "商品コード" }
				)
			);
			isValid = false;
		}

		//// 4 業務ロジック ////

		try {
			if (isValid) {

				// ①商品マスタ一覧取得

				var shohinList = ShouhinTableDao.selectByShohinCodeSE(shohinCodeFrom, shohinCodeTo);

				if (shohinList.size() == 0) {
					// a 件数が0
					// MSG001
					request.setAttribute(
						"SHOHINCODENORECORDSERR",
						MessageUtil.errorMessage(
							"MSG001",
							new String[]{ }
						)
					);
					// 各種データ出力画面へ遷移する
					String url = "/jsp/cm005/cm005001.jsp";
					getServletConfig().getServletContext().getRequestDispatcher(url).forward(request,response);
					return;
				} else {
					// b 件数が0でない
					// ②CSVファイルの作成・編集
					// a ファイル名
					String meisho = CsvUtil.getCsvName("CSV001");
					// 現在日時を取得
					var timestamp = new Timestamp(System.currentTimeMillis());
					var sdf = new SimpleDateFormat("yyyyMMddhhmmss");
					String fileName = URLEncoder.encode(meisho + "_" + sdf.format(timestamp), CHARSET_UTF8) + ".csv";

					// b 出力項目
					String outputString = "";
					for (var shohin : shohinList) {
						outputString += "\"" + shohin.getShohinCode() + "\"" + ","
								+ "\"" + shohin.getShohinName() + "\"" + ","
								+ "\"" + shohin.getTanka() + "\""
								+ System.lineSeparator();
					}

					//// 5 画面項目編集 ////

					// レスポンスをCSVに指定
					response.setContentType("text/csv; charset=" + CHARSET_SHIFTJIS);
					response.setHeader("Content-Disposition","attachment;filename=\"" + fileName + "\"");

					// CSVの中身を作成・DLフォームまで
					try (var pw = response.getWriter()) {
						pw.print(outputString);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				// 各種データ出力画面へ遷移する
				String url = "/jsp/cm005/cm005001.jsp";
				getServletConfig().getServletContext().getRequestDispatcher(url).forward(request,response);
			}
		} catch (Exception ex) {
			// 予期しないエラー
			ex.printStackTrace();
		}
	}
}
