package sdcj.nsk.pj001.dto;

public class CM005001MeisaiDto extends UridenJViewDto {

	/**
	 * 得意先コードごとの金額合計
	 */
	private String kingakuGoukei = null;

	/**
	 * コンストラクタ
	 */
	public CM005001MeisaiDto() {
		super();
	}

	/**
	 * 得意先コードごとの金額合計の取得
	 * @return 得意先コードごとの金額合計
	 */
	public String getKingakuGoukei() {
		return kingakuGoukei;
	}

	/**
	 * 得意先コードごとの金額合計の設定
	 * @param kingakuGoukei 得意先コードごとの金額合計
	 */
	public void setKingakuGoukei(String kingakuGoukei) {
		this.kingakuGoukei = kingakuGoukei;
	}
}
