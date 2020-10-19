package sdcj.nsk.pj001.servlet.CM003;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CM003001_InitAction
 */
@WebServlet("/CM003001_InitAction")
public class CM003001_InitAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CM003001_InitAction() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author 蟹井
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsp = "/jsp/cm003/cm003001.jsp";
		String mode = "0";

		// 初期処理
		String message = "メールアドレスに該当するユーザのパスワードを再発行して、<br>アカウント情報をメールで送信します。<br>メールアドレスを入力してください。";
		request.setAttribute("message", message);
		request.setAttribute("mode", mode);

		// cm003001.jspに遷移
		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
