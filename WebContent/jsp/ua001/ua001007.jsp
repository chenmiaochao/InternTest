<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>売上伝票 更新完了画面</title>
</head>
<body>
<center>
     <!-- 共通(見出し・外枠) begin ここから -->
    <table border="1" cellspacing="0" cellpadding="5" bordercolor="#888888" width="960" height="540">
     <tr bgcolor="#888888" valign="bottom" height="40">
      <td>
       <font color="#FFFFFF">
        <b>sdc 研修プロジェクト 商品管理/売上管理システム</b>
       </font>
      </td>
     </tr>
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
               <td align="center">ようこそ&nbsp;<b>${ sessionScope.loginUser.loginId }</b>&nbsp;さん</td>
               <td></td>
               <!-- リンク先保留 -->
               <td><a href="${ pageContext.request.contextPath }/ChangePasswordAction">パスワード変更</a></td>
               <td></td>
               <!-- リンク先保留 -->
               <td><input type="button" value=" ログアウト " onclick="location.href='${ pageContext.request.contextPath }/LogoutAction'"></td>
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
               <td width="80"></td>
               <td width="20"></td>
               <td width="80"></td>
               <td></td>
              </tr>
              <tr>
              <!-- リンク先保留 -->
               <td align="right"><a href="${ pageContext.request.contextPath }/BackToMenuAction">メニュー</a></td>
               <td align="center">&gt;&gt;</td>
               <td align="left"><b> 売上伝票 更新完了画面</b></td>
               <td></td>
              </tr>
             </table>

            </td>
           </tr>
          </table>
         </td>
        </tr>
        <!-- 共通(ユーザ名・パスワード変更・ログアウトボタン) ここまで -->

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
                 売上伝票 更新完了画面<br>
                </b>
                </font>
               </td>
              </tr>
              <tr height="100">
               <td align="center" valign="middle">
                <font color="#0000FF">
                <b>
                 売上伝票の更新が完了しました。<br>
                </b>
                </font>
               </td>
              </tr>

              <tr height="250">
               <td align="center" valign="top">
                <table border="0" cellspacing="0" cellpadding="5" bordercolor="#888888" width="540" height="150">
                 <!-- ボタン -->
                 <tr>
                  <td align="center">
                   <input type="submit" value="　売上伝票の新規登録　" onclick="location.href='${ pageContext.request.contextPath }/UA001007_RegisterAction'"/>
                  </td>
                 </tr>
                 <tr>
                  <td align="center">
                   <input type="submit" value="　売上伝票一覧画面へ　" onclick="location.href='${ pageContext.request.contextPath }/UA001007_UA001Action'"/>
                  </td>
                 </tr>
                 <tr>
                  <td align="center">
                   <input type="submit" value="　メニュー画面へ　" onclick="location.href='${ pageContext.request.contextPath }/BackToMenuAction'"/>
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