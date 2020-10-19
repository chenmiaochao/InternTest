package sdcj.nsk.pj001.dto;

import java.sql.Timestamp;

/**
* 検索画面明細部の情報を保持するdtoクラス
* @author 近藤
*/
public class UA001001DetailDto {

	/**
	 * 伝票番号
	 */
	private String denNo = null;

	/**
	 * 売上日
	 */
	private String uriDate = null;

	/**
	 * 得意先名
	 */
	private String tokuiName = null;

	/**
	 * 担当者名
	 */
	private String tantouName = null;

	/**
	 * 商品数量
	 */
	private String suryo = null;

	/**
	 * 金額合計
	 */
	private String goukei = null;

	/**
	 * 変更日時
	 */
	private Timestamp updateTime = null;

	/**
	 * コンストラクタ
	 */
	public UA001001DetailDto() {
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
	 * 商品数量の取得
	 * @return 商品数量
	 */
	public String getSuryo() {
		return this.suryo;
	}

	/**
	 * 商品数量の設定
	 * @param suryo 商品数量
	 */
	public void setSuryo(String suryo) {
		this.suryo = suryo;
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
