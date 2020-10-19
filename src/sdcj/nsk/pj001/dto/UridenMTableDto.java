package sdcj.nsk.pj001.dto;

import java.sql.Timestamp;

/**
* 売上伝票情報Viewの情報を保持するdtoクラス
* @author 近藤
*/
public class UridenMTableDto {

	/**
	 * 伝票番号
	 */
	private String denNo = null;

	/**
	 * 明細番号
	 */
	private String meisaiNo = null;

	/**
	 * 商品コード
	 */
	private String shohinCode = null;

	/**
	 * 商品名
	 */
	private String syohinName = null;

	/**
	 * 単価
	 */
	private String tanka = null;

	/**
	 * 数量
	 */
	private String suryo = null;

	/**
	 * 金額
	 */
	private String kingaku = null;

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
	public UridenMTableDto() {
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
	 * 明細番号の取得
	 * @return 明細番号
	 */
	public String getMeisaiNo() {
		return this.meisaiNo;
	}

	/**
	 * 明細番号の設定
	 * @param meisaiNo 明細番号
	 */
	public void setMeisaiNo(String meisaiNo) {
		this.meisaiNo = meisaiNo;
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
	public String getSyohinName() {
		return this.syohinName;
	}

	/**
	 * 商品名の設定
	 * @param syohinName 商品名
	 */
	public void setSyohinName(String syohinName) {
		this.syohinName = syohinName;
	}

	/**
	 * 単価の取得
	 * @return 単価
	 */
	public String getTanka() {
		return this.tanka;
	}

	/**
	 * 単価の設定
	 * @param tanka 単価
	 */
	public void setTanka(String tanka) {
		this.tanka = tanka;
	}

	/**
	 * 数量の取得
	 * @return 数量
	 */
	public String getSuryo() {
		return this.suryo;
	}

	/**
	 * 数量の設定
	 * @param suryo 数量
	 */
	public void setSuryo(String suryo) {
		this.suryo = suryo;
	}

	/**
	 * 金額の取得
	 * @return 金額
	 */
	public String getKingaku() {
		return this.kingaku;
	}

	/**
	 * 金額の設定
	 * @param kingaku 金額
	 */
	public void setKingaku(String kingaku) {
		this.kingaku = kingaku;
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
