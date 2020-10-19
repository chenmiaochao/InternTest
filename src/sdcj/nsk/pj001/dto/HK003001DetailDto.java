package sdcj.nsk.pj001.dto;

import java.sql.Timestamp;

/**
* HK003001検索画面明細部の情報を保持するdtoクラス
* @author 近藤
*/
public class HK003001DetailDto {

	/**
	 * 担当者コード
	 */
	private String tantouCode = null;

	/**
	 * 担当者名
	 */
	private String tantouName = null;

	/**
	 * 変更日時
	 */
	private Timestamp updateTime = null;

	/**
	 * コンストラクタ
	 */
	public HK003001DetailDto() {

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
