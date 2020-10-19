package sdcj.nsk.pj001.servlet.UA001;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UA001005_HK002Action
 * @author nguyen.hungminh
 */
@WebServlet("/UA001005_HK002Action")
public class UA001005_HK002Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UA001005_HK002Action() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author nguyen.hungminh
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO:ロジック処理があれば後ほど実装
		String uid = request.getParameter("uid");
		String url = "/jsp/hk002/hk002001.jsp";
		try {
			HttpSession session = request.getSession();
			if (uid.equals("UA001005")) {
				session.setAttribute("OYA", "UA001005");
			} else {
				url = "/jsp/cm002/cm002001.jsp";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
