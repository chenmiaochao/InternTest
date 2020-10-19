package sdcj.nsk.pj001.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sdcj.nsk.pj001.dbUtils.DBManager;
import sdcj.nsk.pj001.dto.TokuiTableDto;

/**
 *
 * @author nguyen.hungminh
 *
 */
public class TokuiTableDao {

	/**
	 * @author nguyen.hungminh
	 * @param tokuiCode
	 * @return TokuiTableDto
	 */
	public static TokuiTableDto selectByTokuiCode(String tokuiCode) {
		//SQL文
		String sql = "SELECT tokui_name FROM M_TOKUI WHERE tokui_code = ?";
		TokuiTableDto dto = new TokuiTableDto();
		int size = 0;
		try {
			//DBに接続
			Connection con = DBManager.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, tokuiCode);
			ResultSet rs = psm.executeQuery();

			if (rs.next()) {
				dto.setTokuiName(rs.getString("tokui_name"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return dto;
	}

	/**
	 * @author nguyen.hungminh
	 * @param startCode
	 * @param endCode
	 * @param tokui_name
	 * @return int
	 * @implNote 条件取得件数
	 */
	public static int countByCondition(String startCode, String endCode, String tokui_name) {
		//SQL文
		String sql = "SELECT " +
				"        COUNT(tokui_code) " +
				"FROM " +
				"        ( " +
				"            SELECT " +
				"                    ROW_NUMBER() OVER(ORDER BY tokui_code) AS GYO_NO, TOKUI_CODE " +
				"            FROM " +
				"                    M_TOKUI " +
				"            WHERE " +
				"                    1=1" +
				"                AND (tokui_code >= ?" +
				"                AND tokui_code <= ?) " +
				"                AND tokui_name LIKE ?" + //Test sua lai sau
				"            ORDER BY tokui_code " +
				"        ) SUB";
		int size = 0;
		try {
			//DB接続メソッド呼び出し
			Connection con = DBManager.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, startCode);
			psm.setString(2, endCode);
			psm.setString(3, '%' + tokui_name + '%');
			ResultSet rs = psm.executeQuery();

			if (rs.next()) {
				size = rs.getInt(1);
			}
			//件数が１００より大きい場合、１００にする
			if (size > 100) {
				size = 100;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		return size;
	}

	/**
	 * @author nguyen.hungminh
	 * @param startCode
	 * @param endCode
	 * @param tokui_name
	 * @param page
	 * @return List<TokuiTableDto>
	 */
	public static List<TokuiTableDto> selectByCondition(String startCode, String endCode, String tokui_name, int page) {
		List<TokuiTableDto> list = new ArrayList<TokuiTableDto>();
		int startRecord = 0;
		int endRecord = 0;
		/**
		 * 選択ページ目によって適切な行を設定する
		 */
		if (page == 1) {
			startRecord = 1;
			endRecord = 10;
		} else if (page == 10) {
			startRecord = 91;
			endRecord = 100;
		} else {
			endRecord = page * 10;
			startRecord = endRecord - 9;
		}

		//SQL文
		String sql = "SELECT " +
				"    * " +
				"FROM " +
				"    ( " +
				"        SELECT " +
				"            row_number() OVER (ORDER BY tokui_code) AS GYO_NO, " +
				"            M_TOKUI.*  " +
				"        FROM " +
				"            M_TOKUI  " +
				"        WHERE " +
				"        ( " +
				"                tokui_code >= ? " +
				"            AND tokui_code <= ? " +
				"        ) " +
				"        AND tokui_name LIKE ?  " + //Chuyen tu OR sang AND sau khi test xong
				"        ORDER BY " +
				"            tokui_code " +
				"    ) AS subResult " +
				"WHERE " +
				"    GYO_NO  >= ? " +
				"AND GYO_NO <= ?";
		try {
			//DB接続設定
			Connection con = DBManager.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, startCode);
			psm.setString(2, endCode);
			psm.setString(3, '%' + tokui_name + '%');
			psm.setInt(4, startRecord);
			psm.setInt(5, endRecord);
			ResultSet rs = psm.executeQuery();
			//DBに取得したデータをTokuiTableDtoに格納
			while (rs.next()) {
				TokuiTableDto tokuiDto = new TokuiTableDto();
				tokuiDto.setTokuiCode(rs.getString("tokui_code"));
				tokuiDto.setTokuiName(rs.getString("tokui_name"));

				list.add(tokuiDto);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return list;
	}

}
