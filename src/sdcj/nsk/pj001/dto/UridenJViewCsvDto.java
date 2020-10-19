package sdcj.nsk.pj001.dto;

/**
* UridenJViewDtoを継承したdtoクラス
* @author 近藤
*/
public class UridenJViewCsvDto extends UridenJViewDto {

	/**
	 * 年月
	 */
	private int nengetsu = 0;

	/**
	 * コンストラクタ
	 */
	public UridenJViewCsvDto() {
		super();
	}

	/**
	 * 年月の取得
	 * @return 年月
	 */
	public int getNengetsu() {
		return this.nengetsu;
	}

	/**
	 * 年月の設定
	 * @param nengetsu 年月
	 */
	public void setNengetsu(int nengetsu) {
		this.nengetsu = nengetsu;
	}
}
