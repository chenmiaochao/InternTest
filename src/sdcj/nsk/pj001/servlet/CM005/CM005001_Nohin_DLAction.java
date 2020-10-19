package sdcj.nsk.pj001.servlet.CM005;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.UridenJViewDao;
import sdcj.nsk.pj001.dto.AccountTableDto;
import sdcj.nsk.pj001.utils.CsvUtil;
import sdcj.nsk.pj001.utils.MessageUtil;
import sdcj.nsk.pj001.utils.ValidateUtil;

/**
 * Servlet implementation class CM005001_Nohin_DLAction
 */
@WebServlet("/CM005001_Nohin_DLAction")
public class CM005001_Nohin_DLAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CM005001_Nohin_DLAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * * 各種 データ出力画面用メソッド
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @author shutsuno
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 以降使用する文字コード
		final String CHARSET_UTF8 = "UTF-8";
		final String CHARSET_SHIFTJIS = "Shift-JIS";

		//ログインしているユーザー情報を取得
		var sessionUs = request.getSession();
		var loginUser = (AccountTableDto)sessionUs.getAttribute("loginUser");

		//ログイン判定実装
		if (loginUser == null || loginUser.getLoginId().isEmpty()) {
			// 未ログイン
			response.sendRedirect("jsp/cm001/cm001001.jsp");
			return;
		}

		//JSPから値を取得
		String slipNumberFrom = request.getParameter("slipNumberFrom");
		String slipNumberTo = request.getParameter("slipNumberTo");
		String salesDateFrom = request.getParameter("salesDateFrom");
		String salesDateTo = request.getParameter("salesDateTo");
		String customerFrom = request.getParameter("customerFrom");
		String customerTo = request.getParameter("customerTo");
		String tantouFrom = request.getParameter("tantouFrom");
		String tantouTo = request.getParameter("tantouTo");

		String slipNo_min = (slipNumberFrom.isEmpty()) ? "000001" : slipNumberFrom;
		String slipNo_max = (slipNumberTo.isEmpty()) ? "999999" : slipNumberTo;
		String salesDate_min = (salesDateFrom.isEmpty()) ? "00010101" : salesDateFrom;
		String salesDate_max = (salesDateTo.isEmpty()) ? "99991231" : salesDateTo;
		String customer_min = (customerFrom.isEmpty()) ? "0001" : customerFrom;
		String customer_max = (customerTo.isEmpty()) ? "9999" : customerTo;
		String tantou_min = (tantouFrom.isEmpty()) ? "0001" : tantouFrom;
		String tantou_max = (tantouTo.isEmpty()) ? "9999" : tantouTo;

		String url = "/jsp/cm005/cm005001.jsp";

		// TODO 入力チェック
		boolean flag = false;
		String errorMsg = "";
		String wrongSlipNo[] = {"伝票番号","6桁"};
		String wrongSlipValue[] = {"伝票番号","000001","999999"};
		String wrongSlipValue2[] = {"伝票番号"};
		String halfWidthSlipNo[] = {"伝票番号","半角数字"};
		String wrongSalesDate[] = {"売上日","8桁"};
		String wrongSalesDate2[] = {"売上日"};
		String wrongCsr[] = {"得意先コード","4桁"};
		String wrongCsr2[] = {"得意先コード"};
		String halfWidthCsr[] = {"得意先コード","半角"};
		String wrongTantou[] = {"担当者コード","4桁"};
		String wrongTantou2[] = {"担当者コード"};
		String halfWidthTantou[] = {"担当者コード","半角"};

		// 伝票番号半角数字チェック
		boolean reNo = false;
		boolean rsNoFrom = true;
		try {
			rsNoFrom = ValidateUtil.checkHalfWidthDigit(slipNo_min);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		boolean rsNoTo = true;
		try {
			rsNoTo = ValidateUtil.checkHalfWidthDigit(slipNo_max);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		if(rsNoFrom == false || rsNoTo == false) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG010", halfWidthSlipNo) + "<br>";
			flag = true;
			reNo = true;
		}

		if(!reNo) {
			// 伝票番号 桁数チェック
			if(slipNo_min.length()!= 6 || slipNo_max.length()!= 6) {
				errorMsg = errorMsg + MessageUtil.errorMessage("MSG010", wrongSlipNo) + "<br>";
				flag = true;
			}

			// 伝票番号 範囲チェック
			int slipNoMin = Integer.parseInt(slipNo_min);
			int slipNoMax = Integer.parseInt(slipNo_max);
			if (!(1 <= slipNoMin && slipNoMin <= 999999) || !(1 <= slipNoMax && slipNoMax <= 999999)) {
				errorMsg = errorMsg + MessageUtil.errorMessage("MSG011", wrongSlipValue) + "<br>";
				flag = true;
			}else if (slipNoMin > slipNoMax) {
				// 伝票番号 項目相関チェック
				errorMsg = errorMsg + MessageUtil.errorMessage("MSG007", wrongSlipValue2) + "<br>";
				flag = true;
			}
		}

		// 売上日 桁数チェック
		if(salesDate_min.length()!= 8 || salesDate_max.length()!= 8) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG010", wrongSalesDate) + "<br>";
			flag = true;
		}

		//売上日 項目相関チェック
		try {
			if (!ValidateUtil.checkDateValid(salesDate_min, salesDate_max)) {
				errorMsg = errorMsg + MessageUtil.errorMessage("MSG007", wrongSalesDate2) + "<br>";
				flag = true;
			}
		} catch (ParseException ex) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG014", null) + "<br>";
			flag = true;
		}

		// 日付型チェック
		if(!ValidateUtil.checkDate(salesDate_min)) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG014", null) + "<br>";
			flag = true;
		}

		if(!ValidateUtil.checkDate(salesDate_max)) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG014", null) + "<br>";
			flag = true;
		}

		// 得意先コード半角数字チェック
		boolean reCsr = false;
		boolean rsCsrFrom = true;
		try {
			rsCsrFrom = ValidateUtil.checkHalfWidthDigit(customer_min);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		boolean rsCsrTo = true;
		try {
			rsCsrTo = ValidateUtil.checkHalfWidthDigit(customer_max);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		if(rsCsrFrom == false || rsCsrTo == false) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG010", halfWidthCsr) + "<br>";
			flag = true;
			reCsr = true;
		}

		if(!reCsr) {
			// 得意先コード 桁数チェック
			if(customer_min.length()!= 4 || customer_max.length()!= 4) {
				errorMsg = errorMsg + MessageUtil.errorMessage("MSG010", wrongCsr) + "<br>";
				flag = true;
			}

			// 得意先コード 項目相関チェック
			if (Integer.parseInt(customer_min) > Integer.parseInt(customer_max)) {
				errorMsg = errorMsg + MessageUtil.errorMessage("MSG007", wrongCsr2) + "<br>";
				flag = true;
			}
		}

		// 担当者コード半角数字チェック
		boolean reTantou = false;
		boolean rsTantouFrom = true;
		try {
			rsTantouFrom = ValidateUtil.checkHalfWidthDigit(tantou_min);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		boolean rsTantouTo = true;
		try {
			rsTantouTo = ValidateUtil.checkHalfWidthDigit(tantou_max);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		if(rsTantouFrom == false || rsTantouTo == false) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG010", halfWidthTantou) + "<br>";
			flag = true;
			reTantou = true;
		}

		if(!reTantou) {
			// 担当者コード 桁数チェック
			if(tantou_min.length()!= 4 || tantou_max.length()!= 4) {
				errorMsg = errorMsg + MessageUtil.errorMessage("MSG010", wrongTantou) + "<br>";
				flag = true;
			}

			// 得意先コード 項目相関チェック
			if (Integer.parseInt(tantou_min) > Integer.parseInt(tantou_max)) {
				errorMsg = errorMsg + MessageUtil.errorMessage("MSG007", wrongTantou2) + "<br>";
				flag = true;
			}
		}

		//エラーが発生する場合の処理
		if(flag) {
			request.setAttribute("error", errorMsg);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		}

		// 入力 OK
		try {

			var nohinList = UridenJViewDao.selectCSV1ByCondition(slipNo_min,slipNo_max,salesDate_min,salesDate_max,customer_min,customer_max,tantou_min,tantou_max);

			if (nohinList.size() == 0) {
				// a 件数が0
				// MSG001
				request.setAttribute(
					"error",
					MessageUtil.errorMessage(
						"MSG001",
						new String[]{ }
					)
				);
				// 各種データ出力画面へ遷移する
				getServletConfig().getServletContext().getRequestDispatcher(url).forward(request,response);
				return;

			} else {
				// b 件数が0でない
				// ②CSVファイルの作成・編集
				// a ファイル名
				String meisho = CsvUtil.getCsvName("CSV002");
				// 現在日時を取得
				var timestamp = new Timestamp(System.currentTimeMillis());
				var sdf1 = new SimpleDateFormat("yyyyMMddhhmmss");
				String fileName = URLEncoder.encode(meisho + "_" + sdf1.format(timestamp), CHARSET_UTF8) + ".csv";

				// b 出力項目
				String outputString = "";
				for (var nohin : nohinList) {

					String Memo_001 = nohin.getMemo_001().replace(System.lineSeparator(), " ");

					outputString += "\"" + nohin.getDenNo_001() + "\"" + ","
							+ "\"" + nohin.getUriDate_001() + "\"" + ","
							+ "\"" + nohin.getTokuiName_003() + "\"" + ","
							+ "\"" + nohin.getTantouName_004() + "\"" + ","
							+ "\"" + nohin.getTotalCost_001() + "\"" + ","
							+ "\"" + Memo_001 + "\"" + ","
							+ "\"" + nohin.getMeisaiNo_002() + "\"" + ","
							+ "\"" + nohin.getShohiCode_002() + "\"" + ","
							+ "\"" + nohin.getShohinName_002() + "\"" + ","
							+ "\"" + nohin.getSuryo_002() + "\"" + ","
							+ "\"" + nohin.getTanka_002() + "\"" + ","
							+ "\"" + nohin.getKingaku_002() + "\""
							+ System.lineSeparator();
				}

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
