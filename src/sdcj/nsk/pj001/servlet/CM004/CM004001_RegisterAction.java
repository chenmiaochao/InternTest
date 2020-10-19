package sdcj.nsk.pj001.servlet.CM004;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

/**
 * Servlet implementation class CM004001_RegisterAction
 */
@WebServlet("/CM004001_RegisterAction")
public class CM004001_RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CM004001_RegisterAction() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @author 蟹井
	 * @implNote パスワードの変更
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/jsp/cm004/cm004001.jsp";
		boolean checkInput = true;
		boolean changePass = false;
		String message = "";


		// ログインIDからパスワードおよび更新日時の取得
		HttpSession session = request.getSession();
		AccountTableDto accountTableDto = new AccountTableDto();
		accountTableDto = (AccountTableDto) session.getAttribute("loginUser");

		String loginId = accountTableDto.getLoginId();
		Timestamp updateTime = accountTableDto.getUpdateTime();
		String passwd = accountTableDto.getPassword();

		// cm004001.jspから入力値を取得
		String oldPass = request.getParameter("oldPass");
		String newPass = request.getParameter("newPass");
		String kakuninPass = request.getParameter("kakuninPass");

		//入力チェック
		// 旧パスワード必須チェック
		if (oldPass.equals("") || oldPass.length() <= 0) {
			message = "「旧パスワード」を入力してください。<br>";
			checkInput = false;
		} else {
			// 旧パスワード最大長チェック
			if (oldPass.length() > 16) {
				message = "「旧パスワード」は16桁以内で入力してください。<br>";
				checkInput = false;
			} else {
				// 旧パスワード半角チェック
				Pattern p = Pattern.compile("^[\\p{Alnum}|\\p{Punct}]+$");
				Matcher m = p.matcher(oldPass);
				if (!m.find()) {
					message = "「旧パスワード」は半角で入力してください。<br>";
					checkInput = false;
				}
			}
		}
		// 新パスワード必須チェック
		if (newPass.equals("") || newPass.length() <= 0) {
			message += "「新パスワード」を入力してください。<br>";
			checkInput = false;
		} else {
			// 新パスワード最大長チェック
			if (newPass.length() > 16) {
				message += "「新パスワード」は16桁以内で入力してください。<br>";
				checkInput = false;
			} else {
				// 新パスワード半角チェック
				Pattern p = Pattern.compile("^[\\p{Alnum}|\\p{Punct}]+$");
				Matcher m = p.matcher(newPass);
				if (!m.find()) {
					message += "「新パスワード」は半角で入力してください。<br>";
					checkInput = false;
				}
			}
		}
		// 新パスワード確認必須チェック
		if (kakuninPass.equals("") || kakuninPass.length() <= 0) {
			message += "「新パスワード確認」を入力してください。<br>";
			checkInput = false;
		}

		// ①アカウントマスタから取得したパスワードと旧パスワードの入力値を比較
		if (checkInput) {
			changePass = true;

			// ①が一致しなければエラーメッセージを表示
			if ((!oldPass.equals("") || oldPass.length() > 0) && !passwd.equals(oldPass)) {
				message += MessageUtil.errorMessage("MSG019", null) + "<br>";
				changePass = false;
			}

			// ②新パスワードと新パスワード確認の入力値を比較
			// ②が一致しなければエラーメッセージを表示
			if (!newPass.equals(kakuninPass)) {
				message += MessageUtil.errorMessage("MSG017", null) + "<br>";
				changePass = false;
			}
		}

		// ①②が等しければ更新sqlを実行し、成功メッセージを表示
		if (changePass) {
			// 更新日時を取得
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());

			accountTableDto.setPassword(newPass);
			accountTableDto.setUpdateTime(currentTime);

			AccountTableDao accountTableDao = new AccountTableDao();
			int atd = accountTableDao.updatePasswdByCondition(newPass,currentTime,loginId,updateTime);

			if(atd==1) {
				message = MessageUtil.errorMessage("MSG020", null) + "<br>";
				request.setAttribute("message", message);

				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}else {
				message = MessageUtil.errorMessage("MSG018", null) + "<br>";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}


		} else {
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}

	}

}
