package sdcj.nsk.pj001.servlet.CM002;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class BackToMenuAction
 */
/**
 * Servlet implementation class BackToMenuAction
 */
@WebServlet("/BackToMenuAction")
public class BackToMenuAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackToMenuAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 共通メソッド
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// メニュー画面へ遷移する
		String url = "/jsp/cm002/cm002001.jsp";
		String url1 = "/jsp/cm002/cm002001.jsp";
		getServletConfig().getServletContext().getRequestDispatcher(url).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
