<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="sdcj.nsk.pj001.dto.AccountTableDto" %>
<%
	AccountTableDto loginUser = (AccountTableDto)session.getAttribute("loginUser");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>メニュー画面</title>
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
               <td><a href="<%= request.getContextPath() %>/ChangePasswordAction">パスワード変更</a></td>
               <td></td>
               <td><input type="button" value=" ログアウト " onclick="location.href='<%= request.getContextPath() %>/LogoutAction'" ></td>
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
          <table border="1" cellspacing="0" cellpadding="0" width="100%">
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
               <td><a href="<%= request.getContextPath() %>/BackToMenuAction">メニュー</a></td>
               <!--1306003nishi <td><a href="./backToMenuAction.do">メニュー</a></td> -->
               <td align="center"></td>
               <td align="left"></td>
<!--
               <td align="center">&gt;&gt;</td>
               <td align="left"><b>パスワード変更画面</b></td>
-->
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

             <!-- メニューボタン -->
             <table border="0" width="900">
              <tr>
               <td align="center" width="100%">
                <table border="0" cellspacing="0" cellpadding="10" bordercolor="#888888" width="100%">
                 <tr height="500">
                  <td width="100"></td>
                  <td width="700" align="center">

                   <!-- マスタ管理 -->
                   <table border="1" cellspacing="0" cellpadding="00" bordercolor="#888888" width="600" height="75">
                    <tr>
                    <td>
                     <table border="0" cellspacing="0" cellpadding="10" bordercolor="#888888" width="600" height="75">
                      <tr>
                       <td width="150" valign="top">マスタ管理</td>
                       <td width="400" valign="bottom">
                        <form action="<%= request.getContextPath() %>/CM002001_MM001Action">
                        <input type="submit" value="　商品マスタ管理　" />
                        </form>
                       </td>
                       <td>&nbsp;</td>
                      </tr>
                     </table>
                    </td>
                    </tr>
                   </table>
                   <br>

                   <!-- 各種業務 -->
                   <table border="1" cellspacing="0" cellpadding="00" bordercolor="#888888" width="600" height="75">
                    <tr>
                    <td>
                     <table border="0" cellspacing="0" cellpadding="10" bordercolor="#888888" width="600" height="75">
                      <tr>
                       <td width="150" valign="top">各種業務</td>
                       <td width="400" valign="bottom">
                        <form action="<%= request.getContextPath() %>/CM002001_UA001Action">
                        <input type="submit" value="　売上伝票管理　" />
                        </form>
                       </td>
                       <td>&nbsp;</td>
                      </tr>
                     </table>
                    </td>
                    </tr>
                   </table>
                   <br>

                   <!-- データ出力 -->
                   <table border="1" cellspacing="0" cellpadding="00" bordercolor="#888888" width="600" height="75">
                    <tr>
                    <td>
                     <table border="0" cellspacing="0" cellpadding="10" bordercolor="#888888" width="600" height="75">
                      <tr>
                       <td width="150" valign="top">データ出力</td>
                       <td width="400" valign="bottom">
                        <br>
                        <form action="<%= request.getContextPath() %>/CM002001_MM002Action">
                        <input type="submit" value="　商品マスタ一覧出力　" /><br><br>
                        </form>
                        <form action="<%= request.getContextPath() %>/CM002001_CM005Action">
                        <input type="submit" value="　　納品データ出力　　" /><br><br>
                        </form>
                        <form action="<%= request.getContextPath() %>/CM002001_CM005Action">
                        <input type="submit" value="　売上明細データ出力　" /><br><br>
                        </form>
                        <form action="<%= request.getContextPath() %>/CM002001_CM005Action">
                        <input type="submit" value="　売上集計データ出力　" /><br><br>
                        </form>
                       </td>
                       <td>&nbsp;</td>
                      </tr>
                     </table>
                    </td>
                    </tr>
                   </table>
                  </td>
                  <td width="100"></td>
                 </tr>
                </table>
               </td>
               <td width="100"></td>
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
