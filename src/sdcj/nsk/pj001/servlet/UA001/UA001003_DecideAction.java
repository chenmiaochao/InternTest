package sdcj.nsk.pj001.servlet.UA001;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.UridenHTableDao;
import sdcj.nsk.pj001.dao.UridenMTableDao;
import sdcj.nsk.pj001.dto.AccountTableDto;
import sdcj.nsk.pj001.dto.UridenHTableDto;
import sdcj.nsk.pj001.dto.UridenMTableDto;
import sdcj.nsk.pj001.utils.MessageUtil;

/**
 * Servlet implementation class UA001003_DecideAction
 */
@WebServlet("/UA001003_DecideAction")
public class UA001003_DecideAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UA001003_DecideAction() {
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
	 * @author shutsuno
	 * @implNote UA001003_削除確認画面の削除ボタンを押下した際にUA001004_削除完了画面へ遷移する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ログインしているユーザー情報を取得
		var session = request.getSession();
		var loginUser = (AccountTableDto)session.getAttribute("loginUser");

		//ログイン判定実装
		if (loginUser == null || loginUser.getLoginId().isEmpty()) {
			// 未ログイン
			response.sendRedirect("jsp/cm001/cm001001.jsp");
			return;
		}

		String url = "/jsp/ua001/ua001004.jsp";
		String error = "/jsp/ua001/ua001003.jsp";

		//UA001003_削除確認画面から値を取得
		String denNo = request.getParameter("denNo");
		String tim = request.getParameter("updateTime");

		//変数の宣言
		//売上伝票ヘッダのDTOから取得する更新時間
		Timestamp updateTime = null;
		//売上伝票明細のDTOから取得する更新時間
		Timestamp updateTime2 = null;
		//UA001003_削除確認画面から取得した更新時間をTimestamp型に変換した更新時間
		Timestamp TStim = null;
		//エラーメッセージの置換用の配列
		String msgB1[] = {"売上伝票ヘッダ","削除"};
		String msgB2[] = {"売上伝票明細","削除"};

		String o = "5555";

		//String型からTimestamp型への変換
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			TStim = new Timestamp(sdf.parse(tim).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		//伝票番号から売上伝票ヘッダの更新時間を取得する
		try {
			List<UridenHTableDto> UridenH = UridenHTableDao.selectByDenNo(denNo);
			updateTime = UridenH.get(0).getUpdateTime();

		//売上伝票ヘッダより、データが取得できなかった場合、確認画面へ遷移し(留まり)、エラーメッセージを表示する
		}catch(Exception e) {
			request.setAttribute("message",MessageUtil.errorMessage("MSG004", null));
			RequestDispatcher rd = request.getRequestDispatcher(error);
			rd.forward(request, response);
		}

		//売上伝票ヘッダより、取得したデータの変更日時≠削除するデータの変更日時 の場合、確認画面へ遷移し(留まり)、エラーメッセージを表示する
		if(!(TStim.equals(updateTime))) {
			request.setAttribute("message",MessageUtil.errorMessage("MSG004", null));
			RequestDispatcher rd = request.getRequestDispatcher(error);
			rd.forward(request, response);

		}else {


			//伝票番号から売上伝票明細を取得する
			try {
				List<UridenMTableDto> UridenM = UridenMTableDao.countByDenNo(denNo);

				updateTime2 = UridenM.get(0).getUpdateTime();

			//売上伝票明細より、データが取得できなかった場合、確認画面へ遷移し(留まり)、エラーメッセージを表示する
			}catch(Exception e) {
				request.setAttribute("message",MessageUtil.errorMessage("MSG004", null));
				RequestDispatcher rd = request.getRequestDispatcher(error);
				rd.forward(request, response);
			}

			//削除の実行

			int retUH = UridenHTableDao.deleteByCondition(denNo,updateTime);

			//売上伝票ヘッダの削除時、DB接続エラーが発生した際は確認画面へ遷移し(留まり)、エラーメッセージを表示する
			if(retUH == 0) {
				request.setAttribute("message",MessageUtil.errorMessage("MSG006", msgB1));
				RequestDispatcher rd = request.getRequestDispatcher(error);
				rd.forward(request, response);
			}

			int retUM = UridenMTableDao.deleteByDenNo(denNo);

			//売上伝票明細の削除時、DB接続エラーが発生した際は確認画面へ遷移し(留まり)、エラーメッセージを表示する
			if(retUM == 0) {
				request.setAttribute("message",MessageUtil.errorMessage("MSG006", msgB2));
				RequestDispatcher rd = request.getRequestDispatcher(error);
				rd.forward(request, response);
			}

			//正常に削除ができた場合は削除完了画面に遷移する
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);

		}

	}

}