package sdcj.nsk.pj001.servlet.UA001;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sdcj.nsk.pj001.dto.AccountTableDto;
import sdcj.nsk.pj001.dto.UridenJViewDto;

/**
 * Servlet implementation class UA001004_RegisterAction
 */
@WebServlet("/UA001004_RegisterAction")
public class UA001004_RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UA001004_RegisterAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author shutsuno
	 * @apiNote 削除完了画面から伝票新規作成画面へ遷移する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ログインしているユーザー情報を取得
		var session = request.getSession();
		var loginUser = (AccountTableDto)session.getAttribute("loginUser");

		//ログイン判定実装
		if (loginUser == null || loginUser.getLoginId().isEmpty()) {
			// 未ログイン
			response.sendRedirect("jsp/cm001/cm001001.jsp");
			return;
		}

		String url = "/jsp/ua001/ua001005.jsp";
		try {
			List<UridenJViewDto> tempList = new ArrayList<UridenJViewDto>();
			for (int i = 0; i < 4; i++) {
				UridenJViewDto dto = new UridenJViewDto();
				tempList.add(dto);
			}
			HttpSession session2 = request.getSession();
			session2.removeAttribute("URIDENLIST");
			session2.setAttribute("URIDENLIST", tempList);
			session2.setAttribute("MODE", "0");

		} catch (Exception ex) {
			ex.printStackTrace();
			//TODO: エラーページに移る
		} finally {
			getServletConfig().getServletContext().getRequestDispatcher(url).forward(request,
					response);
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
