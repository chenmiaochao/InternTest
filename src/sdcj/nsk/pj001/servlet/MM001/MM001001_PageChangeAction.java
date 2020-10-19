package sdcj.nsk.pj001.servlet.MM001;

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
import sdcj.nsk.pj001.utils.MessageUtil;
import sdcj.nsk.pj001.utils.ValidateUtil;

/**
 * Servlet implementation class MM001001_PageChangeAction
 */
@WebServlet("/MM001001_PageChangeAction")
public class MM001001_PageChangeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MM001001_PageChangeAction() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author 梶原拓斗
	 * @implNote ページ移るロジック
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = "/jsp/mm001/mm001001.jsp";
		try {
			boolean isValid = true;
			String startCode = request.getParameter("startCode");
			String endCode = request.getParameter("endCode");
			String shohinName = request.getParameter("shohinName");
			String page = request.getParameter("page");
			int codesemp = 0;
			int codeeemp = 0;
			int nameemp = 0;

			String startcode[] = {"商品コード_開始"};
			String endcode[] = {"商品コード_終了"};
			String shohinname[] = {"商品名"};
			String shohincode[] = {"商品コード"};
			String starthalfwidth[] = {"商品コード_開始","半角"};
			String endhalfwidth[] = {"商品コード_終了","半角"};
			String checkstartlength[] = {"商品コード_開始","13"};
			String checkendlength[] = {"商品コード_終了","13"};
			String checknamelength[] = {"商品名","20"};
			String empty[]= {""};


			if (page == null || page.isEmpty()) {
				page = "1";
			}

			if(startCode.isEmpty()||endCode.isEmpty()||shohinName.isEmpty()) {
				if(startCode.isEmpty()) {
					startCode = "0";
					codesemp = 1;
				}
				if(endCode.isEmpty()) {
					endCode = "zzzzzzzzzzzzz";
					codeeemp = 1;
				}
				if(shohinName.isEmpty()) {
					shohinName = "";
					nameemp = 1;
				}
			}

			//大小比較
			if(startCode.compareTo(endCode)==1) {
				request.setAttribute("CHECKCOMPAREERR",MessageUtil.errorMessage("MSG007", shohincode));
				isValid = false;
			}

			//バリデーション実装
			//半角チェック
			if (!ValidateUtil.checkHalfWidth(startCode)) {
				request.setAttribute("STARTHALFWIDTHERR",MessageUtil.errorMessage("MSG010", starthalfwidth));
				isValid = false;
			}

			if (!ValidateUtil.checkHalfWidth(endCode)) {
				request.setAttribute("ENDHALFWIDTHERR",MessageUtil.errorMessage("MSG010", endhalfwidth));
				isValid = false;
			}

			//最大長チェック
			if (!ValidateUtil.checkMaxLength(startCode,13)) {
				request.setAttribute("CHECKSTARTLENGTH",MessageUtil.errorMessage("MSG009", checkstartlength));
				isValid = false;
			}

			if (!ValidateUtil.checkMaxLength(endCode,13)) {
				request.setAttribute("CHECKENDLENGTH",MessageUtil.errorMessage("MSG009", checkendlength));
				isValid = false;
			}

			if (!ValidateUtil.checkMaxLength(shohinName,20)) {
				request.setAttribute("CHECKNAMELENGTH",MessageUtil.errorMessage("MSG009", checknamelength));
				isValid = false;
			}

			if (isValid) {
				int numPage = Integer.parseInt(page);
				List<HK001001DetailDto> resultList = ShouhinTableDao.selectByCondition(startCode, endCode, shohinName,
						numPage);
				int listSize = ShouhinTableDao.countByCondition(startCode, endCode, shohinName);
				if (listSize == 0 || resultList.isEmpty()) {
					request.setAttribute("NORESULT", MessageUtil.errorMessage("MSG001",empty));
				}
				if((codesemp == 1)||(codeeemp == 1)||(nameemp == 1)) {
					if(codesemp == 1) {
						startCode = "";
					}
					if(codeeemp == 1) {
						endCode = "";
					}
					if(nameemp == 1) {
						shohinName = "";
					}
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
