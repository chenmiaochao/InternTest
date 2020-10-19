<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="sdcj.nsk.pj001.dto.AccountTableDto" %>
<%
	AccountTableDto loginUser = (AccountTableDto)session.getAttribute("loginUser");
%>
<%

String mode = (String)request.getAttribute("MODE");
String disabled = "";

if(!(request.getAttribute("ERRORFLG")==null)){
	int errorflg = (int)request.getAttribute("ERRORFLG");
	if(errorflg==1){
		disabled = "disabled";
	}
}

%>


<html>
<head>
 <title>商品マスタ 更新確認画面</title>
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
               <td align="left"><b>商品マスタ 更新確認画面</b></td>
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
                 商品マスタ 更新確認画面<br>
                </b>
                </font>
               </td>
              </tr>
              <tr height="100">
               <td align="center" valign="middle">
                <font color="#0000FF">
                <b <%= disabled%>>
                 下記の商品マスタを登録します。<br>
                 ご確認の上、「確定」ボタンを押してください。<br>
                </b>


				<c:set var="updateTime" value ="${requestScope.UPDATETIME }" />


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

                <c:set var="registeredCode" value ="${requestScope.REGISTEREDCODE }" />
                <c:set var="noData" value ="${requestScope.NODATA }" />
                <c:set var="insertMiss" value ="${requestScope.INSERTMISS }" />
                <c:set var="updateMiss" value ="${requestScope.UPDATEMISS }" />

                <!-- registeredCodeが空文字またはNULLでない場合 -->
                <c:if test="${ not empty registeredCode}">
                 <h3 style="color:red">
                  <c:out value="${ registeredCode}"></c:out>
                 </h3>
                </c:if>

                <!-- noDataが空文字またはNULLでない場合 -->
                <c:if test="${ not empty noData}">
                 <h3 style="color:red">
                  <c:out value="${ noData}"></c:out>
                 </h3>
                </c:if>

                <!-- insertMissが空文字またはNULLでない場合 -->
                <c:if test="${ not empty insertMiss}">
                 <h3 style="color:red">
                  <c:out value="${ insertMiss}"></c:out>
                 </h3>
                </c:if>

                <!-- updateMissが空文字またはNULLでない場合 -->
                <c:if test="${ not empty updateMiss}">
                 <h3 style="color:red">
                  <c:out value="${ updateMiss}"></c:out>
                 </h3>
                </c:if>


                </font>
               </td>
              </tr>

              <tr height="250">
               <td align="center" valign="top">

               <c:set var= "shohinCode" value ="${requestScope.SHOHINCODE }"/>
               <c:set var= "shohinName" value ="${requestScope.SHOHINNAME }"/>
               <c:set var= "tanka" value ="${requestScope.TANKA }"/>
               <c:set var= "mode" value ="${requestScope.MODE }"/>
               <c:set var= "updateTime" value ="${requestScope.UPDATETIME }"/>

                <form action="<%=request.getContextPath() %>/MM001003_DecideAction">
                <table border="0" cellspacing="0" cellpadding="5" bordercolor="#888888" width="540" height="150">
                 <!-- 商品情報の入力 -->
                 <tr>
                  <td align="center" valign="top">

                    <table border="1" cellspacing="0" cellpadding="10" width="100%" bordercolor="#888888" width="540" height="50">
                     <!-- 商品コード -->
                     <tr>
                      <td width="150" height="50" bgcolor="#CCCCCC">商品コード</td>
                      <td>
                       <c:out value="${ shohinCode}"></c:out>
                      </td>
                     </tr>
                     <!-- 商品名 -->
                     <tr>
                      <td width="150" height="50" bgcolor="#CCCCCC">商品名</td>
                      <td>
                       <c:out value="${ shohinName}"></c:out>
                      </td>
                     </tr>
                     <!-- 単価 -->
                     <tr>
                      <td width="150" height="50" bgcolor="#CCCCCC">単価</td>
                      <td>
                       <c:out value="${ tanka}"></c:out>
                      </td>
                     </tr>
                    </table>
                  </td>
                 </tr>
                 <!-- ボタン -->
                 <tr>
                  <td align="center" valign="top">
                   <input type="button" value="　戻る　" onclick="location.href='<%=request.getContextPath() %>/MM001003_BackAction?shohinCode=${shohinCode}&shohinName=${shohinName}&tanka=${tanka}&mode=${mode}&updateTime=${updateTime}'" />
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   <input type="submit" value="　登録　" <%=disabled %>>
                   <input type="hidden" name="shohinCode" value="${ shohinCode}">
                   <input type="hidden" name="shohinName" value="${ shohinName}">
                   <input type="hidden" name="tanka" value="${ tanka}">
                   <input type="hidden" name="mode" value="${ mode}">
                   <input type="hidden" name="updateTime" value="${ updateTime}">
                  </td>
                 </tr>
                </table>
                </form>
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
