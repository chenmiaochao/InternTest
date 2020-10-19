<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.Timestamp" %>
<%@ page import="sdcj.nsk.pj001.dto.AccountTableDto" %>
<%
	AccountTableDto loginUser = (AccountTableDto)session.getAttribute("loginUser");
%>
<%

String mode = (String)request.getAttribute("MODE");

String shohincode = "";
String shohinname = "";
String tanka = "";
String oldcode = "";
String disabledA = "";
String disabledB = "";
String display = "";
Timestamp updatetime = null;

if(mode.equals("2")){

	shohincode = (String)request.getAttribute("SHOHINCODE");
	shohinname = (String)request.getAttribute("SHOHINNAME");
	tanka = (String)request.getAttribute("TANKA");
	oldcode = (String)request.getAttribute("SHOHINCODE");
	disabledA = "disabled";
	updatetime = (Timestamp)request.getAttribute("UPDATETIME");
}else if(mode.equals("1")){
	display = "display:none";
}else if(mode.equals("0")){
	disabledB = "disabled";
}

%>

<html>
  <head>
    <title>商品マスタ 登録／編集画面</title>
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
               <td><a href="./CM004001.html">パスワード変更</a></td>
               <td></td>
               <td><input type="button" value=" ログアウト " onclick="location.href='<%=request.getContextPath() %>/LogoutAction'" ></td>
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
               <td align="left"><b>商品マスタ 登録／編集画面</b></td>
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
                 商品マスタ 登録／編集画面<br>
                </b>
                </font>
               </td>
              </tr>
              <tr height="100">
               <td align="center" valign="middle">
                <font color="#FF0000">
                <b>



                <!-- 編集エラー -->

                <c:set var="editError" value ="${requestScope.EDITERROR }" />

                <!-- editErrorが空文字またはNULLでない場合 -->
                <c:if test="${ not empty editError}">
                 <h3 style="color:red" >
                  <c:out value="${ editError}"></c:out>
                 </h3>
                </c:if>


				<!-- 必須チェック -->

                <c:set var="shohinCodeEmpty" value ="${requestScope.SHOHINCODEEMPTY }" />
                <c:set var="shohinNameEmpty" value ="${requestScope.SHOHINNAMEEMPTY }" />
                <c:set var="tankaEmpty" value ="${requestScope.TANKAEMPTY }" />

                <!-- shohinCodeEmptyが空文字またはNULLでない場合 -->
                <c:if test="${ not empty shohinCodeEmpty}">
                 <h3 style="color:red">
                  <c:out value="${ shohinCodeEmpty}"></c:out>
                 </h3>
                </c:if>

                <!-- shohinNameEmptyが空文字またはNULLでない場合 -->
                <c:if test="${ not empty shohinNameEmpty}">
                 <h3 style="color:red">
                  <c:out value="${ shohinNameEmpty}"></c:out>
                 </h3>
                </c:if>

                <!-- tankaEmptyが空文字またはNULLでない場合 -->
                <c:if test="${ not empty tankaEmpty}">
                 <h3 style="color:red">
                  <c:out value="${ tankaEmpty}"></c:out>
                 </h3>
                </c:if>


                <!-- 最大長チェック -->

                <c:set var="checkCodeLength" value ="${requestScope.CHECKCODELENGTH }" />
                <c:set var="checkNameLength" value ="${requestScope.CHECKNAMELENGTH }" />

                <!-- checkCodeLengthが空文字またはNULLでない場合 -->
                <c:if test="${ not empty checkCodeLength}">
                 <h3 style="color:red">
                  <c:out value="${ checkCodeLength}"></c:out>
                 </h3>
                </c:if>

                <!-- checkNameLengthが空文字またはNULLでない場合 -->
                <c:if test="${ not empty checkNameLength}">
                 <h3 style="color:red">
                  <c:out value="${ checkNameLength}"></c:out>
                 </h3>
                </c:if>


                <!-- 範囲チェック -->

                <c:set var="checkTankaRange" value ="${requestScope.CHECKTANKARANGE }" />

                <!-- checkTankaRangeが空文字またはNULLでない場合 -->
                <c:if test="${ not empty checkTankaRange}">
                 <h3 style="color:red">
                  <c:out value="${ checkTankaRange}"></c:out>
                 </h3>
                </c:if>


                <!-- 半角チェック -->

                <c:set var="codeHalfWidthErr" value ="${requestScope.CODEHALFWIDTHERR }" />
                <c:set var="tankaHalfWidthErr" value ="${requestScope.TANKAHALFWIDTHERR }" />

                <!-- codeHalfWidthErrが空文字またはNULLでない場合 -->
                <c:if test="${ not empty codeHalfWidthErr}">
                 <h3 style="color:red">
                  <c:out value="${ codeHalfWidthErr}"></c:out>
                 </h3>
                </c:if>

                <!-- tankaHalfWidthErrが空文字またはNULLでない場合 -->
                <c:if test="${ not empty tankaHalfWidthErr}">
                 <h3 style="color:red">
                  <c:out value="${ tankaHalfWidthErr}"></c:out>
                 </h3>
                </c:if>


                <!-- 登録チェック -->

                <c:set var="registerdCode" value ="${requestScope.REGISTEREDCODE }" />
                <c:set var="noData" value ="${requestScope.NODATA }" />
                <c:set var="differTime" value ="${requestScope.DIFFERTIME }" />

                <!-- registerdCodeが空文字またはNULLでない場合 -->
                <c:if test="${ not empty registerdCode}">
                 <h3 style="color:red">
                  <c:out value="${ registerdCode}"></c:out>
                 </h3>
                </c:if>

                <!-- noDataが空文字またはNULLでない場合 -->
                <c:if test="${ not empty noData}">
                 <h3 style="color:red">
                  <c:out value="${ noData}"></c:out>
                 </h3>
                </c:if>

                <!-- differTimeが空文字またはNULLでない場合 -->
                <c:if test="${ not empty differTime}">
                 <h3 style="color:red">
                  <c:out value="${ differTime}"></c:out>
                 </h3>
                </c:if>


                </b>
                </font>
               </td>
              </tr>

              <form action="<%=request.getContextPath() %>/MM001002_RegisterAction">
              <tr height="250">
               <td align="center" valign="top" >
                <table border="0" cellspacing="0" cellpadding="5" bordercolor="#888888" width="540" height="150">
                 <!-- 商品情報の入力 -->
                 <tr style="<%= display %>">
                  <td align="center" valign="top">
                    <table border="1" cellspacing="0" cellpadding="10" width="100%" bordercolor="#888888" width="540" height="50">
                     <!-- 商品コード -->
                     <tr>
                      <td width="150" height="50" bgcolor="#CCCCCC">商品コード</td>
                      <td>
                        <input type="text" size="40" maxlength="256" name="shohinCode"  value="<%= shohincode %>" <%= disabledA %>>
                      </td>
                     </tr>
                     <!-- 商品名 -->
                     <tr>
                      <td width="150" height="50" bgcolor="#CCCCCC">商品名</td>
                      <td>
                       <input type="text" size="60" maxlength="256" name="shohinName" value="<%= shohinname %>" />
                      </td>
                     </tr>
                     <!-- 単価 -->
                     <tr>
                      <td width="150" height="50" bgcolor="#CCCCCC">単価</td>
                      <td>
                       <input type="text" size="40" maxlength="256" name="tanka"  value="<%= tanka %>"/>
                      </td>
                     </tr>
                    </table>

                  </td>
                 </tr>

                 <!-- ボタン -->
                 <tr>
                  <td align="center" valign="top">
                   <input type="button" value="　戻る　" onclick="location.href='<%=request.getContextPath() %>/MM001002_BackAction'" />
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   <input type="submit" value="　登録　" style="<%= display %>">
                   <input type="hidden" name="mode" value="<%=mode %>">
                   <input type="hidden" name="shohinCode" value="<%=shohincode %>" <%= disabledB %>>
                   <input type="hidden" name="updateTime" value="<%=updatetime %>" <%= disabledB %>>
                  </td>
                 </tr>
                </table>
               </td>
              </tr>
              </form>


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