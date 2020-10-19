package sdcj.nsk.pj001.servlet.CM001;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sdcj.nsk.pj001.dao.AccountTableDao;
import sdcj.nsk.pj001.dto.AccountTableDto;
import sdcj.nsk.pj001.utils.MessageUtil;
import sdcj.nsk.pj001.utils.ValidateUtil;

/**
 * Servlet implementation class CM001001_LoginAction
 */
@WebServlet("/CM001001_LoginAction")
public class CM001001_LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CM001001_LoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * ログイン画面用メソッド
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//JSPから値を取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String url = "/jsp/cm001/cm001001.jsp";
		String url2 = "/jsp/cm002/cm002001.jsp";

		// TODO 入力チェック
		boolean flag = false;
		String errorMsg = "";
		String noLoginId[] = {"ユーザID"};
		String overLoginId[] = {"ユーザID","15"};
		String noPassword[] = {"パスワード"};
		String overPassword[] = {"パスワード","16"};
		String halfWidthId[] = {"ユーザID","半角"};
		String halfWidthPw[] = {"パスワード","半角"};

		if(loginId.length()==0) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG008", noLoginId) + "<br>";
			flag = true;
		}else if(loginId.length()>15) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG009", overLoginId) + "<br>";
			flag = true;
		}

		if(password.length()==0) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG008", noPassword) + "<br>";
			flag = true;
		}else if(password.length()>16) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG009", overPassword) + "<br>";
			flag = true;
		}

		// TODO 半角英数字チェック
		boolean rsId = true;
		try {
			rsId = ValidateUtil.checkHalfWidth(loginId);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		boolean rsPw = true;
		try {
			rsPw = ValidateUtil.checkHalfWidth(password);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		if(rsId == false) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG010", halfWidthId) + "<br>";
			flag = true;
		}
		if(rsPw == false) {
			errorMsg = errorMsg + MessageUtil.errorMessage("MSG010", halfWidthPw) + "<br>";
			flag = true;
		}

		//エラーが発生する場合の処理
		if(flag) {
			request.setAttribute("error", errorMsg);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		}
		HttpSession session = request.getSession();

		//ログインユーザの存在確認
		AccountTableDto loginUser = null;
		try {
			loginUser = AccountTableDao.selectByLoginId(loginId,password);

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		//担当コードがブランクの場合の処理
		if(loginUser == null || loginUser.getTantouCode()==null || loginUser.getTantouCode().isEmpty()) {
			request.setAttribute("error", MessageUtil.errorMessage("MSG016", null) +"<br>");
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}else if(loginUser.getRePasswd()==null || loginUser.getRePasswd().isEmpty()) {
			//再発行パスワードがブランクの場合の処理
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("status", true);

			RequestDispatcher rd = request.getRequestDispatcher(url2);
			rd.forward(request, response);
		}

		if (!password.equals(loginUser.getRePasswd())){
			//入力されたパスワードと再発行パスワードが一致しない場合の処理
			request.setAttribute("error", MessageUtil.errorMessage("MSG021", null) +"<br>");
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}else  {
			//入力されたパスワードと再発行パスワードが一致する場合の処理
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			Timestamp updateTime = loginUser.getUpdateTime();
			String mail = loginUser.getMail();
			loginUser.setPassword(password);
			loginUser.setUpdateTime(currentTime);

			AccountTableDao accountTableDao = new AccountTableDao();
			int upd = accountTableDao.updatePasswdByRePasswd(password,currentTime,loginId,mail,updateTime);

			if(upd==1) {
				session.setAttribute("loginUser", loginUser);
				session.setAttribute("status", true);

				RequestDispatcher rd = request.getRequestDispatcher(url2);
				rd.forward(request, response);
			}else {
				request.setAttribute("error", MessageUtil.errorMessage("MSG022", null) +"<br>");
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}

		}

	}

}
