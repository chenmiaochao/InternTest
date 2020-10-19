package sdcj.nsk.pj001.servlet.UA001;

import java.io.IOException;
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

import sdcj.nsk.pj001.dao.ShouhinTableDao;
import sdcj.nsk.pj001.dao.TantouTableDao;
import sdcj.nsk.pj001.dao.TokuiTableDao;
import sdcj.nsk.pj001.dao.UridenHTableDao;
import sdcj.nsk.pj001.dao.UridenJViewDao;
import sdcj.nsk.pj001.dto.AccountTableDto;
import sdcj.nsk.pj001.dto.UridenHTableDto;
import sdcj.nsk.pj001.dto.UridenJViewDto;
import sdcj.nsk.pj001.utils.MessageUtil;
import sdcj.nsk.pj001.utils.ValidateUtil;

/**
 * Servlet implementation class UA001005_RegisterAction
 * @author nguyen.hungminh
 */
@WebServlet("/UA001005_RegisterAction")
public class UA001005_RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UA001005_RegisterAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author nguyen.hungminh
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = "/jsp/ua001/ua001006.jsp";
		HttpSession session = request.getSession();
		//var session = request.getSession();
		var loginUser = (AccountTableDto)session.getAttribute("loginUser");

		if (loginUser == null || loginUser.getLoginId().isEmpty()) {
			// 未ログイン
			response.sendRedirect("jsp/cm001/cm001001.jsp");
			return;
		}
		String returnPage = "/jsp/ua001/ua001005.jsp";
		List<UridenJViewDto> meisaiList = (List<UridenJViewDto>) session.getAttribute("URIDENLIST");
		String tantouCode = request.getParameter("tantouCode");
		String denNo = request.getParameter("denNo");
		String tokuiCode = request.getParameter("tokuiCode");
		String uriageDate = request.getParameter("uriageDate");
		String biko = request.getParameter("biko");
		//System.out.println(biko);
		try {

			Timestamp henkou_open = (Timestamp) session.getAttribute("HENKOUTIME");
			Timestamp update_time = UridenJViewDao.selectByDenNo(denNo).get(0).getUpdateTime_001();
			String mode = null;
			if ((String) session.getAttribute("MODE") != null) {
				mode = (String) session.getAttribute("MODE");
			}
			System.out.println("MODE:" + mode);
			//System.out.println(henkou_open);
			//System.out.println(update_time);

			if (meisaiList == null || meisaiList.isEmpty()) {
				meisaiList = new ArrayList<UridenJViewDto>();
				for (int i = 0; i < 4; i++) {
					UridenJViewDto dto = new UridenJViewDto();
					meisaiList.add(dto);
				}
			}
			//boolean test = true;
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
			/*
			 ①	必須チェック
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
				ｆ）	半角チェック でエラーがある場合、以下の文字列を画面項目.エラーメッセージに編集する。
					エラーメッセージID：MSG010
				g)	日付チェック でエラーがある場合、以下の文字列を画面項目.エラーメッセージに編集する。
					エラーメッセージID：MSG014
				h)	範囲チェック でエラーがある場合、以下の文字列を画面項目.エラーメッセージに編集する。
					エラーメッセージID：MSG010
					置換文字列：数量、正の数
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
				String errMsg = MessageUtil.errorMessage("MSG011", displayMsg);
				request.setAttribute("ERRMSG", errMsg);
				url = returnPage;
				return;
			}
			if (!ValidateUtil.checkMinLength(uriageDate, 8) || !ValidateUtil.checkMaxLength(uriageDate, 8)
					|| !ValidateUtil.checkHalfWidth(uriageDate) || !ValidateUtil.checkDate(uriageDate)) {
				String displayMsg[] = { "売上日付", "正確で" };
				String errMsg = MessageUtil.errorMessage("MSG014", displayMsg);
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
			//check 売上伝票番号
			if (mode == null) {
				//Todo: 後ほど処理
				url = "/index.jsp";
			} else if (mode.equals("1")) {
				//Todo: 後ほど処理
				url = "/index.jsp";
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
				} else {

					String tantouName = TantouTableDao.selectByTantouCode(tantouCode).getTantouName();
					String tokuiName = TokuiTableDao.selectByTokuiCode(tokuiCode).getTokuiName();

					if ( tokuiName == null || tokuiName.isBlank()  ) {
						String displayMsg[] = { "得意先", "取得" };
						String errMsg = MessageUtil.errorMessage("MSG006", displayMsg);
						request.setAttribute("ERRMSG", errMsg);
						url = returnPage;
						return;
					}
					if (tantouName == null || tantouName.isBlank()  ) {
						String displayMsg[] = { "担当者", "取得" };
						String errMsg = MessageUtil.errorMessage("MSG006", displayMsg);
						request.setAttribute("ERRMSG", errMsg);
						url = returnPage;
						return;
					}
					//System.out.println(tantouCode);
					//System.out.println(denNo);
					//System.out.println(tokuiCode);
					//System.out.println(uriageDate);
					//System.out.println(biko);

					meisaiList.get(0).setDenNo_001(denNo);
					meisaiList.get(0).setTantouCode_001(tantouCode);
					meisaiList.get(0).setTantouName_004(tantouName);
					meisaiList.get(0).setTokuiCode_001(tokuiCode);
					meisaiList.get(0).setTokuiName_003(tokuiName);
					meisaiList.get(0).setUriDate_001(uriageDate);
					meisaiList.get(0).setMemo_001(biko);
					List<String> tempCheckCode = new ArrayList<String>();
					for (int i = 0; i < 4; i++) {
						tempCheckCode.add(request.getParameter("shohinCode" + i).trim());
						meisaiList.get(i).setShohiCode_002(request.getParameter("shohinCode" + i));
						//System.out.println("商品コード" + request.getParameter("shohinCode" + i) );
					}
					//新規登録
					//売上伝票番号が存在するかどうかの確認
					//check 売上伝票番号

					boolean checkDuplicate = ValidateUtil.checkDuplicateUsingSet(tempCheckCode);
					if (checkDuplicate == false) {
						//String displayMsg[] = { "商品コード" };
						String errMsg = MessageUtil.errorMessage("MSG015", null);
						request.setAttribute("ERRMSG", errMsg);
						url = returnPage;
						return;
					}
					int goukei = 0;
					int meisai_count = 0;
					for (int i = 0; i < 4; i++) {
						String shohinCode = request.getParameter("shohinCode" + i);
						String suryo = request.getParameter("suryo" + i);

						if (shohinCode != null && !shohinCode.isBlank() && suryo != null && !suryo.isBlank() &&
								ValidateUtil.checkMaxLength(shohinCode, 13) && ValidateUtil.checkMaxLength(suryo, 8)
								&& ValidateUtil.checkNumberRange(suryo, 0, 999999999)) {

							if (ShouhinTableDao.selectByShohinCode(shohinCode)
									.getShohinName() != null) {
								meisaiList.get(i).setShohiCode_002(shohinCode);
								meisaiList.get(i).setShohinName_002(
										ShouhinTableDao.selectByShohinCode(shohinCode)
												.getShohinName());
								meisaiList.get(i).setTanka_002(
										ShouhinTableDao.selectByShohinCode(shohinCode)
												.getTanka());
								meisaiList.get(i).setSuryo_002(suryo);
								if (meisaiList.get(i).getSuryo_002() != null
										&& meisaiList.get(i).getTanka_002() != null) {
									int kingaku = Integer.parseInt(meisaiList.get(i).getSuryo_002())
											* Integer.parseInt(meisaiList.get(i).getTanka_002());
									meisaiList.get(i).setKingaku_002(Integer.toString(kingaku));
									goukei += kingaku;
								}
								meisai_count++;
							} else {
								String displayMsg[] = { "商品情報", "取得" };
								String errMsg = MessageUtil.errorMessage("MSG006", displayMsg);
								request.setAttribute("ERRMSG", errMsg);
								url = returnPage;
								return;
							}

						} else {
							if ((shohinCode == null || shohinCode.isBlank()
									|| !ValidateUtil.checkMaxLength(shohinCode, 13))
									&& (suryo == null || suryo.isBlank() || !ValidateUtil.checkMaxLength(suryo, 8)
											|| !ValidateUtil.checkNumberRange(suryo, 0, 999999999))) {
								meisaiList.get(i).setShohiCode_002(null);
								meisaiList.get(i).setShohinName_002(null);
								meisaiList.get(i).setSuryo_002(null);
							} else {
								if (shohinCode == null || shohinCode.isBlank()
										|| !ValidateUtil.checkMaxLength(shohinCode, 13)) {
									meisaiList.get(i).setShohiCode_002(null);
									meisaiList.get(i).setSuryo_002(suryo);
									String displayMsg[] = { "商品コード" };
									String errMsg = MessageUtil.errorMessage("MSG008", displayMsg);
									request.setAttribute("ERRMSG", errMsg);
								}
								if (suryo == null || suryo.isBlank() || !ValidateUtil.checkMaxLength(suryo, 8)
										|| !ValidateUtil.checkNumberRange(suryo, 0, 999999999)) {
									meisaiList.get(i).setShohiCode_002(shohinCode);
									meisaiList.get(i).setSuryo_002(null);

									String displayMsg[] = { "数量" };
									String errMsg = MessageUtil.errorMessage("MSG008", displayMsg);
									request.setAttribute("ERRMSG", errMsg);
								}
								url = returnPage;

							}
							meisaiList.get(i).setShohinName_002(null);
							meisaiList.get(i).setTanka_002(null);
							meisaiList.get(i).setSuryo_002(null);
							meisaiList.get(i).setKingaku_002(null);
						}

						//System.out.println("Total" + meisai_count);
					}
					if (meisai_count < 1) {
						String displayMsg[] = { "商品" };
						String errMsg = MessageUtil.errorMessage("MSG008", displayMsg);
						request.setAttribute("ERRMSG", errMsg);
						url = returnPage;
					}
					session.setAttribute("DENNO", denNo);
					session.setAttribute("URIDENLIST", meisaiList);
					session.setAttribute("TANTOUCODE", tantouCode);
					session.setAttribute("TOKUICODE", tokuiCode);
					session.setAttribute("BIKO", biko);
					session.setAttribute("URIAGEDATE", uriageDate);
					session.setAttribute("GOUKEI", goukei);
				}
			} else if (mode.equals("0")) {

				List<String> tempCheckCode = new ArrayList<String>();
				for (int i = 0; i < 4; i++) {

					tempCheckCode.add(request.getParameter("shohinCode" + i).trim());
					meisaiList.get(i).setShohiCode_002(request.getParameter("shohinCode" + i));
					//System.out.println("商品コード" + request.getParameter("shohinCode" + i) );
				}
				//新規登録
				//売上伝票番号が存在するかどうかの確認
				//check 売上伝票番号
				//商品コード重複確認
				boolean checkDuplicate = ValidateUtil.checkDuplicateUsingSet(tempCheckCode);
				//System.out.println("CheckDuplicate" + checkDuplicate);
				if (checkDuplicate == false) {
					String errMsg = MessageUtil.errorMessage("MSG015", null);
					request.setAttribute("ERRMSG", errMsg);
					url = returnPage;
					return;
				}

				String tantouName = TantouTableDao.selectByTantouCode(tantouCode).getTantouName();
				String tokuiName = TokuiTableDao.selectByTokuiCode(tokuiCode).getTokuiName();
				if ( tokuiName == null || tokuiName.isBlank()  ) {
					String displayMsg[] = { "得意先", "取得" };
					String errMsg = MessageUtil.errorMessage("MSG006", displayMsg);
					request.setAttribute("ERRMSG", errMsg);
					url = returnPage;
					return;
				}
				if (tantouName == null || tantouName.isBlank()  ) {
					String displayMsg[] = { "担当者", "取得" };
					String errMsg = MessageUtil.errorMessage("MSG006", displayMsg);
					request.setAttribute("ERRMSG", errMsg);
					url = returnPage;
					return;
				}
				List<UridenHTableDto> existList = UridenHTableDao.selectByDenNo(denNo);
				//System.out.println("Existlist" + existList.size());
				if (!existList.isEmpty()) {
					String displayMsg[] = { "伝票番号" };
					String errMsg = MessageUtil.errorMessage("MSG002", displayMsg);
					request.setAttribute("ERRMSG", errMsg);
					url = returnPage;
					return;
				}
				//System.out.println(tantouCode);
				//System.out.println(denNo);
				//System.out.println(tokuiCode);
				//System.out.println(uriageDate);
				//System.out.println(biko);

				meisaiList.get(0).setDenNo_001(denNo);
				meisaiList.get(0).setTantouCode_001(tantouCode);
				meisaiList.get(0).setTantouName_004(tantouName);
				meisaiList.get(0).setTokuiCode_001(tokuiCode);
				meisaiList.get(0).setTokuiName_003(tokuiName);
				meisaiList.get(0).setUriDate_001(uriageDate);
				meisaiList.get(0).setMemo_001(biko);
				int goukei = 0;
				int meisai_count = 0;
				for (int i = 0; i < 4; i++) {
					String shohinCode = request.getParameter("shohinCode" + i);
					String suryo = request.getParameter("suryo" + i);
					//System.out.println("数量" + i + ": " + suryo);
						//商品コードのチェック最小、最長、範囲
						if (shohinCode != null && !shohinCode.isBlank() && suryo != null && !suryo.isBlank() &&
								ValidateUtil.checkMaxLength(shohinCode, 13) && ValidateUtil.checkMaxLength(suryo, 8)
								&& ValidateUtil.checkNumberRange(suryo, 0, 999999999)) {
							//商品コードのチェック：マスターに存在有無、
							if (ShouhinTableDao.selectByShohinCode(shohinCode).getShohinName() != null) {
								meisaiList.get(i).setShohiCode_002(shohinCode);
								meisaiList.get(i).setShohinName_002(
										ShouhinTableDao.selectByShohinCode(shohinCode)
												.getShohinName());
								meisaiList.get(i).setTanka_002(
										ShouhinTableDao.selectByShohinCode(shohinCode)
												.getTanka());
								meisaiList.get(i).setSuryo_002(suryo);
								if (meisaiList.get(i).getSuryo_002() != null && meisaiList.get(i).getTanka_002() != null) {
									int kingaku = Integer.parseInt(meisaiList.get(i).getSuryo_002())
											* Integer.parseInt(meisaiList.get(i).getTanka_002());
									meisaiList.get(i).setKingaku_002(Integer.toString(kingaku));
									goukei += kingaku;
								}
								meisai_count++;
							}else {
								String[] msg = {"商品情報","取得"};
								String errMsg = MessageUtil.errorMessage("MSG006", msg);
								request.setAttribute("ERRMSG", errMsg);
								url = returnPage;
								return;
							}
						} else {
							if ((shohinCode == null || shohinCode.isBlank()
									|| !ValidateUtil.checkMaxLength(shohinCode, 13))
									&& (suryo == null || suryo.isBlank() || (!ValidateUtil.checkMaxLength(suryo, 8))
											|| !ValidateUtil.checkNumberRange(suryo, 0, 999999999))) {
								meisaiList.get(i).setShohiCode_002(null);
								meisaiList.get(i).setShohinName_002(null);
								meisaiList.get(i).setSuryo_002(null);
							} else {
								if (shohinCode == null || shohinCode.isBlank()
										|| !ValidateUtil.checkMaxLength(shohinCode, 13)) {
									meisaiList.get(i).setShohiCode_002(null);
									meisaiList.get(i).setSuryo_002(suryo);
									String displayMsg[] = { "商品コード" };
									String errMsg = MessageUtil.errorMessage("MSG008", displayMsg);
									request.setAttribute("ERRMSG", errMsg);
								}
								if (suryo == null || suryo.isBlank() || !ValidateUtil.checkMaxLength(suryo, 8)
										|| !ValidateUtil.checkNumberRange(suryo, 0, 999999999)) {
									meisaiList.get(i).setShohiCode_002(shohinCode);
									meisaiList.get(i).setSuryo_002(null);

									String displayMsg[] = { "数量" };
									String errMsg = MessageUtil.errorMessage("MSG008", displayMsg);
									request.setAttribute("ERRMSG", errMsg);
								}
								url = returnPage;

							}
							meisaiList.get(i).setShohinName_002(null);
							meisaiList.get(i).setTanka_002(null);
							meisaiList.get(i).setSuryo_002(null);
							meisaiList.get(i).setKingaku_002(null);
						}

						//System.out.println("Total" + meisai_count);

				}
				if (meisai_count < 1) {
					String displayMsg[] = { "商品" };
					String errMsg = MessageUtil.errorMessage("MSG008", displayMsg);
					request.setAttribute("ERRMSG", errMsg);
					url = returnPage;
				}
				session.setAttribute("DENNO", denNo);
				session.setAttribute("URIDENLIST", meisaiList);
				session.setAttribute("TANTOUCODE", tantouCode);
				session.setAttribute("TOKUICODE", tokuiCode);
				session.setAttribute("BIKO", biko);
				session.setAttribute("URIAGEDATE", uriageDate);
				session.setAttribute("GOUKEI", goukei);
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
