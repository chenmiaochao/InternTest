package sdcj.nsk.pj001.servlet.MM001;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MM001003_BackAction
 */
@WebServlet("/MM001003_BackAction")
public class MM001003_BackAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MM001003_BackAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = "/jsp/mm001/mm001002.jsp";

		String shohinCode = request.getParameter("shohinCode");
		String shohinName = request.getParameter("shohinName");
		String tanka = request.getParameter("tanka");
		String mode = request.getParameter("mode");
		if(mode.equals("2")) {
			try {
				Timestamp updateTime = Timestamp.valueOf(request.getParameter("updateTime"));
				request.setAttribute("UPDATETIME", updateTime);

				System.out.println(updateTime);
			}catch(Exception ex) {
				ex.printStackTrace();
			}


		}

		try {
			request.setAttribute("SHOHINCODE",shohinCode);
			request.setAttribute("SHOHINNAME", URLEncoder.encode(shohinName, "UTF-8"));
			request.setAttribute("TANKA", tanka);

			if(mode.equals("0")) {
				request.setAttribute("MODE", "0");
			}else {
				request.setAttribute("MODE", "2");
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			//MM001002_商品マスタ登録/更新画面へ画面遷移
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}


	}

}
