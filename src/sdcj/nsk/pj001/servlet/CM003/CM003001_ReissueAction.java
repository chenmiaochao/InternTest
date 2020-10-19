package sdcj.nsk.pj001.servlet.CM003;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.AccountTableDao;
import sdcj.nsk.pj001.dto.AccountTableDto;
import sdcj.nsk.pj001.utils.MessageUtil;

/**
 * Servlet implementation class CM003001_ReissueAction
 */
@WebServlet("/CM003001_ReissueAction")
public class CM003001_ReissueAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CM003001_ReissueAction() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @author 蟹井
	 * @implNote パスワードの再発行（ランダムな英数字）とメールの送信
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mode = "1";
		String url = "/jsp/cm003/cm003001.jsp";

		// 入力値（メアド）を取得
		String inputMailaddress = request.getParameter("mailaddress");

		String hissu[] = { "「メールアドレス」" };
		String max[] = { "「メールアドレス」", "255" };
		String hankaku[] = { "「メールアドレス」", "半角" };
		String format[] = { "「メールアドレス」", "正しい形式" };
		String succsess[] = {"「新しいパスワード」","送信"};
		String message = "";

		//入力チェック
		// 必須チェックでエラーがある場合、以下の文字列を画面項目エラーメッセージに編集する。　エラーメッセージID:MSG008
		if (inputMailaddress.equals("") || inputMailaddress.length() <= 0) {
			mode = "0";
			message = MessageUtil.errorMessage("MSG008", hissu) + "<br>";
		} else {
			// 半角チェック でエラーがある場合、以下の文字列を画面項目.エラーメッセージに編集する。　エラーメッセージID:MSG010
			Pattern p = Pattern.compile("^[\\p{Alnum}|\\p{Punct}]+$");
			Matcher m = p.matcher(inputMailaddress);
			if (!m.find()) {
				mode = "0";
				message += MessageUtil.errorMessage("MSG010", hankaku) + "<br>";
			} else {
				// 最大長チェック でエラーがある場合、以下の文字列を画面項目.エラーメッセージに編集する。　エラーメッセージID:MSG009
				if (inputMailaddress.length() > 255) {
					mode = "0";
					message += MessageUtil.errorMessage("MSG009", max) + "<br>";
				}
				// // emailチェック でエラーがある場合、以下の文字列を画面項目.エラーメッセージに編集する。　エラーメッセージID:
				String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
				if (!inputMailaddress.matches(mailFormat)) {
					mode = "0";
					message += MessageUtil.errorMessage("MSG010", format) + "<br>";
				}
			}
		}

		// アカウントマスタ をメールアドレスで検索し、該当レコードを検索する。
		if (mode.equals("1")) {
			// 入力値（メアド）からユーザ情報を取得
			AccountTableDao accountTableDao = new AccountTableDao();
			AccountTableDto accountDto = accountTableDao.selectByCondition(inputMailaddress);

			// データが取得できなかった場合、以下の文字列を画面項目エラーメッセージに編集する。　エラーメッセージID：MSG012
			if (accountDto == null) {
				mode = "0";
				message += MessageUtil.errorMessage("MSG012", null) + "<br>";
			} else {

				// 再発行パスワードをランダムな英数字に設定
				//パスワード桁数
				int length = 16;

				//生成処理
				StringBuilder result = new StringBuilder();
				//パスワードに使用する文字を格納
				StringBuilder source = new StringBuilder();

				//数字
				for (int i = 0x30; i < 0x3A; i++) {
					source.append((char) i);
				}
				//アルファベット
				for (int i = 0x41; i < 0x5b; i++) {
					source.append((char) i);
				}
				for (int i = 0x61; i < 0x7b; i++) {
					source.append((char) i);
				}

				int sourceLength = source.length();
				Random random = new Random();
				while (result.length() < length) {
					result.append(source.charAt(Math.abs(random.nextInt()) % sourceLength));
				}

				String rePassword = result.toString();
				Timestamp currentTime = new Timestamp(System.currentTimeMillis());
				Timestamp updateTime = accountDto.getUpdateTime();
				String loginId = accountDto.getLoginId();

				// アカウントマスタ をログインID、メールアドレス、変更日時で検索し、該当レコードの再発行パスワードを更新する。
				accountTableDao.updateRePasswdByCondition(rePassword, currentTime, inputMailaddress, loginId, updateTime);

				int updateRePasswd = accountTableDao.updateRePasswdByCondition(rePassword, currentTime, inputMailaddress,
						loginId, updateTime);

				// 更新できなかった場合、以下の文字列を画面項目エラーメッセージに編集する。　エラーメッセージID：MSG004
				if (updateRePasswd == 0) {
					mode = "0";
					message += MessageUtil.errorMessage("MSG004", null) + "<br>";

				}

				// 送信するメールの件名と本文
				String subject = "[研修プロジェクト2020] パスワードリマインダ";
				String msg = "研修プロジェクト2020 商品管理／売上管理\r\n\n"
						+ "パスワードを更新しました。\r\n"
						+ "パスワード: " + rePassword + "\r\n\n"
						+ "下記URLからログインしてください。\r\n"
						+ "http://localhost:8090/sales-mgr/InitAction";

				// 送信元の設定
				String userName = "kanii0826yukie@gmail.com";
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "465");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.ssl.enable", "true");

				Session session = Session.getDefaultInstance(props,
						new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(userName, "kke33#yuad");
							}
						});

				// メールを送信
				try {
					MimeMessage mimeMessage = new MimeMessage(session);
					mimeMessage.setFrom(new InternetAddress(userName));
					mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(inputMailaddress));
					mimeMessage.setSubject(subject);
					mimeMessage.setText(msg);

					Transport.send(mimeMessage);

				} catch (MessagingException e) {
					throw new RuntimeException(e);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				// 送信完了後、以下の文字列を画面項目処理完了メッセージに編集する。　エラーメッセージID：MSG005
				message += MessageUtil.errorMessage("MSG005", succsess) + "<br>";
			}
		}

		request.setAttribute("message", message);
		request.setAttribute("mode", mode);

		// 画面遷移
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
