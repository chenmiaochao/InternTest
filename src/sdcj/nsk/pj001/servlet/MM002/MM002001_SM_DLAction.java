package sdcj.nsk.pj001.servlet.MM002;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sdcj.nsk.pj001.dao.ShouhinTableDao;
import sdcj.nsk.pj001.dto.ShohinTableDto;
import sdcj.nsk.pj001.utils.MessageUtil;
import sdcj.nsk.pj001.utils.ValidateUtil;

/**
 * Servlet implementation class MM002001_SM_DLAction
 */
@WebServlet("/MM002001_SM_DLAction")
public class MM002001_SM_DLAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MM002001_SM_DLAction() {
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
		// パラメーターを取得
		String shohinCodeFrom = request.getParameter("shohinCodeFrom");
		shohinCodeFrom = !shohinCodeFrom.isEmpty() ? shohinCodeFrom : "0000000000000";
		String shohinCodeTo = request.getParameter("shohinCodeTo");
		shohinCodeTo = !shohinCodeTo.isEmpty() ? shohinCodeTo : "zzzzzzzzzzzzz";

		// 出力処理の可否
		boolean isValid = true;

		//// 1 項目入力チェック ////

		// ①項目チェック
		// a 最大行チェック
		if (13 < shohinCodeFrom.length() || 13 < shohinCodeTo.length()) {
			// 「○○○○*」は××××*桁以内で入力してください。　=> MSG009
			request.setAttribute(
					"ERR",
					MessageUtil.errorMessage(
							"MSG009",
							new String[] { "商品コード", "13" }));
			isValid = false;
		}

		// b 全半角チェック
		if (!ValidateUtil.checkHalfWidth(shohinCodeFrom) || !ValidateUtil.checkHalfWidth(shohinCodeTo)) {
			// 「○○○○*」は××××*で入力してください。 => MSG010
			request.setAttribute(
					"ERR",
					MessageUtil.errorMessage(
							"MSG010",
							new String[] { "商品コード", "半角" }));
			isValid = false;
		}

		//// 2 項目相関チェック ////

		// ①商品コード 大小比較
		if (0 < shohinCodeFrom.compareTo(shohinCodeTo)) {
			// MSG007
			request.setAttribute(
					"ERR",
					MessageUtil.errorMessage(
							"MSG007",
							new String[] { "商品コード" }));
			isValid = false;
		}

