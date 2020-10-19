package sdcj.nsk.pj001.dto;

import java.sql.Timestamp;

/**
* 売上伝票ヘッダの情報を保持するdtoクラス
* @author 近藤
*/
public class UridenHTableDto {

	/**
	 * 伝票番号
	 */
	private String denNo = null;

	/**
	 * 売上日
	 */
	private String uriDate = null;

	/**
	 * 得意先コード
	 */
	private String tokuiCode = null;

	/**
	 * 担当者コード
	 */
	private String tantouCode = null;

	/**
	 * 金額合計
	 */
	private String goukei = null;

	/**
	 * 備考
	 */
	private String memo = null;

	/**
	 * 登録者
	 */
	private String entryUser = null;

	/**
	 * 登録日時
	 */
	private Timestamp entryTime = null;

	/**
	 * 変更者
	 */
	private String updateUser = null;

	/**
	 * 変更日時
	 */
	private Timestamp updateTime = null;

	/**
	 * コンストラクタ
	 */
	public UridenHTableDto() {
		super();
	}

	/**
	 * 伝票番号の取得
	 * @return 伝票番号
	 */
	public String getDenNo() {
		return this.denNo;
	}

	/**
	 * 伝票番号の設定
	 * @param denNo 伝票番号
	 */
	public void setDenNo(String denNo) {
		this.denNo = denNo;
	}

	/**
	 * 売上日の取得
	 * @return 売上日
	 */
	public String getUriDate() {
		return this.uriDate;
	}

	/**
	 * 売上日の設定
	 * @param uriDate 売上日
	 */
	public void setUriDate(String uriDate) {
		this.uriDate = uriDate;
	}

	/**
	 * 得意先コードの取得
	 * @return 得意先コード
	 */
	public String getTokuiCode() {
		return this.tokuiCode;
	}

	/**
	 * 得意先コードの設定
	 * @param tokuiCode 得意先コード
	 */
	public void setTokuiCode(String tokuiCode) {
		this.tokuiCode = tokuiCode;
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
	 * 金額合計の取得
	 * @return 金額合計
	 */
	public String getGoukei() {
		return this.goukei;
	}

	/**
	 * 金額合計の設定
	 * @param goukei 金額合計
	 */
	public void setGoukei(String goukei) {
		this.goukei = goukei;
	}

	/**
	 * 備考の取得
	 * @return 備考
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 * 備考の設定
	 * @param memo 備考
	 */
	public void setMemo(String memo) {
		this.memo = memo;
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
}
