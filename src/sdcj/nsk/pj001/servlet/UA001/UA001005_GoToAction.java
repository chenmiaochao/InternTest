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

import sdcj.nsk.pj001.dto.UridenMTableDto;

/**
 * Servlet implementation class UA001005_GoToAction
 */
@WebServlet("/UA001005_GoToAction")
public class UA001005_GoToAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UA001005_GoToAction() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//売上伝票の初期値の設定
		String url = "/jsp/ua001/ua001005.jsp";
		try {
			List<UridenMTableDto> tempList = new ArrayList<UridenMTableDto>();
			for (int i = 0; i < 4; i++) {
				UridenMTableDto dto = new UridenMTableDto();
				dto.setShohinCode("");
				dto.setSyohinName("");
				dto.setSuryo("");
				tempList.add(dto);
			}
			HttpSession session = request.getSession();
			session.setAttribute("URIDENLIST", tempList);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}