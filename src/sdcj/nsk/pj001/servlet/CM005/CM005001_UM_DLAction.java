package sdcj.nsk.pj001.servlet.CM005;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sdcj.nsk.pj001.dao.UridenJViewDao;
import sdcj.nsk.pj001.dto.AccountTableDto;
import sdcj.nsk.pj001.dto.UridenJViewDto;
import sdcj.nsk.pj001.utils.MessageUtil;
import sdcj.nsk.pj001.utils.ValidateUtil;

/**
 * Servlet implementation class CM005001_UM_DLAction
 */
@WebServlet("/CM005001_UM_DLAction")
public class CM005001_UM_DLAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CM005001_UM_DLAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author nguyen.hungminh
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");

		//ログインしているユーザー情報を取得
		var sessionUs = request.getSession();
		var loginUser = (AccountTableDto) sessionUs.getAttribute("loginUser");

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
		String wrongSlipNo[] = { "伝票番号", "6桁" };
		String wrongSlipValue[] = { "伝票番号", "000001", "999999" };
		String wrongSlipValue2[] = { "伝票番号" };
		String halfWidthSlipNo[] = { "伝票番号", "半角数字" };
		String wrongSalesDate[] = { "売上日", "8桁" };
		String wrongSalesDate2[] = { "売上日" };
		String wrongCsr[] = { "得意先コード", "4桁" };
		String wrongCsr2[] = { "得意先コード" };
		String halfWidthCsr[] = { "得意先コード", "半角" };
		String wrongTantou[] = { "担当者コード", "4桁" };
		String wrongTantou2[] = { "担当者コード" };
		String halfWidthTantou[] = { "担当者コード", "半角" };

		// 伝票番号半角数字チェック
		boolean reNo = false;
		boolean rsNoFrom = true;
		try {
			rsNoFrom = ValidateUtil.checkHalfWidthDigit(slipNo_min);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		boolean rsNoTo = true;
		try {
			rsNoTo = ValidateUtil.checkHalfWidthDigit(slipNo_max);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (rsNoFrom == false || rsNoTo == false) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG010", halfWidthSlipNo) + "<br>";
			flag = true;
			reNo = true;
		}

		if (!reNo) {
			// 伝票番号 桁数チェック
			if (slipNo_min.length() != 6 || slipNo_max.length() != 6) {
				errorMsg = errorMsg + MessageUtil.errorMessage("MSG010", wrongSlipNo) + "<br>";
				flag = true;
			}

			// 伝票番号 範囲チェック
			int slipNoMin = Integer.parseInt(slipNo_min);
			int slipNoMax = Integer.parseInt(slipNo_max);
			if (!(1 <= slipNoMin && slipNoMin <= 999999) || !(1 <= slipNoMax && slipNoMax <= 999999)) {
				errorMsg = errorMsg + MessageUtil.errorMessage("MSG011", wrongSlipValue) + "<br>";
				flag = true;
			} else if (slipNoMin > slipNoMax) {
				// 伝票番号 項目相関チェック
				errorMsg = errorMsg + MessageUtil.errorMessage("MSG007", wrongSlipValue2) + "<br>";
				flag = true;
			}
		}

		// 売上日 桁数チェック
		if (salesDate_min.length() != 8 || salesDate_max.length() != 8) {
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
		if (!ValidateUtil.checkDate(salesDate_min)) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG014", null) + "<br>";
			flag = true;
		}

		if (!ValidateUtil.checkDate(salesDate_max)) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG014", null) + "<br>";
			flag = true;
		}

		// 得意先コード半角数字チェック
		boolean reCsr = false;
		boolean rsCsrFrom = true;
		try {
			rsCsrFrom = ValidateUtil.checkHalfWidthDigit(customer_min);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		boolean rsCsrTo = true;
		try {
			rsCsrTo = ValidateUtil.checkHalfWidthDigit(customer_max);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (rsCsrFrom == false || rsCsrTo == false) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG010", halfWidthCsr) + "<br>";
			flag = true;
			reCsr = true;
		}

		if (!reCsr) {
			// 得意先コード 桁数チェック
			if (customer_min.length() != 4 || customer_max.length() != 4) {
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		boolean rsTantouTo = true;
		try {
			rsTantouTo = ValidateUtil.checkHalfWidthDigit(tantou_max);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (rsTantouFrom == false || rsTantouTo == false) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG010", halfWidthTantou) + "<br>";
			flag = true;
			reTantou = true;
		}

		if (!reTantou) {
			// 担当者コード 桁数チェック
			if (tantou_min.length() != 4 || tantou_max.length() != 4) {
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
		if (flag) {
			request.setAttribute("error", errorMsg);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		}

		//出力必要なデータの取得
		List<UridenJViewDto> resultList = null;
		try {
			resultList = UridenJViewDao.selectAllByConditions(slipNo_min, slipNo_max, salesDate_min, salesDate_max,
					customer_min, customer_max,
					tantou_min, tantou_max);
			System.out.println(resultList.size());
		} catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/except/exceptioninfo.jsp");
			rd.forward(request, response);
		}
		// このふたつはcloseが必ず必要 => try-with-resource文を活用する、
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String exportName = URLEncoder.encode("売上明細書.xlsx", "UTF-8");
		response.setHeader("content-disposition", "attachment; filename=" + exportName);
		String tokuiNameTemp = "";
		String denpyoNameTemp = "";
		String uriageDateTemp = "";
		int dateTotalMoney = 0;

		if (resultList.size() > 0) {
			tokuiNameTemp = resultList.get(0).getTokuiName_003();
			denpyoNameTemp = resultList.get(0).getDenNo_001();
			uriageDateTemp = resultList.get(0).getUriDate_001();

			try (Workbook workbook = new XSSFWorkbook();
					OutputStream outputStream = response.getOutputStream()) {
				//ページ１の初期値

				Sheet sheet = null;
				Row currentRow = null;
				int rowNumber = 0;
				int pageNumber = 1;
				CellStyle style = workbook.createCellStyle();
				//If 伝票番号・売上日・得意先の中身が変更あれば、出力切り替えなどのフラグ確認
				//初期値設定
				Cell uriageCellContent = null;
				Cell denpyoCellContent = null;
				Cell shohinCellContent = null;
				Cell suryoContent = null;
				Cell tankaContent = null;
				Cell kingakuContent = null;
				CellRangeAddress cellRangeUriage = null;
				CellRangeAddress cellRangeDenpyo = null;
				CellRangeAddress cellRangeShohin = null;
				CellRangeAddress cellRangeSuryo = null;
				CellRangeAddress cellRangeTanka = null;
				CellRangeAddress cellRangeKingaku = null;
				int tokuiGoukei = 0;
				for (int i = 0; i < resultList.size(); i++) {
					//System.out.println("Record:" + i);

					if (i == 0) {
						sheet = workbook.createSheet("ページ " + pageNumber);
						sheet.getPrintSetup().setLandscape(true);
						sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
						//ヘッダー名
						rowNumber = 1;
						currentRow = sheet.createRow(rowNumber);
						Cell headerName = currentRow.createCell(4);
						headerName.setCellValue("売上明細書");
						Font headerFont = headerName.getSheet().getWorkbook().createFont();
						headerFont.setBold(true);
						headerFont.setFontHeightInPoints((short) 24);
						headerFont.setItalic(false);
						CellStyle fontStyle = workbook.createCellStyle();
						fontStyle.setFont(headerFont);
						fontStyle.setAlignment(HorizontalAlignment.CENTER);
						headerName.setCellStyle(fontStyle);
						CellRangeAddress cellRangeHeader = new CellRangeAddress(rowNumber, rowNumber + 1, 4, 10);
						sheet.addMergedRegion(cellRangeHeader);

						//得意先と
						rowNumber = 4;
						currentRow = sheet.createRow(rowNumber);
						Cell tokuiName = currentRow.createCell(1);
						CellRangeAddress cellRangeTokui = new CellRangeAddress(rowNumber, rowNumber, 1, 5);
						sheet.addMergedRegion(cellRangeTokui);

						//得意先設定（得意先変えたらそのページに切り替える）
						tokuiName.setCellValue("得意先：　" + tokuiNameTemp + "　　　　　　　　　　　　");
						Font tokuiFont = tokuiName.getSheet().getWorkbook().createFont();
						tokuiFont.setUnderline(HSSFFont.U_SINGLE);
						CellStyle tokuiStyle = workbook.createCellStyle();
						tokuiStyle.setFont(tokuiFont);
						tokuiName.setCellStyle(tokuiStyle);
						//印刷日
						Cell printDate = currentRow.createCell(11);
						String today = new Timestamp(System.currentTimeMillis()).toString();
						//System.out.println("Today:" + today);
						printDate.setCellValue("印刷日：" + today.substring(0, 4) + "年" + today.substring(5, 7) + "月"
								+ today.substring(8, 10) + "日");

						//表のヘッダー
						rowNumber = 7;
						currentRow = sheet.createRow(rowNumber);
						uriageCellContent = currentRow.createCell(1);
						denpyoCellContent = currentRow.createCell(3);
						shohinCellContent = currentRow.createCell(5);
						suryoContent = currentRow.createCell(8);
						tankaContent = currentRow.createCell(10);
						kingakuContent = currentRow.createCell(12);
						uriageCellContent.setCellValue("売上日");
						denpyoCellContent.setCellValue("伝票番号");
						shohinCellContent.setCellValue("商品名");
						suryoContent.setCellValue("数量");
						tankaContent.setCellValue("単価");
						kingakuContent.setCellValue("金額");
						//border of cell

						uriageCellContent.setCellStyle(style);
						denpyoCellContent.setCellStyle(style);
						shohinCellContent.setCellStyle(style);
						suryoContent.setCellStyle(style);
						tankaContent.setCellStyle(style);
						kingakuContent.setCellStyle(style);
						//表のヘッダーのセル結合
						cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
						cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
						cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
						cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
						cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
						cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
						sheet.addMergedRegion(cellRangeUriage);
						sheet.addMergedRegion(cellRangeDenpyo);
						sheet.addMergedRegion(cellRangeShohin);
						sheet.addMergedRegion(cellRangeSuryo);
						sheet.addMergedRegion(cellRangeTanka);
						sheet.addMergedRegion(cellRangeKingaku);
						pageNumber++;
						rowNumber = 8;

					}

					//出力内容
					//売上日内容

					currentRow = sheet.createRow(rowNumber);
					uriageCellContent = currentRow.createCell(1);
					denpyoCellContent = currentRow.createCell(3);
					shohinCellContent = currentRow.createCell(5);
					suryoContent = currentRow.createCell(8);
					tankaContent = currentRow.createCell(10);
					kingakuContent = currentRow.createCell(12);
					cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
					cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
					cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
					cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
					cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
					cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
					sheet.addMergedRegion(cellRangeUriage);
					sheet.addMergedRegion(cellRangeDenpyo);
					sheet.addMergedRegion(cellRangeShohin);
					sheet.addMergedRegion(cellRangeSuryo);
					sheet.addMergedRegion(cellRangeTanka);
					sheet.addMergedRegion(cellRangeKingaku);
					//
					if (i == 0) {
						uriageCellContent.setCellValue(resultList.get(i).getUriDate_001());
						denpyoCellContent.setCellValue(resultList.get(i).getDenNo_001());
						cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
						cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
					}
					//伝票番号のみ変更

					if (!resultList.get(i).getDenNo_001().equals(denpyoNameTemp) && i > 0
							&& tokuiNameTemp.equals(resultList.get(i).getTokuiName_003())) {

						uriageCellContent.setCellValue("");
						denpyoCellContent.setCellValue("");
						shohinCellContent.setCellValue("【伝票合計】");
						suryoContent.setCellValue("");
						tankaContent.setCellValue("");
						kingakuContent.setCellValue(resultList.get(i - 1).getTotalCost_001());
						dateTotalMoney += Integer.parseInt(resultList.get(i - 1).getTotalCost_001());
						tokuiGoukei += Integer.parseInt(resultList.get(i - 1).getTotalCost_001());
						denpyoNameTemp = resultList.get(i).getDenNo_001();
						//denpyoHenkouFlag = true;
						rowNumber++;
						//最後の行の追加
						if (resultList.get(i).getTokuiName_003().equals(tokuiNameTemp)) {
							currentRow = sheet.createRow(rowNumber);
							uriageCellContent = currentRow.createCell(1);
							denpyoCellContent = currentRow.createCell(3);
							shohinCellContent = currentRow.createCell(5);
							suryoContent = currentRow.createCell(8);
							tankaContent = currentRow.createCell(10);
							kingakuContent = currentRow.createCell(12);
							denpyoCellContent.setCellValue(resultList.get(i).getDenNo_001());
							cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
							cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
							cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
							cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
							cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
							cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
							sheet.addMergedRegion(cellRangeUriage);
							sheet.addMergedRegion(cellRangeDenpyo);
							sheet.addMergedRegion(cellRangeShohin);
							sheet.addMergedRegion(cellRangeSuryo);
							sheet.addMergedRegion(cellRangeTanka);
							sheet.addMergedRegion(cellRangeKingaku);

						}
					} else if (!resultList.get(i).getDenNo_001().equals(denpyoNameTemp) && i > 0
							&& !tokuiNameTemp.equals(resultList.get(i).getTokuiName_003())) {
						currentRow = sheet.createRow(rowNumber);
						uriageCellContent = currentRow.createCell(1);
						denpyoCellContent = currentRow.createCell(3);
						shohinCellContent = currentRow.createCell(5);
						suryoContent = currentRow.createCell(8);
						tankaContent = currentRow.createCell(10);
						kingakuContent = currentRow.createCell(12);
						uriageCellContent.setCellValue("");
						denpyoCellContent.setCellValue("");
						shohinCellContent.setCellValue("【伝票合計】");
						suryoContent.setCellValue("");
						tankaContent.setCellValue("");
						kingakuContent.setCellValue(resultList.get(i - 1).getTotalCost_001());
						dateTotalMoney += Integer.parseInt(resultList.get(i - 1).getTotalCost_001());
						tokuiGoukei += Integer.parseInt(resultList.get(i - 1).getTotalCost_001());
						denpyoNameTemp = resultList.get(i).getDenNo_001();

						rowNumber++;
					}
					//売上日のみ変更

					if (!resultList.get(i).getUriDate_001().equals(uriageDateTemp) && i > 0 &&
							tokuiNameTemp.equals(resultList.get(i).getTokuiName_003())) {
						//uriageDateTemp = resultList.get(i).getUriDate_001();
						uriageCellContent.setCellValue("");
						denpyoCellContent.setCellValue("");
						shohinCellContent.setCellValue("【日付合計】");
						suryoContent.setCellValue("");
						tankaContent.setCellValue("");
						kingakuContent.setCellValue(dateTotalMoney + "");
						dateTotalMoney = 0;
						/*cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
						cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
						cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
						cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
						cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
						cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
						sheet.addMergedRegion(cellRangeUriage);
						sheet.addMergedRegion(cellRangeDenpyo);
						sheet.addMergedRegion(cellRangeShohin);
						sheet.addMergedRegion(cellRangeSuryo);
						sheet.addMergedRegion(cellRangeTanka);
						sheet.addMergedRegion(cellRangeKingaku);*/
						//uriageDateHenkouFlag = true;
						uriageDateTemp = resultList.get(i).getUriDate_001();
						rowNumber++;
						//ページ切り替える前に最後の行の追加
						if (tokuiNameTemp.equals(resultList.get(i).getTokuiName_003())) {
							currentRow = sheet.createRow(rowNumber);
							uriageCellContent = currentRow.createCell(1);
							denpyoCellContent = currentRow.createCell(3);
							shohinCellContent = currentRow.createCell(5);
							suryoContent = currentRow.createCell(8);
							tankaContent = currentRow.createCell(10);
							kingakuContent = currentRow.createCell(12);
							uriageCellContent.setCellValue(resultList.get(i).getUriDate_001());
							denpyoCellContent.setCellValue(resultList.get(i).getDenNo_001());
							cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
							cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
							cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
							cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
							cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
							cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
							sheet.addMergedRegion(cellRangeUriage);
							sheet.addMergedRegion(cellRangeDenpyo);
							sheet.addMergedRegion(cellRangeShohin);
							sheet.addMergedRegion(cellRangeSuryo);
							sheet.addMergedRegion(cellRangeTanka);
							sheet.addMergedRegion(cellRangeKingaku);

						}

					}
					//最後の行に切り替える前に追加
					else if (resultList.get(i).getUriDate_001().equals(uriageDateTemp) && i > 0 &&
							!tokuiNameTemp.equals(resultList.get(i).getTokuiName_003())) {
						//uriageDateTemp = resultList.get(i).getUriDate_001();
						currentRow = sheet.createRow(rowNumber);
						uriageCellContent = currentRow.createCell(1);
						denpyoCellContent = currentRow.createCell(3);
						shohinCellContent = currentRow.createCell(5);
						suryoContent = currentRow.createCell(8);
						tankaContent = currentRow.createCell(10);
						kingakuContent = currentRow.createCell(12);
						uriageCellContent.setCellValue("");
						denpyoCellContent.setCellValue("");
						shohinCellContent.setCellValue("【日付合計】");
						suryoContent.setCellValue("");
						tankaContent.setCellValue("");
						kingakuContent.setCellValue(dateTotalMoney + "");
						dateTotalMoney = 0;

						cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
						cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
						cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
						cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
						cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
						cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
						sheet.addMergedRegion(cellRangeUriage);
						sheet.addMergedRegion(cellRangeDenpyo);
						sheet.addMergedRegion(cellRangeShohin);
						sheet.addMergedRegion(cellRangeSuryo);
						sheet.addMergedRegion(cellRangeTanka);
						sheet.addMergedRegion(cellRangeKingaku);
						/*cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
						cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
						cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
						cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
						cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
						cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
						sheet.addMergedRegion(cellRangeUriage);
						sheet.addMergedRegion(cellRangeDenpyo);
						sheet.addMergedRegion(cellRangeShohin);
						sheet.addMergedRegion(cellRangeSuryo);
						sheet.addMergedRegion(cellRangeTanka);
						sheet.addMergedRegion(cellRangeKingaku);*/
						//uriageDateHenkouFlag = true;
						//tokuiNameTemp = resultList.get(i).getTokuiName_003();
						rowNumber++;
					}
					//切り替えたあとで次に切り替えロジック入る
					else if (!resultList.get(i).getUriDate_001().equals(uriageDateTemp) && i > 0 &&
							!tokuiNameTemp.equals(resultList.get(i).getTokuiName_003())) {
						//uriageDateTemp = resultList.get(i).getUriDate_001();
						currentRow = sheet.createRow(rowNumber);
						uriageCellContent = currentRow.createCell(1);
						denpyoCellContent = currentRow.createCell(3);
						shohinCellContent = currentRow.createCell(5);
						suryoContent = currentRow.createCell(8);
						tankaContent = currentRow.createCell(10);
						kingakuContent = currentRow.createCell(12);
						uriageCellContent.setCellValue("");
						denpyoCellContent.setCellValue("");
						shohinCellContent.setCellValue("【日付合計】");
						suryoContent.setCellValue("");
						tankaContent.setCellValue("");
						kingakuContent.setCellValue(dateTotalMoney + "");
						dateTotalMoney = 0;

						cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
						cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
						cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
						cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
						cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
						cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
						sheet.addMergedRegion(cellRangeUriage);
						sheet.addMergedRegion(cellRangeDenpyo);
						sheet.addMergedRegion(cellRangeShohin);
						sheet.addMergedRegion(cellRangeSuryo);
						sheet.addMergedRegion(cellRangeTanka);
						sheet.addMergedRegion(cellRangeKingaku);
						/*cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
						cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
						cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
						cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
						cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
						cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
						sheet.addMergedRegion(cellRangeUriage);
						sheet.addMergedRegion(cellRangeDenpyo);
						sheet.addMergedRegion(cellRangeShohin);
						sheet.addMergedRegion(cellRangeSuryo);
						sheet.addMergedRegion(cellRangeTanka);
						sheet.addMergedRegion(cellRangeKingaku);*/
						//uriageDateHenkouFlag = true;
						//tokuiNameTemp = resultList.get(i).getTokuiName_003();
						rowNumber++;
					}

					//その他
					if (tokuiNameTemp.equals(resultList.get(i).getTokuiName_003())
							&& uriageDateTemp.equals(resultList.get(i).getUriDate_001())
							&& denpyoNameTemp.equals(resultList.get(i).getDenNo_001())) {
						shohinCellContent.setCellValue(resultList.get(i).getShohinName_002());
						suryoContent.setCellValue(resultList.get(i).getSuryo_002());
						tankaContent.setCellValue(resultList.get(i).getTanka_002());
						kingakuContent.setCellValue(resultList.get(i).getKingaku_002());
						/*cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
						cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
						cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
						cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);

						sheet.addMergedRegion(cellRangeShohin);
						sheet.addMergedRegion(cellRangeSuryo);
						sheet.addMergedRegion(cellRangeTanka);
						sheet.addMergedRegion(cellRangeKingaku);*/
						rowNumber++;
					}
					//System.out.println("i%15"+i);
					//System.out.println(tokuiNameTemp);
					//System.out.println(resultList.get(i).getTokuiName_003());
					if ((i % 15 == 0 || !tokuiNameTemp.equals(resultList.get(i).getTokuiName_003())) && i > 0) {
						//ラストページの最後レコード埋める
						//System.out.println(pageNumber);
						if (!tokuiNameTemp.equals(resultList.get(i).getTokuiName_003())) {
							//空き行もボーダーライン埋める

							currentRow = sheet.createRow(rowNumber);
							uriageCellContent = currentRow.createCell(1);
							denpyoCellContent = currentRow.createCell(3);
							shohinCellContent = currentRow.createCell(5);
							suryoContent = currentRow.createCell(8);
							tankaContent = currentRow.createCell(10);
							kingakuContent = currentRow.createCell(12);
							uriageCellContent.setCellValue("");
							denpyoCellContent.setCellValue("");
							shohinCellContent.setCellValue("");
							suryoContent.setCellValue("");
							tankaContent.setCellValue("");
							kingakuContent.setCellValue("");
							cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
							cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
							cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
							cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
							cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
							cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
							sheet.addMergedRegion(cellRangeUriage);
							sheet.addMergedRegion(cellRangeDenpyo);
							sheet.addMergedRegion(cellRangeShohin);
							sheet.addMergedRegion(cellRangeSuryo);
							sheet.addMergedRegion(cellRangeTanka);
							sheet.addMergedRegion(cellRangeKingaku);
							//得意先合計の処理
							rowNumber++;
							currentRow = sheet.createRow(rowNumber);
							uriageCellContent = currentRow.createCell(1);
							denpyoCellContent = currentRow.createCell(3);
							shohinCellContent = currentRow.createCell(5);
							suryoContent = currentRow.createCell(8);
							tankaContent = currentRow.createCell(10);
							kingakuContent = currentRow.createCell(12);
							uriageCellContent.setCellValue("");
							denpyoCellContent.setCellValue("");
							shohinCellContent.setCellValue("【得意先合計】");
							suryoContent.setCellValue("");
							tankaContent.setCellValue("");
							kingakuContent.setCellValue(tokuiGoukei + "");
							tokuiGoukei = 0;
							cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
							cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
							cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
							cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
							cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
							cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
							sheet.addMergedRegion(cellRangeUriage);
							sheet.addMergedRegion(cellRangeDenpyo);
							sheet.addMergedRegion(cellRangeShohin);
							sheet.addMergedRegion(cellRangeSuryo);
							sheet.addMergedRegion(cellRangeTanka);
							sheet.addMergedRegion(cellRangeKingaku);
							tokuiNameTemp = resultList.get(i).getTokuiName_003();
						}
						//ページ切り替える前にボーダーライン追加
						int numMerged = sheet.getNumMergedRegions();
						//System.out.println(numMerged);
						for (int j = 0; j < numMerged; j++) {
							CellRangeAddress mergedRegions = sheet.getMergedRegion(j);
							if (j == 2 || j == 3 || j == 4 || j == 5 || j == 6 || j == 7) {

								style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
								style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
								RegionUtil.setBorderLeft(BorderStyle.THIN, mergedRegions, sheet);
								RegionUtil.setBorderRight(BorderStyle.THIN, mergedRegions, sheet);
								RegionUtil.setBorderTop(BorderStyle.THIN, mergedRegions, sheet);
								RegionUtil.setBorderBottom(BorderStyle.THIN, mergedRegions, sheet);
							} else if (j == 0 || j == 1) {
								continue;
							} else if (j < numMerged - 6) {
								RegionUtil.setBorderLeft(BorderStyle.THIN, mergedRegions, sheet);
								RegionUtil.setBorderRight(BorderStyle.THIN, mergedRegions, sheet);
							} else if (j >= numMerged - 6) {
								RegionUtil.setBorderLeft(BorderStyle.THIN, mergedRegions, sheet);
								RegionUtil.setBorderRight(BorderStyle.THIN, mergedRegions, sheet);
								RegionUtil.setBorderBottom(BorderStyle.THIN, mergedRegions, sheet);
							}
						}
						tokuiNameTemp = resultList.get(i).getTokuiName_003();
						//シート設定
						sheet = workbook.createSheet("ページ " + pageNumber);
						sheet.getPrintSetup().setLandscape(true);
						sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
						//ヘッダー名
						rowNumber = 1;
						currentRow = sheet.createRow(rowNumber);
						Cell headerName = currentRow.createCell(4);
						headerName.setCellValue("売上明細書");
						Font headerFont = headerName.getSheet().getWorkbook().createFont();
						headerFont.setBold(true);
						headerFont.setFontHeightInPoints((short) 24);
						headerFont.setItalic(false);
						CellStyle fontStyle = workbook.createCellStyle();
						fontStyle.setFont(headerFont);
						fontStyle.setAlignment(HorizontalAlignment.CENTER);
						headerName.setCellStyle(fontStyle);
						CellRangeAddress cellRangeHeader = new CellRangeAddress(rowNumber, rowNumber + 1, 4, 10);
						sheet.addMergedRegion(cellRangeHeader);

						//得意先と
						rowNumber = 4;
						currentRow = sheet.createRow(rowNumber);
						Cell tokuiName = currentRow.createCell(1);
						CellRangeAddress cellRangeTokui = new CellRangeAddress(rowNumber, rowNumber, 1, 5);
						sheet.addMergedRegion(cellRangeTokui);

						//得意先設定（得意先変えたらそのページに切り替える）
						tokuiName.setCellValue("得意先：　" + tokuiNameTemp + "　　　　　　　　　　　　");
						Font tokuiFont = tokuiName.getSheet().getWorkbook().createFont();
						tokuiFont.setUnderline(HSSFFont.U_SINGLE);
						CellStyle tokuiStyle = workbook.createCellStyle();
						tokuiStyle.setFont(tokuiFont);
						tokuiName.setCellStyle(tokuiStyle);
						//印刷日
						Cell printDate = currentRow.createCell(11);
						String today = new Timestamp(System.currentTimeMillis()).toString();
						//System.out.println("Today:" + today);
						printDate.setCellValue("印刷日：" + today.substring(0, 4) + "年" + today.substring(5, 7) + "月"
								+ today.substring(8, 10) + "日");

						//表のヘッダー
						rowNumber = 7;
						currentRow = sheet.createRow(rowNumber);
						uriageCellContent = currentRow.createCell(1);
						denpyoCellContent = currentRow.createCell(3);
						shohinCellContent = currentRow.createCell(5);
						suryoContent = currentRow.createCell(8);
						tankaContent = currentRow.createCell(10);
						kingakuContent = currentRow.createCell(12);
						uriageCellContent.setCellValue("売上日");
						denpyoCellContent.setCellValue("伝票番号");
						shohinCellContent.setCellValue("商品名");
						suryoContent.setCellValue("数量");
						tankaContent.setCellValue("単価");
						kingakuContent.setCellValue("金額");
						//表のヘッダーのセル結合
						cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
						cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
						cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
						cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
						cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
						cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
						sheet.addMergedRegion(cellRangeUriage);
						sheet.addMergedRegion(cellRangeDenpyo);
						sheet.addMergedRegion(cellRangeShohin);
						sheet.addMergedRegion(cellRangeSuryo);
						sheet.addMergedRegion(cellRangeTanka);
						sheet.addMergedRegion(cellRangeKingaku);
						//border of cell

						uriageCellContent.setCellStyle(style);
						denpyoCellContent.setCellStyle(style);
						shohinCellContent.setCellStyle(style);
						suryoContent.setCellStyle(style);
						tankaContent.setCellStyle(style);
						kingakuContent.setCellStyle(style);

						pageNumber++;
						rowNumber = 8;
						currentRow = sheet.createRow(rowNumber);
						uriageCellContent = currentRow.createCell(1);
						denpyoCellContent = currentRow.createCell(3);
						shohinCellContent = currentRow.createCell(5);
						suryoContent = currentRow.createCell(8);
						tankaContent = currentRow.createCell(10);
						kingakuContent = currentRow.createCell(12);

						//
						uriageCellContent.setCellValue(resultList.get(i).getUriDate_001());
						denpyoCellContent.setCellValue(resultList.get(i).getDenNo_001());
						shohinCellContent.setCellValue(resultList.get(i).getShohinName_002());
						suryoContent.setCellValue(resultList.get(i).getSuryo_002());
						tankaContent.setCellValue(resultList.get(i).getTanka_002());
						kingakuContent.setCellValue(resultList.get(i).getKingaku_002());

						cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
						cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
						cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
						cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
						cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
						cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
						sheet.addMergedRegion(cellRangeUriage);
						sheet.addMergedRegion(cellRangeDenpyo);
						sheet.addMergedRegion(cellRangeShohin);
						sheet.addMergedRegion(cellRangeSuryo);
						sheet.addMergedRegion(cellRangeTanka);
						sheet.addMergedRegion(cellRangeKingaku);
						rowNumber++;
						tokuiNameTemp = resultList.get(i).getTokuiName_003();
						uriageDateTemp = resultList.get(i).getUriDate_001();
						denpyoNameTemp = resultList.get(i).getDenNo_001();

					}
					//最後ページの
					if (i == (resultList.size() - 1)) {
						//rowNumber += 1;

						currentRow = sheet.createRow(rowNumber);
						uriageCellContent = currentRow.createCell(1);
						denpyoCellContent = currentRow.createCell(3);
						shohinCellContent = currentRow.createCell(5);
						suryoContent = currentRow.createCell(8);
						tankaContent = currentRow.createCell(10);
						kingakuContent = currentRow.createCell(12);
						uriageCellContent.setCellValue("");
						denpyoCellContent.setCellValue("");
						shohinCellContent.setCellValue("【伝票合計】");
						suryoContent.setCellValue("");
						tankaContent.setCellValue("");
						kingakuContent.setCellValue(resultList.get(i).getTotalCost_001());
						dateTotalMoney = Integer.parseInt(resultList.get(i).getTotalCost_001());
						tokuiGoukei += dateTotalMoney;
						cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
						cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
						cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
						cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
						cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
						cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
						sheet.addMergedRegion(cellRangeUriage);
						sheet.addMergedRegion(cellRangeDenpyo);
						sheet.addMergedRegion(cellRangeShohin);
						sheet.addMergedRegion(cellRangeSuryo);
						sheet.addMergedRegion(cellRangeTanka);
						sheet.addMergedRegion(cellRangeKingaku);

						//
						rowNumber += 1;
						//System.out.println("Lastrow" + rowNumber);
						currentRow = sheet.createRow(rowNumber);
						uriageCellContent = currentRow.createCell(1);
						denpyoCellContent = currentRow.createCell(3);
						shohinCellContent = currentRow.createCell(5);
						suryoContent = currentRow.createCell(8);
						tankaContent = currentRow.createCell(10);
						kingakuContent = currentRow.createCell(12);
						uriageCellContent.setCellValue("");
						denpyoCellContent.setCellValue("");
						shohinCellContent.setCellValue("【日付合計】");
						suryoContent.setCellValue("");
						tankaContent.setCellValue("");
						kingakuContent.setCellValue(dateTotalMoney + "");
						cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
						cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
						cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
						cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
						cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
						cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
						sheet.addMergedRegion(cellRangeUriage);
						sheet.addMergedRegion(cellRangeDenpyo);
						sheet.addMergedRegion(cellRangeShohin);
						sheet.addMergedRegion(cellRangeSuryo);
						sheet.addMergedRegion(cellRangeTanka);
						sheet.addMergedRegion(cellRangeKingaku);

						//
						//空き行もボーダーライン埋める
						rowNumber += 1;
						//System.out.println("Lastrow" + rowNumber);
						currentRow = sheet.createRow(rowNumber);
						uriageCellContent = currentRow.createCell(1);
						denpyoCellContent = currentRow.createCell(3);
						shohinCellContent = currentRow.createCell(5);
						suryoContent = currentRow.createCell(8);
						tankaContent = currentRow.createCell(10);
						kingakuContent = currentRow.createCell(12);
						uriageCellContent.setCellValue("");
						denpyoCellContent.setCellValue("");
						shohinCellContent.setCellValue("");
						suryoContent.setCellValue("");
						tankaContent.setCellValue("");
						kingakuContent.setCellValue("");
						cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
						cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
						cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
						cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
						cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
						cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
						sheet.addMergedRegion(cellRangeUriage);
						sheet.addMergedRegion(cellRangeDenpyo);
						sheet.addMergedRegion(cellRangeShohin);
						sheet.addMergedRegion(cellRangeSuryo);
						sheet.addMergedRegion(cellRangeTanka);
						sheet.addMergedRegion(cellRangeKingaku);
						//得意先合計の処理
						rowNumber += 1;
						//System.out.println("Lastrow" + rowNumber);
						currentRow = sheet.createRow(rowNumber);
						uriageCellContent = currentRow.createCell(1);
						denpyoCellContent = currentRow.createCell(3);
						shohinCellContent = currentRow.createCell(5);
						suryoContent = currentRow.createCell(8);
						tankaContent = currentRow.createCell(10);
						kingakuContent = currentRow.createCell(12);
						uriageCellContent.setCellValue("");
						denpyoCellContent.setCellValue("");
						shohinCellContent.setCellValue("【得意先合計】");
						suryoContent.setCellValue("");
						tankaContent.setCellValue("");
						kingakuContent.setCellValue(tokuiGoukei + "");
						cellRangeUriage = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
						cellRangeDenpyo = new CellRangeAddress(rowNumber, rowNumber, 3, 4);
						cellRangeShohin = new CellRangeAddress(rowNumber, rowNumber, 5, 7);
						cellRangeSuryo = new CellRangeAddress(rowNumber, rowNumber, 8, 9);
						cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 10, 11);
						cellRangeKingaku = new CellRangeAddress(rowNumber, rowNumber, 12, 13);
						sheet.addMergedRegion(cellRangeUriage);
						sheet.addMergedRegion(cellRangeDenpyo);
						sheet.addMergedRegion(cellRangeShohin);
						sheet.addMergedRegion(cellRangeSuryo);
						sheet.addMergedRegion(cellRangeTanka);
						sheet.addMergedRegion(cellRangeKingaku);
					}

				}

				//ラストページボーダーライン追加
				int numMerged = sheet.getNumMergedRegions();
				//System.out.println(numMerged);
				for (int j = 0; j < numMerged; j++) {
					CellRangeAddress mergedRegions = sheet.getMergedRegion(j);
					if (j == 2 || j == 3 || j == 4 || j == 5 || j == 6 || j == 7) {

						style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
						style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						RegionUtil.setBorderLeft(BorderStyle.THIN, mergedRegions, sheet);
						RegionUtil.setBorderRight(BorderStyle.THIN, mergedRegions, sheet);
						RegionUtil.setBorderTop(BorderStyle.THIN, mergedRegions, sheet);
						RegionUtil.setBorderBottom(BorderStyle.THIN, mergedRegions, sheet);
					} else if (j == 0 || j == 1) {
						continue;
					} else if (j < numMerged - 6) {
						RegionUtil.setBorderLeft(BorderStyle.THIN, mergedRegions, sheet);
						RegionUtil.setBorderRight(BorderStyle.THIN, mergedRegions, sheet);
					} else if (j >= numMerged - 6) {
						RegionUtil.setBorderLeft(BorderStyle.THIN, mergedRegions, sheet);
						RegionUtil.setBorderRight(BorderStyle.THIN, mergedRegions, sheet);
						RegionUtil.setBorderBottom(BorderStyle.THIN, mergedRegions, sheet);
					}
				}
				// ワークブックをレスポンスに出力する。
				workbook.write(outputStream);
			} catch (Exception ex) {
				ex.printStackTrace();
				RequestDispatcher rd = request.getRequestDispatcher("/except/exceptioninfo.jsp");
				rd.forward(request, response);
			}
		} else {
			//TODO データない時何かの処理
			request.setAttribute("error", "データ存在しません");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
