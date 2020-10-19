package sdcj.nsk.pj001.servlet.HK002;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HK002001_ClearAction
 * @author nguyen.hungminh
 */
@WebServlet("/HK002001_ClearAction")
public class HK002001_ClearAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HK002001_ClearAction() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = "/jsp/hk002/hk002001.jsp";
		try {
			//不要なアトリビュートを削除
			request.removeAttribute("LISTSIZE");
			request.removeAttribute("RESULTLIST");
			request.removeAttribute("STARTCODE");
			request.removeAttribute("ENDCODE");
			request.removeAttribute("TOKUINAME");
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

