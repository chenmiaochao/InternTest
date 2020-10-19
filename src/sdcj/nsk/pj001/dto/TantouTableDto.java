package sdcj.nsk.pj001.dto;

import java.sql.Timestamp;

/**
* 担当者マスタテーブルの情報を保持するdtoクラス
* @author 近藤
*/
public class TantouTableDto {

	/**
	 * 担当者コード
	 */
	private String tantouCode = null;

	/**
	 * 担当者名
	 */
	private String tantouName = null;

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
	public TantouTableDto() {
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
	 * 担当者名の取得
	 * @return 担当者名
	 */
	public String getTantouName() {
		return this.tantouName;
	}

	/**
	 * 担当者名の設定
	 * @param tantouName 担当者名
	 */
	public void setTantouName(String tantouName) {
		this.tantouName = tantouName;
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
