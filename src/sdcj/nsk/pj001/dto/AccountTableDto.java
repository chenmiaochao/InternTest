package sdcj.nsk.pj001.dto;

import java.sql.Timestamp;

/**
* アカウントテーブルの情報を保持するdtoクラス
* @author 近藤
*/
public class AccountTableDto {

	/**
	 * 担当者コード
	 */
	private String tantouCode = null;

	/**
	 * ログインID
	 */
	private String loginId = null;

	/**
	 * パスワード
	 */
	private String password = null;

	/**
	 * メールアドレス
	 */
	private String mail = null;

	/**
	 * 最終ログイン日時
	 */
	private Timestamp dtLastLogin = null;

	/**
	 * 登録日時
	 */
	private Timestamp entryTime = null;

	/**
	 * 登録者
	 */
	private String entryUser = null;

	/**
	 * 変更日時
	 */
	private Timestamp updateTime = null;

	/**
	 * 変更者
	 */
	private String updateUser = null;

	/**
	 * 再発行パスワード
	 */
	private String rePasswd = null;

	/**
	 * コンストラクタ
	 */
	public AccountTableDto() {
		super();
	}

	/**
	 * 担当者コードの取得
	 * @return 担当者コード
	 */
	public String getTantouCode() {
		return this.tantouCode;
	}

	/**
	 * 担当者コードの設定
	 * @param tantouCode 担当者コード
	 */
	public void setTantouCode(String tantouCode) {
		this.tantouCode = tantouCode;
	}

	/**
	 * ログインIDの取得
	 * @return ログインID
	 */
	public String getLoginId() {
		return this.loginId;
	}

	/**
	 * ログインIDの設定
	 * @param loginId ログインID
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * パスワードの取得
	 * @return パスワード
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * パスワードの設定
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * メールアドレスの取得
	 * @return メールアドレス
	 */
	public String getMail() {
		return this.mail;
	}

	/**
	 * メールアドレスの設定
	 * @param mail メールアドレス
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * 最終ログイン日時の取得
	 * @return 最終ログイン日時
	 */
	public Timestamp getDtLastLogin() {
		return this.dtLastLogin;
	}

	/**
	 * 最終ログイン日時の設定
	 * @param dtLastLogin 最終ログイン日時
	 */
	public void setDtLastLogin(Timestamp dtLastLogin) {
		this.dtLastLogin = dtLastLogin;
	}

	/**
	 * 登録日時の取得
	 * @return 登録日時
	 */
	public Timestamp getEntryTime() {
		return this.entryTime;
	}

	/**
	 * 登録日時の設定
	 * @param entryTime 登録日時
	 */
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}

	/**
	 * 登録者の取得
	 * @return 登録者
	 */
	public String getEntryUser() {
		return this.entryUser;
	}

	/**
	 * 登録者の設定
	 * @param entryUser 登録者
	 */
	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	/**
	 * 変更日時の取得
	 * @return 変更日時
	 */
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 変更日時の設定
	 * @param updateTime 変更日時
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 変更者の取得
	 * @return 変更者
	 */
	public String getUpdateUser() {
		return this.updateUser;
	}

	/**
	 * 変更者の設定
	 * @param updateUser 変更者
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * 再発行パスワードの取得
	 * @return 再発行パスワード
	 */
	public String getRePasswd() {
		return this.rePasswd;
	}

	/**
	 * 再発行パスワードの設定
	 * @param rePasswd 再発行パスワード
	 */
	public void setRePasswd(String rePasswd) {
		this.rePasswd = rePasswd;
	}
}
