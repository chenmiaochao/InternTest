<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<script type="text/javascript">
    <!--
    // -->
</script>
</head>
  <center>

    <!-- 共通(見出し・外枠) begin ここから -->
    <table border="1" cellspacing="0" cellpadding="5" bordercolor="#888888" width="960" height="540">
     <tr bgcolor="#888888" valign="bottom" height="40"><td><font color="#FFFFFF"><b>sdc 研修プロジェクト 商品管理/売上管理システム</b></font></td></tr>
     <tr>
      <td align="center" valign="top">
    <!-- 共通(見出し・外枠) begin ここまで -->

       <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <!-- エラーメッセージ -->
        <tr height="150">
         <td align="center" valign="middle">
          <font color="#FF0000">
          <%
			//エラーの表示
			String message = (String) request.getAttribute("error");
			if (message != null) {
				out.print(message);
				 	}
				 %>
          </font>
         </td>
        </tr>

		 <!-- ユーザID、パスワード入力、ログインボタン -->
        <tr height="250">
         <td align="center" valign="top">
          <table border="1" cellspacing="0" cellpadding="5" bordercolor="#888888" width="540" height="300">
           <tr>
            <td align="center">
             <form action="<%= request.getContextPath() %>/CM001001_LoginAction" method="POST">
              <table border="0" width="100%" height="100%">
               <tr height="0">
                <td width="100"></td>
                <td></td>
                <td width="20"></td>
                <td></td>
                <td width="100"></td>
               </tr>

               <tr height="60"><td colspan="5"></td></tr>

               <tr>
                <td></td>
                <td>ユーザID</td>
                <td></td>
                <td>
                 <input type="text" property="" size="20" maxlength="30" name= "loginId"  />
                </td>
                <td></td>
               </tr>

               <tr height="20"><td colspan="5"></td></tr>

               <tr>
                <td></td>
                <td>パスワード</td>
                <td></td>
                <td>
                 <input type="password" property="" size="20" maxlength="30" name= "password"  />
                </td>
                <td></td>
               </tr>


               <tr height="20"><td colspan="5"></td></tr>

               <tr>
                <td align="center" colspan="5">
                 <input type="submit" value="　ログイン　" />
                </td>
               </tr>

               <tr height="60"><td colspan="5"></td></tr>

              </table>
             </form>
            </td>
           </tr>
          </table>
         </td>
        </tr>

		 <!-- パスワードリマインダ画面へのリンク -->
        <tr height="150">
         <td align="center" valign="top">
          <table border="0" cellspacing="0" cellpadding="5" bordercolor="#888888" width="540">
           <tr><td align="right" valign="top"><a href="<%= request.getContextPath() %>/CM003001_InitAction">パスワードを忘れた方はこちらへ</a></td></tr>
          </table>
         </td>
        </tr>

    <!-- 共通(見出し・外枠) end ここから -->
      </td>
     </tr>
    </table>
    <!-- 共通(見出し・外枠) end ここまで -->

  </center>
  </body>
</html>