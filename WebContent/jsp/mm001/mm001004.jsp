<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="sdcj.nsk.pj001.dto.AccountTableDto" %>
<%
	AccountTableDto loginUser = (AccountTableDto)session.getAttribute("loginUser");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%

String message = (String)request.getAttribute("MESSAGE");

%>

<html>
  <head>
    <title>商品マスタ 更新完了画面</title>
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
               <td><a href="<%=request.getContextPath() %>/ChangePasswordAction">パスワード変更</a></td>
               <td></td>
               <td><input type="button" value=" ログアウト " onclick="location.href='<%=request.getContextPath() %>/LogoutAction'"></td>
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
               <td align="right"><a href="<%=request.getContextPath() %>/BackToMenuAction">メニュー</a></td>
               <td align="center">&gt;&gt;</td>
               <td align="left"><b>商品マスタ 更新完了画面</b></td>
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
                 商品マスタ 更新完了画面<br>
                </b>
                </font>
               </td>
              </tr>
              <tr height="100">
               <td align="center" valign="middle">
                <font color="#0000FF">
                <b>
                 <c:set var="insertSuccess" value ="${requestScope.INSERTSUCCESS }" />
                 <c:set var="updateSuccess" value ="${requestScope.UPDATESUCCESS }" />
                 <c:set var="deleteSuccess" value ="${requestScope.DELETESUCCESS }" />

                <!-- insertSuccessが空文字またはNULLでない場合 -->
                <c:if test="${ not empty insertSuccess}">
                 <h3 style="color:blue">
                  <c:out value="${ insertSuccess}"></c:out>
                 </h3>
                </c:if>

                <!-- updateSuccessが空文字またはNULLでない場合 -->
                <c:if test="${ not empty updateSuccess}">
                 <h3 style="color:blue">
                  <c:out value="${ updateSuccess}"></c:out>
                 </h3>
                </c:if>

                <!-- deleteSuccessが空文字またはNULLでない場合 -->
                <c:if test="${ not empty updateSuccess}">
                 <h3 style="color:blue">
                  <c:out value="${ deleteSuccess}"></c:out>
                 </h3>
                </c:if>

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
                   <input type="submit" value="　商品マスタの新規登録　" onclick="location.href='<%=request.getContextPath() %>/MM001004_RegisterAction'"/>
                  </td>
                 </tr>
                 <tr>
                  <td align="center">
                   <input type="submit" value="　商品マスタ一覧画面へ　" onclick="location.href='<%=request.getContextPath() %>/MM001004_MM001Action'"/>
                  </td>
                 </tr>
                 <tr>
                  <td align="center">
                   <input type="submit" value="　メニュー画面へ　" onclick="location.href='<%=request.getContextPath() %>/BackToMenuAction'"/>
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