package sdcj.nsk.pj001.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import sdcj.nsk.pj001.dbUtils.DBManager;
import sdcj.nsk.pj001.dto.UridenMTableDto;

public class UridenMTableDao {

	/**
	 * @author shutsuno
	 * @param denNo
	 * @return list(MTableDto)
	 * @thows Exception
	 * @implNote 伝票Noを取得して伝票明細データを返すメソッド
	 */
	public static List<UridenMTableDto> countByDenNo(String denNo) {

		List<UridenMTableDto> list = new ArrayList<UridenMTableDto>();

		String sql = "SELECT " +
				"        SHOHIN_CODE, SHOHIN_NAME, TANKA, SURYO, KINGAKU " +
				"FROM " +
				"        T_URIDEN_M " +
				"WHERE " +
				"        DENNO = ?";

		try {

			//DB接続
			Connection con = DBManager.makeConnection();

			//SQL文作成
			PreparedStatement psm = con.prepareStatement(sql);

			//伝票Noをバインド
			psm.setString(1, denNo);

			//実行
			ResultSet rs = psm.executeQuery();

			//UridenMTableDtoに格納
			while (rs.next()) {
				UridenMTableDto MTableDto = new UridenMTableDto();

				MTableDto.setShohinCode(rs.getString("SHOHIN_CODE"));
				MTableDto.setSyohinName(rs.getString("SHOHIN_NAME"));
				MTableDto.setTanka(rs.getString("TANKA"));
				MTableDto.setSuryo(rs.getString("SURYO"));
				MTableDto.setKingaku(rs.getString("KINGAKU"));

				list.add(MTableDto);

			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}

	/**
	 * @author shutsuno
	 * @param denNo
	 * @return 1
	 * @thows Exception
	 * @implNote 伝票Noを取得して伝票明細データを削除するメソッド
	 */
	public static int deleteByDenNo(String denNo) {

		String sql = "DELETE " +
				"FROM " +
				"        T_URIDEN_M " +
				"WHERE " +
				"        DENNO = ? ";

		try {

			//DB接続
			Connection con = DBManager.makeConnection();

			//SQL文作成
			PreparedStatement psm = con.prepareStatement(sql);

			//伝票Noをバインド
			psm.setString(1, denNo);

			//実行
			psm.executeUpdate();

		}catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}


		return 1;
	}
	/**
	 * @author nguyen.hungminh
	 * @param denNo
	 * @param meisaiNo
	 * @param shohinCode
	 * @param shohinName
	 * @param suryo
	 * @param tanka
	 * @param kingaku
	 * @param entryTime
	 * @param entryUser
	 * @param updateTime
	 * @param updateUser
	 * @return
	 * @throws Exception
	 */
	public static int insert(String denNo, int meisaiNo, String shohinCode, String shohinName, int suryo, int tanka,
			int kingaku, Timestamp entryTime, String entryUser, Timestamp updateTime, String updateUser) throws Exception {

		String sql = "INSERT INTO " +
				"        T_URIDEN_M ( DENNO, MEISAI_NO, SHOHIN_CODE, SHOHIN_NAME, SURYO, TANKA, KINGAKU, ENTRYTIME, ENTRYUSER, UPDATETIME, UPDATEUSER) " +
				"VALUES ( ?, ?, ?, ?, ?, ?, ?, TO_TIMESTAMP(?, 'yyyy-mm-dd HH24:MI:SS'), ?, TO_TIMESTAMP(?, 'yyyy-mm-dd HH24:MI:SS'), ?)";
		Connection con = DBManager.makeConnection();
		try {

			//DB接続
			//SQL文作成
			PreparedStatement psm = con.prepareStatement(sql);

			//伝票Noと更新時間をバインド
			psm.setString(1, denNo);
			psm.setInt(2, meisaiNo);
			psm.setString(3, shohinCode);
			psm.setString(4, shohinName);
			psm.setInt(5, suryo);
			psm.setInt(6, tanka);
			psm.setInt(7, kingaku);
			psm.setTimestamp(8, entryTime);
			psm.setString(9, entryUser);
			psm.setTimestamp(10, updateTime);
			psm.setString(11, updateUser);

			//実行
			int temp = psm.executeUpdate();
			psm.close();
			con.close();
			return temp;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}

	}

}
