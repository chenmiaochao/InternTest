package sdcj.nsk.pj001.servlet.HK001;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HK001001_ClearAction
 */
@WebServlet("/HK001001_ClearAction")
public class HK001001_ClearAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HK001001_ClearAction() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author nguyen.hungminh
	 * @implNote Clearボタンを押下するとき、商品選択補助検索画面に戻り、検索フォームもリセット
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = "/jsp/hk001/hk001001.jsp";
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
		}catch (Exception ex) {
			//TODO: エラーページに移るため、url変数新値を代入する
			ex.printStackTrace();
		}finally {
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}