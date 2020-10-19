package sdcj.nsk.pj001.dto;

import java.sql.Timestamp;

/**
* 検索画面明細部の情報を保持するdtoクラス
* @author 近藤
*/
public class MM001001DetailDto {

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
	 * 更新日
	 */
	private Timestamp updateTime = null;

	/**
	 * コンストラクタ
	 */
	public MM001001DetailDto() {
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
	 * 更新日の取得
	 * @return 更新日
	 */
	public Timestamp getUpdateTime() {

		return this.updateTime;

	}

	/**
	 * 更新日の設定
	 * @param updateTime 更新日
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
