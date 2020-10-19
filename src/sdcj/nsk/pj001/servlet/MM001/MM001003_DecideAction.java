package sdcj.nsk.pj001.servlet.MM001;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdcj.nsk.pj001.dao.ShouhinTableDao;
import sdcj.nsk.pj001.dto.ShohinTableDto;
import sdcj.nsk.pj001.utils.MessageUtil;
import sdcj.nsk.pj001.utils.ValidateUtil;

/**
 * Servlet implementation class MM001003_DecideAction
 */
@WebServlet("/MM001003_DecideAction")
public class MM001003_DecideAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MM001003_DecideAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/jsp/mm001/mm001004.jsp";
		String backurl = "/jsp/mm001/mm001003.jsp";

		//TODO Systemをログインユーザに変更

		String shohinCode = request.getParameter("shohinCode");
		String shohinName = request.getParameter("shohinName");
		String tanka = request.getParameter("tanka");
		String mode = request.getParameter("mode");
		String updateUser = "System";
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		boolean isValid = true;
		int errorflg = 1;
		Timestamp updateTime=null;
		if(mode.equals("2")) {
			updateTime = Timestamp.valueOf(request.getParameter("updateTime"));
			request.setAttribute("UPDATETIME", updateTime);
		}


		String shohinname[] = {"商品名"};
		String shohincode[] = {"商品コード"};
		String money[] = {"単価"};
		String checknamelength[] = {"商品名","20"};
		String checktankarange[] = {"0","999999999"};
		String tankahalfwidth[] = {"単価","半角"};
		String isof[] = {"商品マスタ","新規登録"};
		String usof[] = {"商品マスタ","編集"};
		String nodata[] = {""};
		String differtime[] = {""};


		//必須チェック
		if (shohinCode == null || shohinCode.isEmpty()) {
			request.setAttribute("SHOHINCODEEMPTY",MessageUtil.errorMessage("MSG008", shohincode));
			isValid = false;
		}
		if (shohinName == null || shohinName.isEmpty()) {
			request.setAttribute("SHOHINNAMEEMPTY",MessageUtil.errorMessage("MSG008", shohinname));
			isValid = false;
		}
		if (tanka == null || tanka.isEmpty()) {
			request.setAttribute("TANKAEMPTY",MessageUtil.errorMessage("MSG008", money));
			isValid = false;
		}


		//最大長チェック
		if (!ValidateUtil.checkMaxLength(shohinName,20)) {
			request.setAttribute("CHECKNAMELENGTH",MessageUtil.errorMessage("MSG009", checknamelength));
			isValid = false;
		}


		//範囲チェック
		if(!((tanka == null || tanka.isEmpty()))) {
			if (tanka.matches("^[0-9]*$")) {
				int tankaRange = Integer.parseInt(tanka);
				if(!(0 <= tankaRange && tankaRange <= 999999999)) {
					request.setAttribute("CHECKTANKARANGE",MessageUtil.errorMessage("MSG011", checktankarange));
					isValid = false;
				}
			}else {
				request.setAttribute("CHECKTANKARANGE",MessageUtil.errorMessage("MSG011", checktankarange));
				isValid = false;
			}
		}


		//全半角チェック
		if (!ValidateUtil.checkHalfWidth(tanka)) {
			request.setAttribute("TANKAHALFWIDTHERR",MessageUtil.errorMessage("MSG010", tankahalfwidth));
			isValid = false;
		}


		try {
			List<ShohinTableDto> tempList = null;
			tempList = ShouhinTableDao.selectByShohinCodeK(shohinCode);

			if(mode.equals("0")) {
				if (tempList.size() > 0) {
					request.setAttribute("REGISTEREDCODE",MessageUtil.errorMessage("MSG002", shohincode));
					isValid = false;
					}
			}else if(mode.equals("2")) {
					if (tempList.size() == 0) {
						request.setAttribute("NODATA",MessageUtil.errorMessage("MSG004", nodata));
						isValid = false;
					}
					if(!(tempList.get(0).getUpdateTime().equals(updateTime))) {
						request.setAttribute("NODATA",MessageUtil.errorMessage("MSG004", nodata));
						isValid = false;
					}
				}
			}catch(Exception ex) {
			ex.printStackTrace();
		}


		if(!(isValid)) {
			RequestDispatcher rd = request.getRequestDispatcher(backurl);
			rd.forward(request, response);
			return;
		}


		try {

		if(mode.equals("0")) {
			try {
				String entryUser = "System";
				ShouhinTableDao ShouhinTableDaoD = new ShouhinTableDao();
				ShouhinTableDaoD.insert(shohinCode,shohinName,tanka,currentTime,entryUser,currentTime,updateUser);
				request.setAttribute("INSERTSUCCESS", MessageUtil.errorMessage("MSG005", isof));
			}catch(Exception ex) {
				request.setAttribute("INSERTMISS", MessageUtil.errorMessage("MSG006", isof));
				url = backurl;
			}

		}else if(mode.equals("2")){
			try {
				List<ShohinTableDto> tempList=null;
				tempList = ShouhinTableDao.selectByShohinCodeK(shohinCode);

				ShouhinTableDao ShouhinTableDaoD = new ShouhinTableDao();
				ShouhinTableDaoD.updateByCondition(shohinCode,shohinName,tanka,currentTime,updateUser,updateTime);

				request.setAttribute("UPDATESUCCESS", MessageUtil.errorMessage("MSG005", usof));
				errorflg = 0;
			}catch(Exception ex) {
				request.setAttribute("UPDATEMISS", MessageUtil.errorMessage("MSG006", usof));
				url = backurl;
				ex.printStackTrace();
			}

		}


		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			request.setAttribute("ERRORFLG", errorflg);
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}





	}

}
