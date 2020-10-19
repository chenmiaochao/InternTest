package sdcj.nsk.pj001.servlet.HK003;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.TantouTableDao;
import sdcj.nsk.pj001.dto.TantouTableDto;
import sdcj.nsk.pj001.utils.ValidateUtil;

/**
 * Servlet implementation class HK003001_PageChangeAction
 * @author nguyen.hungminh
 */
@WebServlet("/HK003001_PageChangeAction")
public class HK003001_PageChangeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HK003001_PageChangeAction() {
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
		String url = "/jsp/hk003/hk003001.jsp";
		try {
			boolean isValid = true;
			String startCode = request.getParameter("startCode");
			//System.out.println(startCode);
			String endCode = request.getParameter("endCode");
			String tantouName = request.getParameter("tantouName");
			String page = request.getParameter("page");
			if (page == null || page.isEmpty()) {
				page = "1";
			}
			//未入力チェック
			if (startCode == null || startCode.isEmpty()||
					endCode == null || endCode.isEmpty() ||
							tantouName == null || tantouName.isEmpty()) {
					request.setAttribute("CHECKEMPTY", "全部入力してください");
					isValid = false;
			}
			//バリデーション実装
			//TODO:MessageUtils実装完了したら、ここに修正する
			if (!ValidateUtil.checkHalfWidth(startCode) || !ValidateUtil.checkHalfWidth(endCode)) {
				request.setAttribute("HALFWIDTHERR", "半角数字だけ入力してください");
				isValid = false;
			}
			if (!ValidateUtil.checkMaxLength(startCode, 4) || !ValidateUtil.checkMaxLength(endCode, 4) ||
				!ValidateUtil.checkMinLength(startCode, 4) || !ValidateUtil.checkMinLength(endCode, 4)) {
				request.setAttribute("CODELENGTHERR", "担当コードが４文字数");
				isValid = false;
			}
			if (!ValidateUtil.checkMaxLength(tantouName, 10)) {
				request.setAttribute("TANTOUNAMEERR", "担当名の入力最大長が10文字");
				isValid = false;
			}
			request.setAttribute("STARTCODE", startCode);
			request.setAttribute("ENDCODE", endCode);
			request.setAttribute("TANTOUNAME", tantouName);
			if (isValid) {
				int numPage = Integer.parseInt(page);
				List<TantouTableDto> resultList = TantouTableDao.selectByCondition(startCode, endCode, tantouName,
						numPage);
				int listSize = TantouTableDao.countByCondition(startCode, endCode, tantouName);
				if (listSize == 0 || resultList.isEmpty() || resultList == null) {
					request.setAttribute("NORESULT", "該当するデータが存在しません。");
				}
				//必要な変数を設定する
				request.setAttribute("LISTSIZE", listSize);
				request.setAttribute("RESULTLIST", resultList);
				//UTF-8エンコーディング対応
				request.setAttribute("STARTCODE", URLEncoder.encode(startCode, "UTF-8"));
				request.setAttribute("ENDCODE", URLEncoder.encode(endCode, "UTF-8"));
				request.setAttribute("TANTOUNAME", tantouName);
				request.setAttribute("NUMBERFIRST", numPage * 10 - 9);
				request.setAttribute("NUMBERLAST", (numPage * 10));
				request.setAttribute("PAGE", numPage);
				request.setAttribute("TANTOUENCODE", URLEncoder.encode(tantouName, "UTF-8"));
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