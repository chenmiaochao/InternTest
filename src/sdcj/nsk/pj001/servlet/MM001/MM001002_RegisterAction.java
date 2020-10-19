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
 * Servlet implementation class MM001002_RegisterAction
 * @author 梶原
 */
@WebServlet("/MM001002_RegisterAction")
public class MM001002_RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MM001002_RegisterAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author 梶原
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = "/jsp/mm001/mm001003.jsp";
		String backurl = "/jsp/mm001/mm001002.jsp";
		String shohinCode = request.getParameter("shohinCode");
		String shohinName = request.getParameter("shohinName");
		String tanka = request.getParameter("tanka");
		String mode = request.getParameter("mode");
		Timestamp updateTime=null;
		if(mode.equals("2")) {
			updateTime = Timestamp.valueOf(request.getParameter("updateTime"));
			request.setAttribute("UPDATETIME", updateTime);
		}

		boolean isValid = true;

		if(mode.equals("0")) {
			request.setAttribute("MODE", "0");
		}else {
			request.setAttribute("MODE", "2");
		}

		String shohinname[] = {"商品名"};
		String shohincode[] = {"商品コード"};
		String money[] = {"単価"};
		String checkcodelength[] = {"商品コード","13"};
		String checknamelength[] = {"商品名","20"};
		String checktankarange[] = {"単価","0","999999999"};
		String codehalfwidth[] = {"商品コード","半角"};
		String tankahalfwidth[] = {"単価","半角"};
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
		if (!ValidateUtil.checkMaxLength(shohinCode,13)) {
			request.setAttribute("CHECKCODELENGTH",MessageUtil.errorMessage("MSG009", checkcodelength));
			isValid = false;
		}

		if (!ValidateUtil.checkMaxLength(shohinName,20)) {
			request.setAttribute("CHECKNAMELENGTH",MessageUtil.errorMessage("MSG009", checknamelength));
			isValid = false;
		}


		//範囲チェック


		if(!((tanka == null) || (tanka.isEmpty()))) {
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
		if (!ValidateUtil.checkHalfWidth(shohinCode)) {
			request.setAttribute("CODEHALFWIDTHERR",MessageUtil.errorMessage("MSG010", codehalfwidth));
			isValid = false;
		}

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

				if (!(tempList.get(0).getUpdateTime().equals(updateTime))) {
					request.setAttribute("DIFFERTIME",MessageUtil.errorMessage("MSG004", differtime));
					isValid = false;
				}

			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}


		try {
			request.setAttribute("SHOHINCODE",shohinCode);
			request.setAttribute("SHOHINNAME", shohinName);
			request.setAttribute("TANKA", tanka);

			if(!(isValid)) {
				url = backurl;
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
