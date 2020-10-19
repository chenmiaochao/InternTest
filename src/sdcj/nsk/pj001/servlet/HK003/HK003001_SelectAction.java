package sdcj.nsk.pj001.servlet.HK003;

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
 * Servlet implementation class HK003001_SelectAction
 * @author nguyen.hungminh
 */
@WebServlet("/HK003001_SelectAction")
public class HK003001_SelectAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HK003001_SelectAction() {
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
			String tantouCode = request.getParameter("tantouCode");
			String oyaPage = (String) session.getAttribute("OYA");
			if (oyaPage.equals("UA001005")) {
				List<UridenJViewDto> tempList = (List<UridenJViewDto>) session.getAttribute("URIDENLIST");
				tempList.get(0).setTantouCode_001(tantouCode);
				tempList.get(0).setTantouCode_004(tantouCode);
				session.setAttribute("URIDENLIST", tempList);
			}else if (oyaPage.equals("MM002001")) {
				String order = (String) session.getAttribute("TANTOUORDER");
				if (order.equals("from")) {
					session.setAttribute("TANTOUCODEFROM", tantouCode);
				}else {
					session.setAttribute("TANTOUCODETO", tantouCode);
				}
				url = "/jsp/mm002/mm002001.jsp";
			}else if (oyaPage.equals("CM005001")) {
				String order = (String) session.getAttribute("TANTOUORDER");
				if (order.equals("from")) {
					session.setAttribute("TANTOUCODEFROM", tantouCode);
				}else {
					session.setAttribute("TANTOUCODETO", tantouCode);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}