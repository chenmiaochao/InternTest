package sdcj.nsk.pj001.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author 近藤
 */
public class CsvUtil {

	/**
	 * パラメータで指定したコードに対応するCSVファイル名を取得する。
	 * @param csvId
	 * @return CSVファイル名
	 * @author 近藤
	 */
	public static String getCsvName(String csvId) {

		try {
			// validateMsg.propertiesの読み込み
			ResourceBundle bundle = ResourceBundle.getBundle("csvId");

			//エラーIDからエラーメッセージを取得・リターン
			return bundle.getString(csvId);
		} catch (MissingResourceException e) {
			e.printStackTrace();
			return null;
		}
	}
}
