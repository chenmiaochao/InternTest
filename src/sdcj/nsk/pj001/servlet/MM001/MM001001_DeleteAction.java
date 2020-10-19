package sdcj.nsk.pj001.servlet.MM001;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.ShouhinTableDao;
import sdcj.nsk.pj001.dto.ShohinTableDto;
import sdcj.nsk.pj001.utils.MessageUtil;

/**
 * Servlet implementation class MM001001_DeleteAction
 */
@WebServlet("/MM001001_DeleteAction")
public class MM001001_DeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MM001001_DeleteAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *
	 * @author 梶原
	 * @implNote MM001001_商品マスタ一覧の削除ボタンを押下した際に、該当商品の削除処理を行い、MM001004_更新完了画面へ遷移する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/jsp/mm001/mm001004.jsp";
		String backurl = "/jsp/mm001/mm001001.jsp";

		//MM001001_商品マスタ一覧から商品コードと変更日時を取得
		String shohinCode = request.getParameter("shohinCode");
		Timestamp timestamp = Timestamp.valueOf(request.getParameter("updateTime"));

		String emptycode[] = { "商品コード", shohinCode };
		String differtime[] = { "" };
		String deleterw[] = { "商品マスタ", "削除" };

		try {
			List<ShohinTableDto> tempList = null;
			tempList = ShouhinTableDao.selectByShohinCodeK(shohinCode);
			if (tempList.size() > 0) {
				Timestamp timestampCheck = tempList.get(0).getUpdateTime();

				if (!(timestamp.equals(timestampCheck))) {
					request.setAttribute("DIFFERTIME", MessageUtil.errorMessage("MSG004", differtime));
					RequestDispatcher rd = request.getRequestDispatcher(backurl);
					rd.forward(request, response);
				}

				try {
					ShouhinTableDao ShouhinTableDaoD = new ShouhinTableDao();
					ShouhinTableDaoD.deleteByCondition(shohinCode, timestamp);
					request.setAttribute("DELETESUCCESS", MessageUtil.errorMessage("MSG005", deleterw));
				} catch (Exception ex) {
					request.setAttribute("MISSDELETE", MessageUtil.errorMessage("MSG006", deleterw));

				}
			}else {
				request.setAttribute("EMPTYCODE", MessageUtil.errorMessage("MSG003", emptycode));
				url = backurl;

			}
		} catch (Exception ex) {
			request.setAttribute("MISSDELETE", MessageUtil.errorMessage("MSG006", deleterw));
			RequestDispatcher rd = request.getRequestDispatcher(backurl);
			rd.forward(request, response);
			return;
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}

		//TODO エラーメッセージ処理

	}

}
