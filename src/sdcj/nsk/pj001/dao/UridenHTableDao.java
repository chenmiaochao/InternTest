package sdcj.nsk.pj001.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import sdcj.nsk.pj001.dbUtils.DBManager;
import sdcj.nsk.pj001.dto.UridenHTableDto;

public class UridenHTableDao {

	/**
	 * @author shutsuno
	 * @param denNo
	 * @return list(HTableDto)
	 * @thows Exception
	 * @implNote 伝票Noを取得して伝票データを返すメソッド
	 */
	public static List<UridenHTableDto> selectByDenNo(String denNo) {

		List<UridenHTableDto> list = new ArrayList<UridenHTableDto>();

		String sql = "SELECT " +
				"        DENNO, URI_DATE, TOKUI_CODE, TANTOU_CODE, GOUKEI, MEMO, ENTRYTIME, ENTRYUSER, UPDATETIME, UPDATEUSER " +
				"FROM " +
				"        T_URIDEN_H " +
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

			//UridenHTableDtoに格納
			while (rs.next()) {
				UridenHTableDto HTableDto = new UridenHTableDto();

				HTableDto.setDenNo(rs.getString("DENNO"));
				HTableDto.setUriDate(rs.getString("URI_DATE"));
				HTableDto.setTokuiCode(rs.getString("TOKUI_CODE"));
				HTableDto.setTantouCode(rs.getString("TANTOU_CODE"));
				HTableDto.setGoukei(rs.getString("GOUKEI"));
				HTableDto.setMemo(rs.getString("MEMO"));
				HTableDto.setEntryTime(rs.getTimestamp("ENTRYTIME"));
				HTableDto.setEntryUser(rs.getString("ENTRYUSER"));
				HTableDto.setUpdateTime(rs.getTimestamp("UPDATETIME"));
				HTableDto.setUpdateUser(rs.getString("UPDATEUSER"));

				list.add(HTableDto);

			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}

	/**
	 * @author shutsuno
	 * @param denNo
	 * @param updateTime
	 * @return 1
	 * @thows Exception
	 * @implNote 伝票Noと更新時間を取得して伝票データを削除するメソッド
	 */
	public static int deleteByCondition(String denNo, Timestamp updateTime) {

		String sql = "DELETE " +
				"FROM " +
				"        T_URIDEN_H " +
				"WHERE " +
				"        DENNO = ? " +
				"AND " +
				"        UPDATETIME = TO_DATE(?, 'yyyy-mm-dd')";

		try {

			//DB接続
			Connection con = DBManager.makeConnection();

			//SQL文作成
			PreparedStatement psm = con.prepareStatement(sql);

			//伝票Noと更新時間をバインド
			psm.setString(1, denNo);
			psm.setTimestamp(2, updateTime);

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
	 * @param uriDate
	 * @param tokuiCode
	 * @param tantouCode
	 * @param goukei
	 * @param biko
	 * @param entryTime
	 * @param entryUser
	 * @param updateTime
	 * @param updateUser
	 * @return
	 * @throws Exception
	 */
	public static int insert(String denNo, String uriDate, String tokuiCode, String tantouCode, int goukei, String biko,
			Timestamp entryTime, String entryUser, Timestamp updateTime, String updateUser) throws Exception {

		String sql = "INSERT INTO" +
				"       T_URIDEN_H ( DENNO, URI_DATE, TOKUI_CODE, TANTOU_CODE, GOUKEI, MEMO, ENTRYTIME, ENTRYUSER, UPDATETIME, UPDATEUSER) "
				+
				"VALUES " +
				"       ( ?, ?, ?, ?, ?, ?, TO_DATE(?, 'yyyy-mm-dd'), ?, TO_DATE(?, 'yyyy-mm-dd'), ?)";
		Connection con = DBManager.makeConnection();
		try {
			//DB接続
			//Transactionのため
			//SQL文作成
			PreparedStatement psm = con.prepareStatement(sql);

			//伝票Noと更新時間をバインド
			psm.setString(1, denNo);
			psm.setString(2, uriDate);
			psm.setString(3, tokuiCode);
			psm.setString(4, tantouCode);
			psm.setInt(5, goukei);
			psm.setString(6, biko);
			psm.setTimestamp(7, entryTime);
			psm.setString(8, entryUser);
			psm.setTimestamp(9, updateTime);
			psm.setString(10, updateUser);

			//実行
			int count = psm.executeUpdate();
			psm.close();
			con.close();
			return count;
		} catch (SQLException ex) {
			ex.printStackTrace();

			System.out.println("Rollback");
			con.rollback();

			return 0;
		}
	}
	/**
	 * @author nguyen.hungminh
	 * @param denNo
	 * @param uriDate
	 * @param tokuiCode
	 * @param tantouCode
	 * @param goukei
	 * @param biko
	 * @param updateTime
	 * @param updateUser
	 * @return
	 * @throws Exception
	 */
	public static int updateByDenNo(String denNo, String uriDate, String tokuiCode, String tantouCode, int goukei, String biko,
			Timestamp updateTime, String updateUser) throws Exception {
		String sql = "update t_uriden_h " +
				"set uri_date = ?," +
				"    tokui_code = ?," +
				"    tantou_code = ?," +
				"    goukei = ?," +
				"    memo = ?," +
				"    updatetime = ?," +
				"    updateuser = ? " +
				"where denno = ?";
		Connection con = DBManager.makeConnection();
		try {
			//DB接続
			//Transactionのため
			//SQL文作成
			PreparedStatement psm = con.prepareStatement(sql);

			//伝票Noと更新時間をバインド

			psm.setString(1, uriDate);
			psm.setString(2, tokuiCode);
			psm.setString(3, tantouCode);
			psm.setInt(4, goukei);
			psm.setString(5, biko);
			psm.setTimestamp(6, updateTime);
			psm.setString(7, updateUser);
			psm.setString(8, denNo);

			//実行
			int count = psm.executeUpdate();
			psm.close();
			con.close();
			return count;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
	}

}
