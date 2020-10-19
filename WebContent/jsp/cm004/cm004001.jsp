<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sdcj.nsk.pj001.dto.AccountTableDto" %>
<%
	AccountTableDto loginUser = (AccountTableDto)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更画面</title>
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
        <!-- 共通(ユーザ名・パスワード変更・ログアウトボタン) ここから -->
        <tr height="50">
         <td align="center" valign="middle">
          <table border="1" cellspacing="0" cellpadding="0" width="100%">
           <tr>
            <td>
             <table border="0" cellspacing="2" cellpadding="0" width="100%">
              <tr height="1">
               <td width="10"></td>
               <td width="650"></td>
               <td width="10"></td>
               <td width="100"></td>
               <td width="10"></td>
               <td width="30"></td>
               <td width="10"></td>
              </tr>
              <tr>
               <td></td>
               <td align="center">ようこそ&nbsp;<b><%= loginUser.getLoginId() %></b>&nbsp;さん</td>
               <td></td>
               <td><a href="ChangePasswordAction">パスワード変更</a></td>
               <td></td>
               <td><input type="button" value=" ログアウト " onclick="location.href='LogoutAction'"></td>
               <td></td>
              </tr>
             </table>
            </td>
           </tr>
          </table>
         </td>
        </tr>
        <!-- 共通(ユーザ名・パスワード変更・ログアウトボタン) ここまで -->
        <!-- 共通(パンくず) ここから -->
        <tr height="50">
         <td align="left" valign="middle">
          <table border="0" cellspacing="0" cellpadding="0" width="100%">
           <tr>
            <td>
             <table border="0" cellspacing="2" cellpadding="0" width="100%">
              <tr height="1">
               <td width="60"></td>
               <td width="20"></td>
               <td width="80"></td>
               <td></td>
              </tr>
              <tr>
               <td><a href="BackToMenuAction">メニュー</a></td>
               <td align="center">&gt;&gt;</td>
               <td align="left"><b>パスワード変更画面</b></td>
               <td></td>
              </tr>
             </table>
            </td>
           </tr>
          </table>
         </td>
        </tr>
        <!-- 共通(パンくず) ここまで -->
        <!-- 画面固有UI begin ここから -->
        <tr>
         <td>
          <table border="1" cellspacing="0" cellpadding="0" width="100%">
           <tr>
            <td align="center" valign="middle">
        <!-- 画面固有UI begin ここまで -->
             <!-- メッセージ -->
             <table width="100%">
              <tr height="20">
               <td align="left" valign="middle">
                <font color="#000000">
                <b>
                 パスワード変更<br>
                </b>
                </font>
               </td>
              </tr>
              <tr height="100">
               <td align="center" valign="middle">
             	<form action="CM004001_RegisterAction" method="POST">
		<font color="#FF0000">
                <b>
			<%
			String message = (String) request.getAttribute("message");
			if (message != null)
			   out.print(message);
			%>
		        </b>
                </font>
               </td>
              </tr>
              <tr height="250">
               <td align="center" valign="top">
                <table border="0" cellspacing="0" cellpadding="5" bordercolor="#888888" width="540" height="150">
                 <!-- パスワードの入力 -->
                 <tr>
                  <td align="center" valign="top">
                    <table border="1" cellspacing="0" cellpadding="10" width="100%" bordercolor="#888888" width="540" height="50">
                     <!-- 旧パスワード -->
                     <tr>
                      <td width="200" height="50" bgcolor="#CCCCCC">旧パスワード</td>
                      <td>
                       <input type="password" property="" size="50" maxlength="256" name="oldPass"  />
                      </td>
                     </tr>
                     <!-- 新パスワード -->
                     <tr>
                      <td width="200" height="50" bgcolor="#CCCCCC">新パスワード</td>
                      <td>
                       <input type="password" property="" size="50" maxlength="256" name="newPass"  />
                      </td>
                     </tr>
                     <!-- 新パスワードの確認 -->
                     <tr>
                      <td width="200" height="50" bgcolor="#CCCCCC">新パスワード確認</td>
                      <td>
                       <input type="password" property="" size="50" maxlength="256" name="kakuninPass"  />
                      </td>
                     </tr>
                    </table>
                  </td>
                 </tr>
                 <!-- ボタン -->
                 <tr style="right:30%;">
                  <td align="center" valign="top" style="content:""; display:block; clear:both; ">
                  <input type="submit" value="　更新　" style="float:right; margin-right:170px;"/> <!-- ボタンの位置調整するときはmargin-rightの値変更 -->
                </form>
                <form action="CM004001_BackAction" method="POST">
                 <input type="submit" value="　戻る　" style="float:right; margin-right:40px; "/>
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
             </table>
        <!-- 画面固有UI end ここから -->
            </td>
           </tr>
          </table>
         </td>
        </tr>
        <!-- 画面固有UI end ここまで -->
      </table>
    <!-- 共通(見出し・外枠) end ここから -->
      </td>
     </tr>
    </table>
    <!-- 共通(見出し・外枠) end ここまで -->
  </center>
</body>
</html>

