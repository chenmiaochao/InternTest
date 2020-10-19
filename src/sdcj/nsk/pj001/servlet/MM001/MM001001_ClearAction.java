package sdcj.nsk.pj001.servlet.MM001;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MM001001_ClearAction
 */
@WebServlet("/MM001001_ClearAction")
public class MM001001_ClearAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MM001001_ClearAction() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author 梶原拓斗
	 * @implNote Clearボタンを押下するとき、商品マスタ検索画面に戻り、検索フォームもリセット
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = "/jsp/mm001/mm001001.jsp";
		try {
			//不要なアトリビュートを削除
			request.removeAttribute("LISTSIZE");
			request.removeAttribute("RESULTLIST");
			request.removeAttribute("STARTCODE");
			request.removeAttribute("ENDCODE");
			request.removeAttribute("SHOHINNAME");
			request.removeAttribute("NUMBERFIRST");
			request.removeAttribute("NUMBERLAST");
			request.removeAttribute("PAGE");
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}catch (Exception ex) {
			//TODO: エラーページに移るため、url変数新値を代入する
			ex.printStackTrace();
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
