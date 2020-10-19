<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="sdcj.nsk.pj001.dto.AccountTableDto" %>
<%
	AccountTableDto loginUser = (AccountTableDto)session.getAttribute("loginUser");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <title>商品マスタ データ出力画面</title>
 </head>
<body>
 <!-- LOGIN USER -->
   <c:set var="user" value="${ sessionScope.loginUser }"/>
​
   <!-- ERRORS -->
   <c:set var="err" value="${ requestScope.ERR }"/>

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
               <td><input type="button" value=" ログアウト " onclick="location.href='${ pageContext.request.contextPath }/LogoutAction'" ></td>
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
               <td align="left"><b>商品マスタ データ出力</b></td>
               <td></td>
              </tr>
             </table>

            </td>
           </tr>
          </table>
         </td>
        </tr>

        <!-- 共通(パンくず) ここまで -->
​
        <!-- 画面固有UI begin ここから -->
        <tr>
         <td>
          <table border="1" cellspacing="0" cellpadding="0" width="100%">
           <tr>
            <td align="center" valign="middle">
        <!-- 画面固有UI begin ここまで -->
​
             <!-- メッセージ -->
             <table width="100%">
              <tr height="20">
               <td align="left" valign="middle">
                <font color="#000000">
                <b>
                 商品マスタ データ出力<br>
                </b>
                </font>
               </td>
              </tr>
              <tr height="250">
              <td align="center" valign="middle">
                <font color="#FF0000">
                <b>
                 <c:if test="${not empty requestScope.ERR }">
					<c:out value="${requestScope.ERR }" />
                 </c:if>
                </b>
                </font>
               </td>
               <td align="center" valign="top">
                <table border="0" cellspacing="0" cellpadding="5" bordercolor="#888888" width="900" height="150">
                 <tr height="0">
                  <td width="80"></td>
                  <td width="80"></td>
                  <td width="5"></td>
                  <td width="650"></td>
                 </tr>
                 <form>
                  <!-- 商品コード -->
                 <tr>
                  <td></td>
                  <td align="right">商品コード</td>
                  <td></td>
                  <td>
                   <input type="button" value="選択" onclick="location.href='${ pageContext.request.contextPath }/MM002001_HK001_FromAction?uid=MM002001'" />&nbsp;
                   <input type="text" size="20" name="shohinCodeFrom" value="${ sessionScope.SHOHINCODEFROM }" />
                   &nbsp;～&nbsp;
                   <input type="button" value="選択" onclick="location.href='${ pageContext.request.contextPath }/MM002001_HK001_ToAction?uid=MM002001'" />&nbsp;
                   <input type="text" size="20" name="shohinCodeTo" value="${ sessionScope.SHOHINCODETO }" />
                  </td>
                 </tr>
                 <!-- ボタン -->
                 <tr height="50">
                  <td align="center" colspan="3"></td>
                  <td>
                   <input type="submit" formaction="${ pageContext.request.contextPath }/MM002001_CSV_DLAction" formmethod="get" value="　CSVダウンロード　" />
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   <input type="submit" formaction="${ pageContext.request.contextPath }/MM002001_SM_DLAction" formmethod="get" value="　帳票ダウンロード　" />
                  </td>
                 </tr>
                 </form>
                </table>
               </td>
              </tr>
              <tr height="150">
               <td align="center" valign="top">
               </td>
              </tr>
             </table>
​
        <!-- 画面固有UI end ここから -->
            </td>
           </tr>
          </table>
         </td>
        </tr>
        <!-- 画面固有UI end ここまで -->
​
      </table>
    <!-- 共通(見出し・外枠) end ここから -->
      </td>
     </tr>
    </table>
    <!-- 共通(見出し・外枠) end ここまで -->
    <c:if test="${not empty requestScope.SHOHINCODELENGTHERR }">
		<c:out value="${requestScope.SHOHINCODELENGTHERR }" />
    </c:if>
    <c:if test="${not empty requestScope.SHOHINCODEHALFWIDTHERR }">
		<c:out value="${requestScope.SHOHINCODEHALFWIDTHERR }" />
    </c:if>
    <c:if test="${not empty requestScope.SHOHINCODECORRELATIONERR }">
		<c:out value="${requestScope.SHOHINCODECORRELATIONERR }" />
    </c:if>
  </center>
  </body>
</html>