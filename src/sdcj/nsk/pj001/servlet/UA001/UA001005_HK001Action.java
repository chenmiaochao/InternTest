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
 * Servlet implementation class UA001005_HK001Action
 */
@WebServlet("/UA001005_HK001Action")
public class UA001005_HK001Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UA001005_HK001Action() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author nguyen.hungminh
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/jsp/hk001/hk001001.jsp";
		try {

			//System.out.println(row);
			HttpSession session = request.getSession();
			String uid = request.getParameter("uid");
			if (uid.equals("UA001005")) {
				session.setAttribute("OYA", "UA001005");
				//売上伝票での行
				String row = request.getParameter("row");
				List<UridenMTableDto> tempList = (List<UridenMTableDto>) session.getAttribute("URIDENLIST");
				if (tempList == null) {
					tempList = new ArrayList<UridenMTableDto>();
					for (int i = 0; i < 4; i++) {
						UridenMTableDto dto = new UridenMTableDto();
						dto.setShohinCode("");
						dto.setSyohinName("");
						dto.setSuryo("");
						tempList.add(dto);
					}
				}
				//フォームで初期値の取得
				for (int i = 0; i < 4; i++) {
					UridenMTableDto uridenDto = new UridenMTableDto();
					uridenDto.setShohinCode(request.getParameter("shohinCode" + i));
					uridenDto.setSyohinName(request.getParameter("shohinName" + i));
					uridenDto.setTanka(request.getParameter("tanka" + i));

					uridenDto.setSuryo(request.getParameter("suryo" + i));
					tempList.add(uridenDto);
				}
				session.setAttribute("URIDENLIST", tempList);
				session.setAttribute("SELECTEDROW", row);
			} else {
				url = "/jsp/cm002/cm002001.jsp";
			}

		} catch (Exception e) {
			e.printStackTrace();
			//TODO: エラーページに移る
		} finally {
			getServletConfig().getServletContext().getRequestDispatcher(url).forward(request, response);
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
