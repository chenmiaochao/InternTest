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

/**
 * Servlet implementation class MM001001_EditAction
 */
@WebServlet("/MM001001_EditAction")
public class MM001001_EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MM001001_EditAction() {
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

		String url = "/jsp/mm001/mm001002.jsp";
		try {
			String shohinCode = request.getParameter("shohinCode");
			Timestamp updateTime = Timestamp.valueOf(request.getParameter("updateTime"));
			String mode = "";
			List<ShohinTableDto> tempList=null;
			String editerror[] = {"商品コード",shohinCode};

			tempList = ShouhinTableDao.selectByShohinCodeK(shohinCode);

			if (tempList.size() > 0) {
				request.setAttribute("SHOHINCODE", tempList.get(0).getShohinCode());
				request.setAttribute("SHOHINNAME", tempList.get(0).getShohinName());
				request.setAttribute("TANKA", tempList.get(0).getTanka());
				request.setAttribute("UPDATETIME", updateTime);
				mode="2";
			}else {
				request.setAttribute("EDITERROR", MessageUtil.errorMessage("MSG003", editerror));
				mode="1";
			}

			//1:編集エラーあり
			//2:編集エラーなし
			if(mode.equals("1")) {
				request.setAttribute("MODE", "1");
			}else if(mode.equals("2")) {
				request.setAttribute("MODE", "2");
			}

		}catch(Exception ex){
			ex.printStackTrace();
			//エラーページに移る
		}finally {
			//MM001002_登録/編集画面へ画面遷移
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}

	}

}
