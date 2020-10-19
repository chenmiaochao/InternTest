package sdcj.nsk.pj001.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author nguyen.hungminh
 * @version 1.0
 * @implNote バリデーション実装
 */
public class ValidateUtil {
	/**
	 * @author nguyen.hungminh
	 * @param phase
	 * @param maxLength
	 * @return boolean
	 * @implNote 文字の最大長チェック
	 */
	public static boolean checkMaxLength(String phase, int maxLength) {
		if (phase.isBlank() || phase.isEmpty()) {
			return true;
		}
		int phaseLength = phase.length();
		if (phaseLength > maxLength) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @author nguyen.hungminh
	 * @param phase
	 * @param minLength
	 * @return boolean
	 * @implNote 文字のmin長さチェック
	 */
	public static boolean checkMinLength(String phase, int minLength) {
		if (phase.isBlank() || phase.isEmpty()) {
			return true;
		}
		int phaseLength = phase.length();
		if (phaseLength < minLength) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @author nguyen.hungminh
	 * @param phase
	 * @return boolean
	 * @implNote 半角文字チェック
	 */
	public static boolean checkHalfWidth(String phase) {
		if (phase.isBlank() || phase.isEmpty() || phase == null) {
			return true;
		}
		Pattern pattern = Pattern.compile("^[0-9a-zA-Z]+$");
		Matcher matcher = pattern.matcher(phase);
		boolean isCheck = matcher.matches();
		return isCheck;
	}

	/**
	 * @author nguyen.hungminh
	 * @param phase
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean checkNumberRange(String phase, long min, long max) {
		if (!checkHalfWidth(phase)) {
			return false;
		}
		long number;
		try {
			number = Long.parseLong(phase);
		} catch (Exception ex) {
			return false;
		}
		if (number < max && number > min) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @author nguyen.hungminh
	 * @param phase
	 * @return
	 */
	public static boolean checkDate(String phase) {
		if (phase == null || phase.isBlank()) {
			return false;

		} else {
			DateTimeFormatter formatter = new DateTimeFormatterBuilder()
					.appendPattern("uuuuMMdd")
					.toFormatter()
					.withChronology(IsoChronology.INSTANCE)
					.withResolverStyle(ResolverStyle.STRICT);
			try {
				LocalDate date = LocalDate.parse(phase, formatter);
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}

	}

	/**
	 * 半角数字チェック
	 * @author 近藤
	 * @param phase チェックする文字列
	 * @return boolean
	 */
	public static boolean checkHalfWidthDigit(String phase) {

		if (phase.isBlank() || phase.isEmpty() || phase == null) {
			return true;
		}
		Pattern pattern = Pattern.compile("^[0-9]+$");
		Matcher matcher = pattern.matcher(phase);
		return matcher.matches();
	}

	/**
	 * 日付範囲チェック
	 * @author 近藤
	 * @param date_min チェックする下限日付
	 * @param date_max チェックする上限日付
	 * @return boolean
	 * @throws ParseException 引数が日付に変換できない値
	 */
	public static boolean checkDateValid(String date_min, String date_max) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false);
		var uridateMin = sdf.parse(date_min);
		var uridateMax = sdf.parse(date_max);
		return uridateMax.after(uridateMin);
	}

	/**
	 * @author nguyen.hungminh
	 * @param input
	 * @return 商品コード重複有無チェック,true = 重複なし、false = あり
	 */
	public static boolean checkDuplicateUsingSet(List<String> listContainingDuplicates) {

		final Set<String> set1 = new HashSet<String>();
		listContainingDuplicates.remove(null);

		for (String yourInt : listContainingDuplicates) {
			System.out.println("List items:" + yourInt);
			if (yourInt != null ) {
				if (!set1.add(yourInt) && !yourInt.equals("")) {
					//System.out.println("Duplicate check入り");
					return false;
				}
			}
		}
		return true;
	}
}