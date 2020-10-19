<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>パスワードリマインダ画面</title>
  </head>
  <body>
  <center>

    <!-- 共通(見出し・外枠) begin ここから -->
    <table border="1" cellspacing="0" cellpadding="5" bordercolor="#888888" width="960" height="540">
     <tr bgcolor="#888888" valign="bottom" height="40"><td><font color="#FFFFFF"><b>sdc 研修プロジェクト 商品管理/売上管理システム</b></font></td></tr>
     <tr>
      <td align="center" valign="top">
    <!-- 共通(見出し・外枠) begin ここまで -->

       <table border="0" cellspacing="0" cellpadding="0" width="100%">

        <!-- メッセージ -->
        <tr height="100">
         <td align="center" valign="middle">
          <font color="#6699FF">
          <b>
           <%
			String message = (String) request.getAttribute("message");
			String mode = (String) request.getAttribute("mode");
			out.print(message);
			%>
          </b>
          </font>
         </td>
        </tr>

        <!-- メールアドレス -->
        <tr height="250">
         <td align="center" valign="top">
          <table border="0" cellspacing="0" cellpadding="5" bordercolor="#888888" width="540" height="150">
           <tr>
            <td align="center" valign="top">
             <form action="CM003001_ReissueAction" method="POST">
              <table border="1" cellspacing="0" cellpadding="10" width="100%" bordercolor="#888888" width="540" height="50">
               <tr>
                <td width="200" height="50" align="right" bgcolor="#CCCCCC">メールアドレス&nbsp;</td>
                <td>
                 <input type="text" property="" size="50" maxlength="256" name= "mailaddress"  />
                </td>
               </tr>

              </table>
            </td>
           </tr>
           <!-- ボタン -->
           <tr style="right:30%;">
            <td align="center" valign="top" style="content:""; display:block; clear:both; ">
               <%
               if("0".equals(mode)){
               %>
                  <input type="submit" value="　再発行　" style="float:right; margin-right:170px;"/>
               <%
               }
               %>
            </form>
            <form action="InitAction" method="POST">
                <input type="submit" value="　ログイン画面へ　" style="float:right; margin-right:40px; "/>
            </form>
              </td>
            </tr>
          </table>
         </td>
        </tr>

        <tr height="150">
         <td align="center" valign="top">
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
