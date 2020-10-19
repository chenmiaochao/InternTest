<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
  <meta charset="UTF-8">
    <title>売上伝票 削除確認画面</title>
    <script type="text/javascript">
    <!--
    // -->
    </script>
  </head>
  <body>

   <!-- LOGIN USER -->
  <c:set var="user" value="${ sessionScope.loginUser }"/>

  <!-- DATAS -->
  <c:set var="list" value="${ requestScope.uridenJViewList }"/>
  <c:set var="denNo" value="${ requestScope.denNo }"/>

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
               <td align="center">ようこそ&nbsp;<b>${ user.loginId }</b>&nbsp;さん</td>
               <td></td>
               <td><a href='<%=request.getContextPath()%>/ChangePasswordAction'>パスワード変更</a></td>
               <td></td>
               <td><input type="button" value=" ログアウト " onclick="location.href='<%=request.getContextPath()%>/LogoutAction'"></td>
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
               <td align="right"><a href='<%=request.getContextPath()%>/BackToMenuAction'>メニュー</a></td>
               <td align="center">&gt;&gt;</td>
               <td align="left"><b>売上伝票 削除確認画面</b></td>
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
                 売上伝票 削除確認画面<br>
                </b>
                </font>
               </td>
              </tr>

        	<c:set var= "Error" value ="${requestScope.message}"/>
        		<c:if test="${ not empty Error}">
        			<h3>${Error}</h3>
        		</c:if>

        		<c:if test="${empty Error}">

              <tr height="100">
               <td align="center" valign="middle">
                <font color="#6699FF">
                <b>
                 下記の売上伝票を削除します。<br>
                 ご確認の上、「削除」ボタンを押してください。<br>
                </b>
                </font>
               </td>
              </tr>
              <tr height="250">
               <td align="center" valign="top">
                <table border="0" cellspacing="0" cellpadding="5" bordercolor="#888888" width="540" height="150">
                 <!-- 伝票情報の入力 -->

				<tr>
                  <td align="center" valign="top">
                   <form action="">
                    <table border="1" cellspacing="0" cellpadding="10" width="100%" bordercolor="#888888" width="540" height="50">
                     <!-- 伝票番号 -->
                     <tr>
                      <td width="150" height="50" bgcolor="#CCCCCC">伝票番号</td>
                      <td>${ list.get(0).denNo_001 }</td>
                     </tr>
                     <!-- 売上日 -->
                     <tr>
                      <td width="150" height="50" bgcolor="#CCCCCC">売上日</td>
                      <td>${ list.get(0).uriDate_001 }</td>
                     </tr>
                     <!-- 得意先 -->
                     <tr>
                      <td width="150" height="50" bgcolor="#CCCCCC">得意先</td>
                      <td>${ list.get(0).tokuiName_003 }</td>
                     </tr>
                     <!-- 担当者 -->
                     <tr>
                      <td width="150" height="50" bgcolor="#CCCCCC">担当者</td>
                      <td>${ list.get(0).tantouName_004 }</td>
                     </tr>
                     <!-- 備考 -->
                     <tr>
                      <td width="150" height="100" bgcolor="#CCCCCC">備考</td>
                      <td>
                       <pre>${ list.get(0).memo_001 }</pre>
                      </td>
                     </tr>
                    </table>
                   </form>
                  </td>
                 </tr>
                </table>
                <table>
                 <!-- 明細 -->
                 <tr>
                  <td>
                   <table border="1" cellspacing="0" cellpadding="1" bordercolor="#888888">
                    <!-- 見出し -->
                    <tr bgcolor="#AAAAAA" height="30">
                     <th width="250">商品</th>
                     <th width="150">単価</th>
                     <th width="100">数量</th>
                     <th width="150">金額</th>
                    </tr>

                    </c:if>

                    <c:forEach items="${ list }" var="u" varStatus="s">
                     <!-- 明細 -->
                     <tr height="30">
                      <td>${ u.shohinName_002 }</td>
                      <td align="right">${ u.tanka_002 }</td>
                      <td align="right">${ u.suryo_002 }</td>
                      <td align="right">${ u.kingaku_002 }</td>
                     </tr>
                    </c:forEach>
                   </table>
                  </td>
                 </tr>

				<c:if test="${empty Error}">

                 <!-- 合計金額 -->
                 <tr>
                  <td align="right" valign="top">
                   <table border="1" cellspacing="0" cellpadding="1" bordercolor="#888888">
                    <tr height="30">
                     <td bgcolor="#AAAAAA" width="100">合計金額</td>
                     <td width="150" align="right">${ list.get(0).totalCost_001 }</td>
                    </tr>
                   </table>
                  </td>
                 </tr>

                 </c:if>

                 <!-- ボタン -->
                 <tr>
                  <td align="center" valign="top">
                   <input type="submit" value="　戻る　"  onclick="location.href='<%=request.getContextPath()%>/UA001003_BackAction'"/>

                   <c:if test="${empty Error}">
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   <input type="submit" value="　削除　" onclick="location.href='<%=request.getContextPath()%>/UA001003_DecideAction?denNo=${list.get(0).denNo_001}&updateTime=${list.get(0).updateTime_001}'"/>
                   </c:if>

                  </td>
                 </tr>
                </table>

               </td>
              </tr>
              <tr height="50">
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