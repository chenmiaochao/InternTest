package sdcj.nsk.pj001.dto;

import java.sql.Timestamp;

/**
* 得意先マスタテーブルの情報を保持するdtoクラス
* @author 近藤
*/
public class TokuiTableDto {

	/**
	 * 得意先コード
	 */
	private String tokuiCode = null;

	/**
	 * 得意先名
	 */
	private String tokuiName = null;

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
	 * コンストラクタ
	 */
	public TokuiTableDto() {
		super();
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
	 * 得意先名の取得
	 * @return 得意先名
	 */
	public String getTokuiName() {
		return this.tokuiName;
	}

	/**
	 * 得意先名の設定
	 * @param tokuiName 得意先名
	 */
	public void setTokuiName(String tokuiName) {
		this.tokuiName = tokuiName;
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
}
