package sdcj.nsk.pj001.dto;

import java.sql.Timestamp;

/**
* アカウントテーブルの情報を保持するdtoクラス
* @author 近藤
*/
public class AccountTantouTableDto {

	/**
	 * 担当者コード
	 */
	private String account_tantouCode = null;

	/**
	 * ログインID
	 */
	private String account_loginId = null;

	/**
	 * パスワード
	 */
	private String account_password = null;

	/**
	 * メールアドレス
	 */
	private String account_mail = null;

	/**
	 * 最終ログイン日時
	 */
	private Timestamp account_dtLastLogin = null;

	/**
	 * 登録日時
	 */
	private Timestamp account_entryTime = null;

	/**
	 * 登録者
	 */
	private String account_entryUser = null;

	/**
	 * 変更日時
	 */
	private Timestamp account_updateTime = null;

	/**
	 * 変更者
	 */
	private String account_updateUser = null;

	/**
	 * 担当コード
	 */
	private String tantou_tantouCode = null;

	/**
	 * 担当者名
	 */
	private String tantou_tantouName = null;

	/**
	 * 登録日時
	 */
	private Timestamp tantou_entryTime = null;

	/**
	 * 登録者
	 */
	private String tantou_entryUser = null;

	/**
	 * 更新日時
	 */
	private Timestamp tantou_updateTime = null;

	/**
	 * 更新者
	 */
	private String tantou_updateUser = null;

	/**
	 * 再発行パスワード
	 */
	private String account_rePasswd = null;

	/**
	 * コンストラクタ
	 */
	public AccountTantouTableDto() {
		super();
	}

	/**
	 * 担当者コードの取得
	 * @return 担当者コード
	 */
	public String getAccount_tantouCode() {
		return this.account_tantouCode;
	}

	/**
	 * 担当者コードの設定
	 * @param account_tantouCode 担当者コード
	 */
	public void setAccount_tantouCode(String account_tantouCode) {
		this.account_tantouCode = account_tantouCode;
	}

	/**
	 * ログインIDの取得
	 * @return ログインID
	 */
	public String getAccount_loginId() {
		return this.account_loginId;
	}

	/**
	 * ログインIDの設定
	 * @param account_loginId ログインID
	 */
	public void setAccount_loginId(String account_loginId) {
		this.account_loginId = account_loginId;
	}

	/**
	 * パスワードの取得
	 * @return パスワード
	 */
	public String getAccount_password() {
		return this.account_password;
	}

	/**
	 * パスワードの設定
	 * @param account_password パスワード
	 */
	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}

	/**
	 * メールアドレスの取得
	 * @return メールアドレス
	 */
	public String getAccount_mail() {
		return this.account_mail;
	}

	/**
	 * メールアドレスの設定
	 * @param account_mail メールアドレス
	 */
	public void setAccount_mail(String account_mail) {
		this.account_mail = account_mail;
	}

	/**
	 * 最終ログイン日時の取得
	 * @return 最終ログイン日時
	 */
	public Timestamp getAccount_dtLastLogin() {
		return this.account_dtLastLogin;
	}

	/**
	 * 最終ログイン日時の設定
	 * @param account_dtLastLogin 最終ログイン日時
	 */
	public void setAccount_dtLastLogin(Timestamp account_dtLastLogin) {
		this.account_dtLastLogin = account_dtLastLogin;
	}

	/**
	 * 登録日時の取得
	 * @return 登録日時
	 */
	public Timestamp getAccount_entryTime() {
		return this.account_entryTime;
	}

	/**
	 * 登録日時の設定
	 * @param account_entryTime 登録日時
	 */
	public void setAccount_entryTime(Timestamp account_entryTime) {
		this.account_entryTime = account_entryTime;
	}

	/**
	 * 登録者の取得
	 * @return 登録者
	 */
	public String getAccount_entryUser() {
		return this.account_entryUser;
	}

	/**
	 * 登録者の設定
	 * @param account_entryUser 登録者
	 */
	public void setAccount_entryUser(String account_entryUser) {
		this.account_entryUser = account_entryUser;
	}

	/**
	 * 変更日時の取得
	 * @return 変更日時
	 */
	public Timestamp getAccount_updateTime() {
		return this.account_updateTime;
	}

	/**
	 * 変更日時の設定
	 * @param account_updateTime 変更日時
	 */
	public void setAccount_updateTime(Timestamp account_updateTime) {
		this.account_updateTime = account_updateTime;
	}

	/**
	 * 変更者の取得
	 * @return 変更者
	 */
	public String getAccount_updateUser() {
		return this.account_updateUser;
	}

	/**
	 * 変更者の設定
	 * @param account_updateUser 変更者
	 */
	public void setAccount_updateUser(String account_updateUser) {
		this.account_updateUser = account_updateUser;
	}

	/**
	 * 担当コードの取得
	 * @return 担当コード
	 */
	public String getTantou_tantouCode() {
		return this.tantou_tantouCode;
	}

	/**
	 * 担当コードの設定
	 * @param tantou_tantouCode 担当コード
	 */
	public void setTantou_tantouCode(String tantou_tantouCode) {
		this.tantou_tantouCode = tantou_tantouCode;
	}

	/**
	 * 担当者名の取得
	 * @return 担当者名
	 */
	public String getTantou_tantouName() {
		return this.tantou_tantouName;
	}

	/**
	 * 担当者名の設定
	 * @param tantou_tantouName 担当者名
	 */
	public void setTantou_tantouName(String tantou_tantouName) {
		this.tantou_tantouName = tantou_tantouName;
	}

	/**
	 * 登録日時の取得
	 * @return 登録日時
	 */
	public Timestamp getTantou_entryTime() {
		return this.tantou_entryTime;
	}

	/**
	 * 登録日時の設定
	 * @param tantou_entryTime 登録日時
	 */
	public void setTantou_entryTime(Timestamp tantou_entryTime) {
		this.tantou_entryTime = tantou_entryTime;
	}

	/**
	 * 登録者の取得
	 * @return 登録者
	 */
	public String getTantou_entryUser() {
		return this.tantou_entryUser;
	}

	/**
	 * 登録者の設定
	 * @param tantou_entryUser 登録者
	 */
	public void setTantou_entryUser(String tantou_entryUser) {
		this.tantou_entryUser = tantou_entryUser;
	}

	/**
	 * 更新日時の取得
	 * @return 更新日時
	 */
	public Timestamp getTantou_updateTime() {
		return this.tantou_updateTime;
	}

	/**
	 * 更新日時の設定
	 * @param tantou_updateTime 更新日時
	 */
	public void setTantou_updateTime(Timestamp tantou_updateTime) {
		this.tantou_updateTime = tantou_updateTime;
	}

	/**
	 * 更新者の取得
	 * @return 更新者
	 */
	public String getTantou_updateUser() {
		return this.tantou_updateUser;
	}

	/**
	 * 更新者の設定
	 * @param tantou_updateUser 更新者
	 */
	public void setTantou_updateUser(String tantou_updateUser) {
		this.tantou_updateUser = tantou_updateUser;
	}

	/**
	 * 再発行パスワードの取得
	 * @return 再発行パスワード
	 */
	public String getAccount_rePasswd() {
		return this.account_rePasswd;
	}

	/**
	 * 再発行パスワードの設定
	 * @param account_rePasswd 再発行パスワード
	 */
	public void setAccount_rePasswd(String account_rePasswd) {
		this.account_rePasswd = account_rePasswd;
	}
}
