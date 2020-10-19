/**
 *
 */
package sdcj.nsk.pj001.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sdcj.nsk.pj001.dbUtils.DBManager;
import sdcj.nsk.pj001.dto.TantouTableDto;

/**
 * @author nguyen.hungminh
 *
 */
public class TantouTableDao {

	public static List<TantouTableDto> selectByCondition (String startCode,String endCode,String tantou_name, int page) {
			List<TantouTableDto> list = new ArrayList<TantouTableDto>();
			int startRecord = 0;
			int endRecord = 0;
			/**
			 * 選択ページ目によって適切な行を設定する
			 */
			if (page == 1) {
				startRecord = 1;
				endRecord = 10;
			}else if(page == 10) {
				startRecord = 91;
				endRecord = 100;
			}
			else {
				endRecord = page *10;
				startRecord = endRecord - 9;
			}

			//SQL文
			String sql = "SELECT " +
					"    * " +
					"FROM " +
					"    ( " +
					"        SELECT " +
					"            row_number() OVER (ORDER BY tantou_code) AS GYO_NO, " +
					"            M_TANTOU.*  " +
					"        FROM " +
					"            M_TANTOU  " +
					"        WHERE " +
					"        ( " +
					"                tantou_code >= ? " +
					"            AND tantou_code <= ? " +
					"        ) " +
					"        AND tantou_name LIKE ?  " +
					"        ORDER BY " +
					"            tantou_code " +
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
				psm.setString(3, '%'+ tantou_name + '%');
				psm.setInt(4, startRecord);
				psm.setInt(5, endRecord);
				ResultSet rs = psm.executeQuery();
				//DBに取得したデータをHK001001DetailDtoに格納
				while (rs.next()) {
					TantouTableDto detailDto = new TantouTableDto();
					detailDto.setTantouCode(rs.getString("tantou_code"));
					detailDto.setTantouName(rs.getString("tantou_name"));
					detailDto.setEntryTime(rs.getTimestamp("entrytime"));
					detailDto.setEntryUser(rs.getString("entryuser"));

					detailDto.setUpdateTime(rs.getTimestamp("updatetime"));
					detailDto.setUpdateUser(rs.getString("updateuser"));
					list.add(detailDto);
				}
			}catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
			return list;
	}

	public static int countByCondition(String startCode,String endCode,String tantou_name) {
		//SQL文
				String sql = "SELECT " +
						"        COUNT(tantou_code) " +
						"FROM " +
						"        ( " +
						"            SELECT " +
						"                    ROW_NUMBER() OVER(ORDER BY tantou_code) AS GYO_NO, tantou_code " +
						"            FROM " +
						"                    M_TANTOU " +
						"            WHERE " +
						"                    1=1" +
						"                AND (tantou_code >= ?" +
						"                AND tantou_code <= ?) " +
						"                AND tantou_name LIKE ?" +
						"            ORDER BY tantou_code " +
						"        ) SUB";
				int size = 0;
				try {
					//DBに接続
					Connection con = DBManager.makeConnection();
					PreparedStatement psm = con.prepareStatement(sql);
					psm.setString(1, startCode);
					psm.setString(2, endCode);
					psm.setString(3, '%'+ tantou_name + '%');
					ResultSet rs = psm.executeQuery();

					if (rs.next()) {
						size = rs.getInt(1);
					}
					//件数が１００より大きい場合、１００にする
					if (size > 100) {
						size = 100;
					}
				}catch (Exception ex) {
					ex.printStackTrace();
					return 0;
				}
				return size;
	}

	/**
	 * @author nguyen.hungminh
	 * @param tantouCode
	 * @return TantouTableDto
	 */
	public static TantouTableDto selectByTantouCode(String tantouCode) {
		//SQL文
		String sql = "SELECT tantou_name FROM M_TANTOU WHERE tantou_code = ?";
		TantouTableDto dto = new TantouTableDto();
		int size = 0;
		try {
			//DBに接続
			Connection con = DBManager.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, tantouCode);
			ResultSet rs = psm.executeQuery();

			if (rs.next()) {
				dto.setTantouName(rs.getString("tantou_name"));
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return dto;
	}
}
