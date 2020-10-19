package sdcj.nsk.pj001.servlet.UA001;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UA001005_HK003Action
 */
@WebServlet("/UA001005_HK003Action")
public class UA001005_HK003Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UA001005_HK003Action() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String url = "/jsp/hk003/hk003001.jsp";
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}