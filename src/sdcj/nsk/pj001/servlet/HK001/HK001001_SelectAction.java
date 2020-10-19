package sdcj.nsk.pj001.servlet.HK001;

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
 * Servlet implementation class HK001001_SelectAction
 */
@WebServlet("/HK001001_SelectAction")
public class HK001001_SelectAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HK001001_SelectAction() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author nguyen.hungminh
	 * @implNote 妥当な行で結果を返すロジック
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = "/jsp/ua001/ua001005.jsp";
		try {
			String shohinCode = request.getParameter("shohinCode");
			HttpSession session = request.getSession();
			String oyaPage = (String) session.getAttribute("OYA");
			if (oyaPage.equals("UA001005")) {
				String selectedRow = (String) session.getAttribute("SELECTEDROW");
				List<UridenJViewDto> tempList = (List<UridenJViewDto>) session.getAttribute("URIDENLIST");
				//親画面での妥当な行に売上伝票Listに設定する
				if (selectedRow.equals("0")) {
					tempList.get(0).setShohiCode_002(shohinCode);

				} else if (selectedRow.equals("1")) {
					tempList.get(1).setShohiCode_002(shohinCode);
				} else if (selectedRow.equals("2")) {
					tempList.get(2).setShohiCode_002(shohinCode);
				} else {
					tempList.get(3).setShohiCode_002(shohinCode);
				}
				//session.removeAttribute("SELECTEDROW");
				session.setAttribute("URIDENLIST", tempList);
			}else if (oyaPage.equals("MM002001")) {
				String order = (String) session.getAttribute("SHOHINORDER");
				if (order.equals("from")) {
					session.setAttribute("SHOHINCODEFROM", shohinCode);
				}else {
					session.setAttribute("SHOHINCODETO", shohinCode);
				}
				url = "/jsp/mm002/mm002001.jsp";
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
