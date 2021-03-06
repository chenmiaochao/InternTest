package sdcj.nsk.pj001.servlet.CM005;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CM005001_HK002_ToAction
 */
@WebServlet("/CM005001_HK002_ToAction")
public class CM005001_HK002_ToAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CM005001_HK002_ToAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author shutsuno,minh
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/jsp/hk002/hk002001.jsp";
		try {
			HttpSession session = request.getSession();
			String uid = request.getParameter("uid");
			if (uid.equals("CM005001")) {
				session.setAttribute("TOKUIORDER", "to");
				session.setAttribute("OYA", uid);
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
