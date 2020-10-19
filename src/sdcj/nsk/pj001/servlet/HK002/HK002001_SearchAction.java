package sdcj.nsk.pj001.servlet.HK002;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.TokuiTableDao;
import sdcj.nsk.pj001.dto.TokuiTableDto;
import sdcj.nsk.pj001.utils.ValidateUtil;

/**
 * Servlet implementation class HK002001_SearchAction
 * @author nguyen.hungminh
 */
@WebServlet("/HK002001_SearchAction")
public class HK002001_SearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HK002001_SearchAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author nguyen.hungminh
	 * @implNote 検索ボタンのロジック実装する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = "/jsp/hk002/hk002001.jsp";
		try {
			boolean isValid = true;
			String startCode = request.getParameter("startCode");
			//System.out.println(startCode);
			String endCode = request.getParameter("endCode");
			String tokuiName = request.getParameter("tokuiName");
			String page = request.getParameter("page");
			if (page == null || page.isEmpty()) {
				page = "1";
			}
			//未入力チェック
			if (startCode == null || startCode.isEmpty()||
					endCode == null || endCode.isEmpty() ||
							tokuiName == null || tokuiName.isEmpty()) {
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

				request.setAttribute("CODELENGTHERR", "得意先コードが４文字数");
				isValid = false;
			}
			if (!ValidateUtil.checkMaxLength(tokuiName, 20)) {
				request.setAttribute("TOKUINAMEERR", "得意先名の入力最大長が２０文字");
				isValid = false;
			}
			request.setAttribute("STARTCODE", startCode);
			request.setAttribute("ENDCODE", endCode);
			request.setAttribute("TOKUINAME", tokuiName);
			if (isValid) {
				int numPage = Integer.parseInt(page);
				List<TokuiTableDto> resultList = TokuiTableDao.selectByCondition(startCode, endCode, tokuiName,
						numPage);
				int listSize = TokuiTableDao.countByCondition(startCode, endCode, tokuiName);
				if (listSize == 0 || resultList.isEmpty()) {
					request.setAttribute("NORESULT", "該当するデータが存在しません。");
				}
				//必要な変数を設定する
				request.setAttribute("LISTSIZE", listSize);
				request.setAttribute("RESULTLIST", resultList);
				//UTF-8エンコーディング対応
				request.setAttribute("STARTCODE", URLEncoder.encode(startCode, "UTF-8"));
				request.setAttribute("ENDCODE", URLEncoder.encode(endCode, "UTF-8"));
				request.setAttribute("TOKUINAME", tokuiName);
				request.setAttribute("NUMBERFIRST", numPage * 10 - 9);
				request.setAttribute("NUMBERLAST", (numPage * 10));
				request.setAttribute("PAGE", numPage);
				request.setAttribute("TOKUIENCODE", URLEncoder.encode(tokuiName, "UTF-8"));

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

