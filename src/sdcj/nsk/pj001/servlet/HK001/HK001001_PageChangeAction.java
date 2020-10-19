package sdcj.nsk.pj001.servlet.HK001;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.ShouhinTableDao;
import sdcj.nsk.pj001.dto.HK001001DetailDto;
import sdcj.nsk.pj001.utils.ValidateUtil;

/**
 * Servlet implementation class HK001001_PageChangeAction
 */
@WebServlet("/HK001001_PageChangeAction")
public class HK001001_PageChangeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HK001001_PageChangeAction() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author nguyen.hungminh
	 * @implNote ページ移るロジック
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = "/jsp/hk001/hk001001.jsp";
		try {
			boolean isValid = true;
			String startCode = request.getParameter("startCode");
			String endCode = request.getParameter("endCode");
			String shohinName = request.getParameter("shohinName");
			String page = request.getParameter("page");

			if (page == null || page.isEmpty()) {
				page = "1";
			}
			//未入力チェック
			if (startCode == null || startCode.isEmpty()||
					endCode == null || endCode.isEmpty() ||
					shohinName == null || shohinName.isEmpty()) {
					request.setAttribute("CHECKEMPTY", "全部入力してください");
					isValid = false;
					return;
			}
			//バリデーション実装
			//TODO:MessageUtils実装完了したら、ここに修正する
			if (!ValidateUtil.checkHalfWidth(startCode) || !ValidateUtil.checkHalfWidth(endCode)) {
				request.setAttribute("HALFWIDTHERR", "半角数字だけ入力してください");
				isValid = false;
			}
			if (!ValidateUtil.checkMaxLength(startCode, 13) || !ValidateUtil.checkMaxLength(endCode, 13)) {
				request.setAttribute("CODELENGTHERR", "商品コードの入力最大長が１３数字");
				isValid = false;
			}
			if (!ValidateUtil.checkMaxLength(shohinName, 20)) {
				request.setAttribute("SHOHINNAMEERR", "商品名の入力最大長が２０文字");
				isValid = false;
			}
			request.setAttribute("STARTCODE", startCode);
			request.setAttribute("ENDCODE", endCode);
			request.setAttribute("SHOHINNAME", shohinName);
			if (isValid) {
				int numPage = Integer.parseInt(page);
				List<HK001001DetailDto> resultList = ShouhinTableDao.selectByCondition(startCode, endCode, shohinName,
						numPage);
				int listSize = ShouhinTableDao.countByCondition(startCode, endCode, shohinName);
				if (listSize == 0 || resultList.isEmpty() || resultList == null) {
					request.setAttribute("NORESULT", "該当するデータが存在しません。");
				}
				//必要な変数を設定する
				request.setAttribute("LISTSIZE", listSize);
				request.setAttribute("RESULTLIST", resultList);
				//UTF-8エンコーディング対応
				request.setAttribute("STARTCODE", URLEncoder.encode(startCode, "UTF-8"));
				request.setAttribute("ENDCODE", URLEncoder.encode(endCode, "UTF-8"));
				request.setAttribute("SHOHINNAME", shohinName);
				request.setAttribute("NUMBERFIRST", numPage * 10 - 9);
				request.setAttribute("NUMBERLAST", (numPage * 10));
				request.setAttribute("PAGE", numPage);
				request.setAttribute("NAMEENCODE", URLEncoder.encode(shohinName, "UTF-8"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			//TODO: エラーページに移るため、url変数新値を代入する
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}



}

