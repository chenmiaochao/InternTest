package sdcj.nsk.pj001.dbUtils;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author nguyen.hungminh
 * @version 1.0
 *
 */
public class DBManager {

	/**
	 *
	 * @return Connection
	 * @throws Exception
	 * @author nguyen.hungminh
	 */
	public static Connection makeConnection() throws Exception {
		InitialContext cxt = new InitialContext();
		//TOMCATサーバーでのcontext.xmlファイルを読み込み「jdbc/postgres」を検索する
		//見つかったら必要な要素を格納する
		DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/postgres" );

		if ( ds == null ) {
		   throw new Exception("Data source not found!");
		}
		Connection conn = null;
		conn = ds.getConnection();
		return conn;
	}
}