		//// 4 業務ロジック ////
		List<ShohinTableDto> resultList = null;
		if (isValid) {
			try {

				resultList = ShouhinTableDao.exportByShohinCode(shohinCodeFrom, shohinCodeTo);

			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
				RequestDispatcher rd = request.getRequestDispatcher("/except/exceptioninfo.jsp");
				rd.forward(request, response);
			}
		} else {
			String url = "/jsp/mm002/mm002001.jsp";
			getServletConfig().getServletContext().getRequestDispatcher(url).forward(request, response);
			return;
		}
		if (resultList.size() > 0) {
			// ワークブックとバイトストリーム(レスポンス)を生成する。
			// このふたつはcloseが必ず必要 => try-with-resource文を活用する、
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			String exportName = URLEncoder.encode("商品マスタ一覧.xlsx", "UTF-8");
			response.setHeader("content-disposition", "attachment; filename=" + exportName);

			try (Workbook workbook = new XSSFWorkbook();
					OutputStream outputStream = response.getOutputStream()) {

				//ページ１の初期値

				int pageNumber = 1;
				int rowNumber = 5;
				Sheet sheet = null;
				Row currentRow = null;
				Cell shohinCode = null;
				Cell shohinName = null;
				Cell tanka = null;
				//styleの初期値
				CellStyle style = workbook.createCellStyle();
				// シートを生成する: 実際は複雑なロジックを用いるが、今回はサンプルなのでシンプルなものにとどめておく。
				for (int i = 0; i < resultList.size(); i++) {

					//Style of cell

					if (i % 20 == 0) {
						//ヘッダー用

						sheet = workbook.createSheet("ページ " + pageNumber);
						sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);

						//ファイル名
						rowNumber = 2;
						currentRow = sheet.createRow(rowNumber);
						Cell fileName = currentRow.createCell(3);
						fileName.setCellValue("商品マスタ一覧");
						Font newFont = fileName.getSheet().getWorkbook().createFont();
						newFont.setBold(true);
						newFont.setFontHeightInPoints((short) 24);
						newFont.setItalic(false);
						CellStyle fontStyle = workbook.createCellStyle();
						fontStyle.setFont(newFont);
						fileName.setCellStyle(fontStyle);

						//出力日
						rowNumber = 4;
						currentRow = sheet.createRow(rowNumber);
						Cell exportDate = currentRow.createCell(6);
						exportDate.setCellValue("出力日");
						Cell dateCell = currentRow.createCell(7);
						CreationHelper createHelper = workbook.getCreationHelper();
						CellStyle dateStyle = workbook.createCellStyle();
						dateStyle.setDataFormat(
								createHelper.createDataFormat().getFormat("yyyy/MM/dd"));
						dateStyle.setAlignment(HorizontalAlignment.LEFT);
						dateCell.setCellValue(new Date());
						dateCell.setCellStyle(dateStyle);
						CellRangeAddress cellRangeDate = new CellRangeAddress(rowNumber, rowNumber, 7, 8);
						sheet.addMergedRegion(cellRangeDate);

						//出力範囲
						rowNumber = 5;
						currentRow = sheet.createRow(rowNumber);
						Cell range = currentRow.createCell(6);
						range.setCellValue("範囲");
						Cell rangeCode = currentRow.createCell(7);
						rangeCode.setCellValue(shohinCodeFrom + "～" + shohinCodeTo);
						CellRangeAddress cellRangeCode = new CellRangeAddress(rowNumber, rowNumber, 7, 8);
						sheet.addMergedRegion(cellRangeCode);

						//ヘッダー内容
						rowNumber = 7;
						currentRow = sheet.createRow(rowNumber);
						shohinCode = currentRow.createCell(1);
						shohinName = currentRow.createCell(3);
						tanka = currentRow.createCell(7);
						shohinCode.setCellValue("商品コード");
						shohinName.setCellValue("商品名");
						tanka.setCellValue("単価");
						//border of cell

						shohinCode.setCellStyle(style);
						shohinName.setCellStyle(style);
						tanka.setCellStyle(style);
						//Merge cell
						CellRangeAddress cellRangeId = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
						CellRangeAddress cellRangeName = new CellRangeAddress(rowNumber, rowNumber, 3, 6);
						CellRangeAddress cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 7, 8);
						sheet.addMergedRegion(cellRangeId);
						sheet.addMergedRegion(cellRangeName);
						sheet.addMergedRegion(cellRangeTanka);

						//Page number

						rowNumber = 29;
						currentRow = sheet.createRow(rowNumber);
						Cell page = currentRow.createCell(4);
						page.setCellValue(pageNumber);
						CellStyle pageNumberStyle = workbook.createCellStyle();
						pageNumberStyle.setAlignment(HorizontalAlignment.CENTER);
						page.setCellStyle(pageNumberStyle);
						CellRangeAddress pageIdRange = new CellRangeAddress(rowNumber, rowNumber, 4, 5);
						sheet.addMergedRegion(pageIdRange);

						pageNumber++;
						rowNumber = 8;
					}
					//中身出力
					currentRow = sheet.createRow(rowNumber);
					shohinCode = currentRow.createCell(1);
					shohinName = currentRow.createCell(3);
					tanka = currentRow.createCell(7);

					shohinCode.setCellValue(resultList.get(i).getShohinCode());
					shohinName.setCellValue(resultList.get(i).getShohinName());
					tanka.setCellValue(resultList.get(i).getTanka());

					CellRangeAddress cellRangeId = new CellRangeAddress(rowNumber, rowNumber, 1, 2);
					CellRangeAddress cellRangeName = new CellRangeAddress(rowNumber, rowNumber, 3, 6);
					CellRangeAddress cellRangeTanka = new CellRangeAddress(rowNumber, rowNumber, 7, 8);
					sheet.addMergedRegion(cellRangeId);
					sheet.addMergedRegion(cellRangeName);
					sheet.addMergedRegion(cellRangeTanka);
					//sheet.addMergedRegion(arg0)

					rowNumber++;
					int numMerged = sheet.getNumMergedRegions();

					for (int j = 0; j < numMerged; j++) {
						CellRangeAddress mergedRegions = sheet.getMergedRegion(j);
						if (j == 2 || j == 3 || j == 4) {

							style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
							style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
							RegionUtil.setBorderLeft(BorderStyle.THIN, mergedRegions, sheet);
							RegionUtil.setBorderRight(BorderStyle.THIN, mergedRegions, sheet);
							RegionUtil.setBorderTop(BorderStyle.THIN, mergedRegions, sheet);
							RegionUtil.setBorderBottom(BorderStyle.THIN, mergedRegions, sheet);
						} else if (j == 0 || j == 1 || j == 5) {
							continue;
						} else {
							RegionUtil.setBorderLeft(BorderStyle.THIN, mergedRegions, sheet);
							RegionUtil.setBorderRight(BorderStyle.THIN, mergedRegions, sheet);
							RegionUtil.setBorderTop(BorderStyle.THIN, mergedRegions, sheet);
							RegionUtil.setBorderBottom(BorderStyle.THIN, mergedRegions, sheet);
						}
					}
				}

				// ワークブックをレスポンスに出力する。
				workbook.write(outputStream);
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher rd = request.getRequestDispatcher("/except/exceptioninfo.jsp");
				rd.forward(request, response);
			}
		}else {
			request.setAttribute(
					"ERR",
					"検索条件で商品が存在しません");
			String url = "/jsp/mm002/mm002001.jsp";
			getServletConfig().getServletContext().getRequestDispatcher(url).forward(request, response);
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
