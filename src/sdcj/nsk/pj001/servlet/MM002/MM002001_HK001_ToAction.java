package sdcj.nsk.pj001.servlet.MM002;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MM002001_HK001_ToAction
 */
@WebServlet("/MM002001_HK001_ToAction")
public class MM002001_HK001_ToAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MM002001_HK001_ToAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author nguyen.hungminh
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/jsp/hk001/hk001001.jsp";
		try {
			HttpSession session = request.getSession();
			String uid = request.getParameter("uid");
			if (uid.equals("MM002001")) {
				session.setAttribute("SHOHINORDER","to");
				session.setAttribute("OYA", uid);
			}else {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
