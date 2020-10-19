<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="sdcj.nsk.pj001.dto.AccountTableDto" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>各種データ出力画面</title>
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
               <td width="80"></td>
               <td width="20"></td>
               <td width="80"></td>
               <td></td>
              </tr>
              <tr>
              <!-- リンク先保留 -->
               <td align="right"><a href="./BackToMenuAction">メニュー</a></td>
               <td align="center">&gt;&gt;</td>
               <td align="left"><b>各種データ出力画面</b></td>
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
                 各種データ出力<br>
                </b>
                </font>
               </td>
              </tr>
              <tr height="250">
              <td>
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
               <td align="center" valign="top">
               <form>
	                <table border="0" cellspacing="0" cellpadding="5" bordercolor="#888888" width="900" height="150">
	                 <tr height="0">
	                  <td width="80"></td>
	                  <td width="80"></td>
	                  <td width="5"></td>
	                  <td width="650"></td>
	                 </tr>
	                 <!-- 伝票番号 -->
	                 <tr>
	                  <td></td>
	                  <td align="right">伝票番号</td>
	                  <td></td>
	                  <td>
	                   <input type="text" name="slipNumberFrom" size="20">
	                   &nbsp;～&nbsp;
	                   <input type="text" name="slipNumberTo" size="20">
	                  </td>
	                 </tr>
	                 <!-- 売上日 -->
	                 <tr>
	                  <td></td>
	                  <td align="right">売上日</td>
	                  <td></td>
	                  <td>
	                   <input type="text" name="salesDateFrom" size="10">
	                   &nbsp;～&nbsp;
	                   <input type="text" name="salesDateTo" size="10">
	                  </td>
	                 </tr>
	                 <!-- 得意先 -->
	                 <tr>
	                  <td></td>
	                  <td align="right">得意先</td>
	                  <td></td>
	                  <td>
		               <input type="button" value="選択" onclick="location.href='${ pageContext.request.contextPath }/CM005001_HK002_FromAction?uid=CM005001'">&nbsp;
		               <input name="customerFrom" type="text" size="20" value ="${sessionScope.TOKUICODEFROM }">&nbsp;～&nbsp;
	                   <input type="button" value="選択" onclick="location.href='${ pageContext.request.contextPath }/CM005001_HK002_ToAction?uid=CM005001'">&nbsp;
	                   <input name="customerTo"  type="text" size="20" value ="${sessionScope.TOKUICODETO }">
	                  </td>
	                 </tr>
	                 <!-- 担当者 -->
	                 <tr>
	                  <td></td>
	                  <td align="right">担当者</td>
	                  <td></td>
	                  <td>
		                   <input type="button" value="選択" onclick="location.href='${ pageContext.request.contextPath }/CM005001_HK003_FromAction?uid=CM005001'">&nbsp;
		                   <input name="tantouFrom" type="text" size="20" value ="${sessionScope.TANTOUCODEFROM }">&nbsp;～&nbsp;
		                   <input type="button" value="選択" onclick="location.href='${ pageContext.request.contextPath }/CM005001_HK003_ToAction?uid=CM005001'">&nbsp;
		                   <input name="tantouTo" type="text" size="20" value ="${sessionScope.TANTOUCODETO }">&nbsp;～&nbsp;
	                  </td>
	                 </tr>
	                 <!-- ボタン -->
	                 <tr height="50">
	                  <td align="center" colspan="3"></td>
	                  <td>
	                   <input type="submit" formaction="${ pageContext.request.contextPath }/CM005001_Nohin_DLAction" formmethod="get" value="　納品書CSVダウンロード　" />
	                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="submit" formaction="${ pageContext.request.contextPath }/CM005001_NH_DLAction" formmethod="get" value="　納品書ダウンロード　" />
	                  </td>
	                 </tr>
	                 <tr height="50">
	                  <td align="center" colspan="3"></td>
	                  <td>
	                   <input type="submit" formaction="${ pageContext.request.contextPath }/CM005001_Meisai_DLAction" formmethod="get" value="　売上明細書CSVダウンロード　" />
	                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="submit" formaction="${ pageContext.request.contextPath }/CM005001_UM_DLAction" formmethod="get" value="　売上明細書ダウンロード　" />
	                  </td>
	                 </tr>
	                 <tr height="50">
	                  <td align="center" colspan="3"></td>
	                  <td>
	                   <input type="submit" formaction="${ pageContext.request.contextPath }/CM005001_Shukei_DLAction" formmethod="get" value="　売上集計表CSVダウンロード　" />
	                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                   <input type="submit" formaction="${ pageContext.request.contextPath }/CM005001_US_DLAction" formmethod="get" value="　売上集計表ダウンロード　" />
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
    <c:if test="${not empty requestScope.error }">
					<c:out value="${requestScope.error }" />
    </c:if>
    <!-- 共通(見出し・外枠) end ここまで -->
  </center>
  </body>
</html>