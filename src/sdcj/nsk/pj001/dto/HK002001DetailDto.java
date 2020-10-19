package sdcj.nsk.pj001.dto;

import java.sql.Timestamp;

/**
* HK002001検索画面明細部の情報を保持するdtoクラス
* @author 近藤
*/
public class HK002001DetailDto {

	/**
	 * 得意先コード
	 */
	private String tokuiCode = null;

	/**
	 * 得意先名
	 */
	private String tokuiName = null;

	/**
	 * 変更日時
	 */
	private Timestamp updateTime = null;

	/**
	 * コンストラクタ
	 */
	public HK002001DetailDto() {
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
