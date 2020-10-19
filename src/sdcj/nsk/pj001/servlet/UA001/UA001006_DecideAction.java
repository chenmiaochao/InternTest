package sdcj.nsk.pj001.servlet.UA001;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sdcj.nsk.pj001.dao.UridenHTableDao;
import sdcj.nsk.pj001.dao.UridenJViewDao;
import sdcj.nsk.pj001.dao.UridenMTableDao;
import sdcj.nsk.pj001.dbUtils.DBManager;
import sdcj.nsk.pj001.dto.AccountTableDto;
import sdcj.nsk.pj001.dto.UridenHTableDto;
import sdcj.nsk.pj001.dto.UridenJViewDto;
import sdcj.nsk.pj001.utils.MessageUtil;
import sdcj.nsk.pj001.utils.ValidateUtil;

/**
 * Servlet implementation class UA001006_DecideAction
 */
@WebServlet("/UA001006_DecideAction")
public class UA001006_DecideAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UA001006_DecideAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = "/jsp/ua001/ua001007.jsp";
		String returnPage = "/jsp/ua001/ua001006.jsp";
		HttpSession session = request.getSession();
		//var session = request.getSession();
		var loginUser = (AccountTableDto)session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getLoginId().isEmpty()) {
			// 未ログイン
			response.sendRedirect("jsp/cm001/cm001001.jsp");
			return;
		}
		String denNo = (String) session.getAttribute("DENNO");
		List<UridenJViewDto> meisaiList = (List<UridenJViewDto>) session.getAttribute("URIDENLIST");
		String tantouCode = (String) session.getAttribute("TANTOUCODE");
		String tokuiCode = (String) session.getAttribute("TOKUICODE");
		String biko = (String) session.getAttribute("BIKO");
		String uriageDate = (String) session.getAttribute("URIAGEDATE");
		Timestamp henkou_open = (Timestamp) session.getAttribute("HENKOUTIME");
		Timestamp update_time = null;

		try {
			int goukei = 0;
			update_time = UridenJViewDao.selectByDenNo(denNo).get(0).getUpdateTime_001();
			if (session.getAttribute("GOUKEI") == null || session.getAttribute("GOUKEI").equals("0")) {
				return;
			} else {
				goukei = (int) session.getAttribute("GOUKEI");
			}
			if (tantouCode == null || denNo == null || tokuiCode == null || uriageDate == null || biko == null
					|| tantouCode.isBlank() || denNo.isBlank() || tokuiCode.isBlank() || uriageDate.isBlank()
					|| biko.isBlank()) {
				meisaiList.get(0).setDenNo_001(denNo);
				meisaiList.get(0).setTantouCode_001(tantouCode);
				meisaiList.get(0).setTokuiCode_001(tokuiCode);
				meisaiList.get(0).setUriDate_001(uriageDate);
				meisaiList.get(0).setMemo_001(biko);
				session.setAttribute("URIDENLIST", meisaiList);
				String displayMsg[] = { "全項目" };
				String errMsg = MessageUtil.errorMessage("MSG008", displayMsg);
				request.setAttribute("ERRMSG", errMsg);
				url = returnPage;
				//test = false;
				return;
			}
			//System.out.println("BIKO:"+biko);
			/*
			必須チェック
			a)	必須チェック でエラーがある場合、以下の文字列を画面項目.エラーメッセージに編集する。
				エラーメッセージID：MSG008
			b)	最大長チェック でエラーがある場合、以下の文字列を画面項目.エラーメッセージに編集する。
				エラーメッセージID：MSG010
			c)	最小長チェック でエラーがある場合、以下の文字列を画面項目.エラーメッセージに編集する。
				エラーメッセージID：MSG010
			d)	半角数値チェック でエラーがある場合、以下の文字列を画面項目.エラーメッセージに編集する。
				エラーメッセージID：MSG010
			e)	範囲チェック でエラーがある場合、以下の文字列を画面項目.エラーメッセージに編集する。
				エラーメッセージID：MSG011
			f)	日付妥当性チェックでエラーがある場合、以下の文字列を画面項目エラーメッセージに編集する。
				エラーメッセージID：MSG015
			*/
			//System.out.println(!ValidateUtil.checkMinLength(denNo, 6));
			if ((!ValidateUtil.checkMinLength(denNo, 6)) || (!ValidateUtil.checkMaxLength(denNo, 6))
					|| (!ValidateUtil.checkHalfWidth(denNo))) {
				String displayMsg[] = { "売上伝票番号", "正確" };
				String errMsg = MessageUtil.errorMessage("MSG010", displayMsg);
				request.setAttribute("ERRMSG", errMsg);
				url = returnPage;
				return;
			}
			if (!ValidateUtil.checkNumberRange(denNo, 0, 999999)) {
				String displayMsg[] = { "売上伝票番号", "000001", "999999" };
				String errMsg = MessageUtil.errorMessage("MSG011", null);
				request.setAttribute("ERRMSG", errMsg);
				url = returnPage;
				return;
			}
			if (!ValidateUtil.checkMinLength(uriageDate, 8) || !ValidateUtil.checkMaxLength(uriageDate, 8)
					|| !ValidateUtil.checkHalfWidth(uriageDate) || !ValidateUtil.checkDate(uriageDate)) {
				String displayMsg[] = { "売上日付", "正確で" };
				String errMsg = MessageUtil.errorMessage("MSG015", displayMsg);
				request.setAttribute("ERRMSG", errMsg);
				url = returnPage;
				return;
			}
			if (!ValidateUtil.checkMaxLength(tokuiCode, 4) || !ValidateUtil.checkMaxLength(tantouCode, 4)
					|| !ValidateUtil.checkMaxLength(biko, 1000)) {
				String displayMsg[] = { "得意また担当コードまた備考欄", "正確" };
				String errMsg = MessageUtil.errorMessage("MSG010", displayMsg);
				request.setAttribute("ERRMSG", errMsg);
				url = returnPage;
				return;
			}

			//System.out.println(goukei);
			//Mode = 0: 登録
			//Mode = 2: 編集
			String mode = (String) session.getAttribute("MODE");
			if (meisaiList == null) {
				return;
			}
			if (mode.equals("0")) {

				List<String> tempCheckCode = new ArrayList<String>();
				for (int i = 0; i < 4; i++) {
					//System.out.println(meisaiList.get(i).getShohiCode_002());
					if (meisaiList.get(i).getShohiCode_002() != null) {
						tempCheckCode.add(meisaiList.get(i).getShohiCode_002());
					}
				}
				//新規登録
				//売上伝票番号が存在するかどうかの確認
				//check 売上伝票番号

				if (ValidateUtil.checkDuplicateUsingSet(tempCheckCode) == false) {
					//String displayMsg[] = { "商品コード" };
					String errMsg = MessageUtil.errorMessage("MSG015", null);
					request.setAttribute("ERRMSG", errMsg);
					url = returnPage;
					return;
				}
				List<UridenHTableDto> existList = UridenHTableDao.selectByDenNo(denNo);
				if (existList.isEmpty()) {
					//登録進んでる
					Connection con = DBManager.makeConnection();
					try {
						con.setAutoCommit(false);
						//System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));
						//TODO 後で変更 admin
						try {
							UridenHTableDao.insert(denNo, uriageDate, tokuiCode, tantouCode, goukei, biko,
									new java.sql.Timestamp(System.currentTimeMillis()), "admin",
									new java.sql.Timestamp(System.currentTimeMillis()), "admin");
						} catch (Exception ex) {
							ex.printStackTrace();
							String displayMsg[] = { "売上伝票ヘッダ", "新規登録" };
							String errMsg = MessageUtil.errorMessage("MSG006", displayMsg);
							request.setAttribute("ERRMSG", errMsg);
							url = returnPage;
						}
						try {
							for (int i = 0; i < meisaiList.size(); i++) {
								//System.out.println(meisaiList.get(i).getShohiCode_002());
								if (meisaiList.get(i).getShohiCode_002() == null) {
									return;
								} else if (meisaiList.get(i).getShohiCode_002() != null
										|| !meisaiList.get(i).getShohiCode_002().isBlank()) {
									//明細番号の作り方が不明
									//TODO currentLoginUserの変更

									UridenMTableDao.insert(denNo, i, meisaiList.get(i).getShohiCode_002(),
											meisaiList.get(i).getShohinName_002(),
											Integer.parseInt(meisaiList.get(i).getSuryo_002()),
											Integer.parseInt(meisaiList.get(i).getTanka_002()),
											Integer.parseInt(meisaiList.get(i).getKingaku_002()),
											new java.sql.Timestamp(System.currentTimeMillis()), "admin",
											new java.sql.Timestamp(System.currentTimeMillis()), "admin");
								}
							}
						} catch (Exception ex) {
							ex.printStackTrace();
							String displayMsg[] = { "売上伝票明細", "新規登録" };
							String errMsg = MessageUtil.errorMessage("MSG006", displayMsg);
							request.setAttribute("ERRMSG", errMsg);
							url = returnPage;
						}
						con.commit();
						session.removeAttribute("DENNO");
						session.removeAttribute("URIDENLIST");
						session.removeAttribute("TANTOUCODE");
						session.removeAttribute("TOKUICODE");
						session.removeAttribute("BIKO");
						session.removeAttribute("URIAGEDATE");
						session.removeAttribute("GOUKEI");
						session.removeAttribute("MODE");
					} catch (SQLException ex) {
						ex.printStackTrace();
						System.out.println("RollBack");
						String displayMsg[] = { "売上伝票ヘッダ", "新規登録" };
						String errMsg = MessageUtil.errorMessage("MSG006", displayMsg);
						request.setAttribute("ERRMSG", errMsg);
						url = returnPage;
						con.rollback();
					} finally {
						con.setAutoCommit(true);
						con.close();
					}

				} else {
					String displayMsg[] = { "伝票番号" };
					String errMsg = MessageUtil.errorMessage("MSG002", displayMsg);
					request.setAttribute("ERRMSG", errMsg);
					url = returnPage;
					return;
				}
			} else if (mode.equals("2")) {
				if (update_time != null && !henkou_open.equals(update_time)) {
					//編集モードの確認
					//排他処理実装
					String errMsg = MessageUtil.errorMessage("MSG004", null);
					request.setAttribute("ERRMSG", errMsg);
					url = returnPage;
					return;
				} else if (update_time == null) {
					//TODO: エラー見直し
					String errMsg = MessageUtil.errorMessage("MSG004", null);
					request.setAttribute("ERRMSG", errMsg);
					url = returnPage;
					return;
				}
				//編集モード・更新
				//その伝票番号が存在するかどうかの確認
				//存在しなかったらMSG004を出す
				if (UridenHTableDao.selectByDenNo(denNo) == null) {
					//error MSG004
					String errorMsg = MessageUtil.errorMessage("MSG004", null);
					request.setAttribute("ERROR", errorMsg);
					return;
				} else {
					List<String> tempCheckCode = new ArrayList<String>();
					for (int i = 0; i < 4; i++) {
						tempCheckCode.add(meisaiList.get(i).getShohiCode_002());
					}
					//新規登録
					//売上伝票番号が存在するかどうかの確認
					//check 売上伝票番号
					boolean checkDuplicate = ValidateUtil.checkDuplicateUsingSet(tempCheckCode);
					if (checkDuplicate == false) {
						String displayMsg[] = { "商品コード" };
						String errMsg = MessageUtil.errorMessage("MSG006", displayMsg);
						request.setAttribute("ERRMSG", errMsg);
						url = returnPage;
						return;
					}
					Connection con = DBManager.makeConnection();
					try {
						con.setAutoCommit(false);
						//System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));
						//TODO 後で変更 admin
						UridenHTableDao.updateByDenNo(denNo, uriageDate, tokuiCode, tantouCode, goukei, biko,
								new java.sql.Timestamp(System.currentTimeMillis()), "admin");
						//設計書では明細の更新メソッド存在しないため
						int result = UridenMTableDao.deleteByDenNo(denNo);
						for (int i = 0; i < meisaiList.size(); i++) {
							//System.out.println(meisaiList.get(i).getShohiCode_002());
							if (meisaiList.get(i).getShohiCode_002() == null ) {
								continue;
							} else {
								//明細番号の作り方が不明
								//TODO currentLoginUserの変更
								UridenMTableDao.insert(denNo, i, meisaiList.get(i).getShohiCode_002(),
										meisaiList.get(i).getShohinName_002(),
										Integer.parseInt(meisaiList.get(i).getSuryo_002()),
										Integer.parseInt(meisaiList.get(i).getTanka_002()),
										Integer.parseInt(meisaiList.get(i).getKingaku_002()),
										new java.sql.Timestamp(System.currentTimeMillis()), "admin",
										new java.sql.Timestamp(System.currentTimeMillis()), "admin");
							}
						}
						con.commit();
						session.removeAttribute("DENNO");
						session.removeAttribute("URIDENLIST");
						session.removeAttribute("TANTOUCODE");
						session.removeAttribute("TOKUICODE");
						session.removeAttribute("BIKO");
						session.removeAttribute("URIAGEDATE");
						session.removeAttribute("GOUKEI");
						session.removeAttribute("MODE");
					} catch (SQLException ex) {
						ex.printStackTrace();
						con.rollback();
						System.out.println("RollBack");
					} finally {
						con.setAutoCommit(true);
						con.close();
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			url = "/except/exceptioninfo.jsp";
		} finally {
			if (url.equals(returnPage)) {
				meisaiList.get(0).setDenNo_001(denNo);
				meisaiList.get(0).setTantouCode_001(tantouCode);
				meisaiList.get(0).setTokuiCode_001(tokuiCode);
				meisaiList.get(0).setUriDate_001(uriageDate);
				meisaiList.get(0).setMemo_001(biko);
				session.setAttribute("URIDENLIST", meisaiList);
			}
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
