package sdcj.nsk.pj001.servlet.UA001;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.UridenJViewDao;
import sdcj.nsk.pj001.dto.AccountTableDto;
import sdcj.nsk.pj001.dto.UridenJViewDto;
import sdcj.nsk.pj001.utils.MessageUtil;

/**
 * Servlet implementation class UA001005_GoToAction
 */
@WebServlet("/UA001001_EditAction")
public class UA001001_EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UA001001_EditAction() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//売上伝票の初期値の設定
		var session = request.getSession();
		var loginUser = (AccountTableDto)session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getLoginId().isEmpty()) {
			// 未ログイン
			response.sendRedirect("jsp/cm001/cm001001.jsp");
			return;
		}
		String url = "/jsp/ua001/ua001005.jsp";
		String mode = "";
		try {
			String denNo = request.getParameter("denNo");
			//System.out.println(denNo);
			List<UridenJViewDto> tempList = null;

			try {
				tempList = UridenJViewDao.selectByDenNo(denNo);
				//取得方法は若干設計書と違うため、エラーの確認も違う
				if (tempList.get(0).getShohiCode_002() == null || tempList.isEmpty() || tempList.get(0).getShohiCode_002().equals("")) {
					String displayMsg[] = { "伝票番号", denNo };
					String errMsg = MessageUtil.errorMessage("MSG003", displayMsg);
					request.setAttribute("ERRMSG", errMsg);
					mode = "1";
				}else {
					mode = "2";
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				mode = "1";
			}
			//System.out.println(tempList.size());
			//session = request.getSession();
			session.removeAttribute("URIDENLIST");
			session.setAttribute("URIDENLIST", tempList);
			//1:編集がエラーあり
			//2:編集がエラーなし
			if (mode.equals("1")) {
				session.setAttribute("MODE", mode);
			} else if (mode.equals("2")) {
				//最終変更日時の取得
				try {
					session.setAttribute("HENKOUTIME", tempList.get(0).getUpdateTime_001());
					session.setAttribute("MODE",mode);

				} catch (Exception ex) {
					//TODO: エラーページに遷移
					ex.printStackTrace();
					mode = "1";
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			//TODO: エラーページに移る
		} finally {
			System.out.println("Mode:" + mode);
			getServletConfig().getServletContext().getRequestDispatcher(url).forward(request,
					response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}