package sdcj.nsk.pj001.dto;

public class CM005001ShukeiDto extends UridenJViewDto {

	/**
	 * 年月
	 */
	private String nengetsu = null;

	/**
	 * 得意先コードにおける年月ごとの金額合計
	 */
	private String shukeiNengetsu = null;

	/**
	 * 対象年月（得意先コードごと）の合計金額
	 */
	private String kingakuGoukei = null;

	/**
	 * コンストラクタ
	 */
	public CM005001ShukeiDto() {
		super();
	}

	/**
	 * 年月の取得
	 * @return 年月
	 */
	public String getNengetsu() {
		return nengetsu;
	}

	/**
	 * 年月の設定
	 * @param nengetsu
	 */
	public void setNengetsu(String nengetsu) {
		this.nengetsu = nengetsu;
	}

	/**
	 * 得意先コードにおける年月ごとの金額合計の取得
	 * @return 得意先コードにおける年月ごとの金額合計
	 */
	public String getShukeiNengetsu() {
		return shukeiNengetsu;
	}

	/**
	 * 得意先コードにおける年月ごとの金額合計の設定
	 * @param shukeiNengetsu
	 */
	public void setShukeiNengetsu(String shukeiNengetsu) {
		this.shukeiNengetsu = shukeiNengetsu;
	}

	/**
	 * 対象年月（得意先コードごと）の合計金額の取得
	 * @return 対象年月（得意先コードごと）の合計金額
	 */
	public String getKingakuGoukei() {
		return kingakuGoukei;
	}

	/**
	 * 対象年月（得意先コードごと）の合計金額の設定
	 * @param kingakuGoukei
	 */
	public void setKingakuGoukei(String kingakuGoukei) {
		this.kingakuGoukei = kingakuGoukei;
	}

}
