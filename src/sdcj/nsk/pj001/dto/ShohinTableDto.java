package sdcj.nsk.pj001.dto;

import java.sql.Timestamp;

/**
* 商品マスタテーブルの情報を保持するdtoクラス
* @author 近藤
*/
public class ShohinTableDto {

	/**
	 * 商品コード
	 */
	private String shohinCode = null;

	/**
	 * 商品名
	 */
	private String shohinName = null;

	/**
	 * 商品単価
	 */
	private String tanka = null;

	/**
	 * 登録者
	 */
	private Timestamp entryTime = null;

	/**
	 * 登録日時
	 */
	private String entryUser = null;

	/**
	 * 変更者
	 */
	private Timestamp updateTime = null;

	/**
	 * 変更日時
	 */
	private String updateUser = null;

	/**
	 * コンストラクタ
	 */
	public ShohinTableDto() {
		super();
	}

	/**
	 * 商品コードの取得
	 * @return 商品コード
	 */
	public String getShohinCode() {
		return this.shohinCode;
	}

	/**
	 * 商品コードの設定
	 * @param shohinCode 商品コード
	 */
	public void setShohinCode(String shohinCode) {
		this.shohinCode = shohinCode;
	}

	/**
	 * 商品名の取得
	 * @return 商品名
	 */
	public String getShohinName() {
		return this.shohinName;
	}

	/**
	 * 商品名の設定
	 * @param shohinName 商品名
	 */
	public void setShohinName(String shohinName) {
		this.shohinName = shohinName;
	}

	/**
	 * 商品単価の取得
	 * @return 商品単価
	 */
	public String getTanka() {
		return this.tanka;
	}

	/**
	 * 商品単価の設定
	 * @param tanka 商品単価
	 */
	public void setTanka(String tanka) {
		this.tanka = tanka;
	}

	/**
	 * 登録者の取得
	 * @return 登録者
	 */
	public Timestamp getEntryTime() {
		return this.entryTime;
	}

	/**
	 * 登録者の設定
	 * @param entryTime 登録者
	 */
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}

	/**
	 * 登録日時の取得
	 * @return 登録日時
	 */
	public String getEntryUser() {
		return this.entryUser;
	}

	/**
	 * 登録日時の設定
	 * @param entryUser 登録日時
	 */
	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	/**
	 * 変更者の取得
	 * @return 変更者
	 */
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 変更者の設定
	 * @param updateTime 変更者
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 変更日時の取得
	 * @return 変更日時
	 */
	public String getUpdateUser() {
		return this.updateUser;
	}

	/**
	 * 変更日時の設定
	 * @param updateUser 変更日時
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}
