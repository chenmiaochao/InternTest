package sdcj.nsk.pj001.servlet.HK002;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sdcj.nsk.pj001.dto.UridenJViewDto;

/**
 * Servlet implementation class HK002001_SelectAction
 * @author nguyen.hungminh
 */
@WebServlet("/HK002001_SelectAction")
public class HK002001_SelectAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HK002001_SelectAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author nguyen.hungminh
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = "/jsp/ua001/ua001005.jsp";
		try {
			HttpSession session = request.getSession();
			String tokuiCode = request.getParameter("tokuiCode");
			String oyaPage = (String) session.getAttribute("OYA");
			if (oyaPage.equals("UA001005")) {
				List<UridenJViewDto> tempList = (List<UridenJViewDto>)session.getAttribute("URIDENLIST");
				tempList.get(0).setTokuiCode_001(tokuiCode);
				tempList.get(0).setTokuiCode_003(tokuiCode);
				session.setAttribute("URIDENLIST", tempList);
			}else if (oyaPage.equals("MM002001")) {
				String order = (String) session.getAttribute("TOKUIORDER");
				if (order.equals("from")) {
					session.setAttribute("TOKUICODEFROM", tokuiCode);
				}else {
					session.setAttribute("TOKUICODETO", tokuiCode);
				}
				url = "/jsp/mm002/mm002001.jsp";
			}else if (oyaPage.equals("CM005001")) {
				String order = (String) session.getAttribute("TOKUIORDER");
				if (order.equals("from")) {
					session.setAttribute("TOKUICODEFROM", tokuiCode);
				}else {
					session.setAttribute("TOKUICODETO", tokuiCode);
				}
				url = "/jsp/cm005/cm005001.jsp";
			}else {
				url = "/jsp/cm002/cm002001.jsp";
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}