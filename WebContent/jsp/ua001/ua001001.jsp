<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "sdcj.nsk.pj001.dto.UA001001DetailDto" %>
<%@ page import = "java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>売上伝票 一覧画面</title>
  </head>

  <body>

  <!-- LOGIN USER -->
  <c:set var="user" value="${ sessionScope.loginUser }"/>

  <!-- DATAS -->
  <c:set var="list" value="${ requestScope.ua001001DetailList }"/>
  <c:set var="currentPage" value ="${ requestScope.page_num }"/>
  <c:set var="size" value="${ requestScope.listsize }"/>

  <!-- ERRORS -->
  <c:set var="halfWidthDigitErr" value="${ requestScope.HALFWIDTHDIGITERR }"/>
  <c:set var="dennoLengthErr" value="${ requestScope.DENNOLENGTHERR }"/>
  <c:set var="uridateLengthErr" value="${ requestScope.URIDATELENGTHERR }"/>
  <c:set var="dennoRangeErr" value="${ requestScope.DENNORANGEERR }"/>
  <c:set var="dennoCorrelationErr" value="${ requestScope.DENNOCORRELATIONERR }"/>
  <c:set var="uridateCorrelationErr" value="${ requestScope.URIDATECORRELATIONERR }"/>
  <c:set var="uridateInvalidErr" value="${ requestScope.URIDATEINVALIDERR }"/>
  <c:set var="noRecordErr" value="${ requestScope.NORECORDERR }"/>

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
               <td align="center">ようこそ&nbsp;<b>${ user.loginId }</b>&nbsp;さん</td>
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
               <td align="left"><b>売上伝票 一覧画面</b></td>
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
                 売上伝票 一覧画面<br>
                </b>
                </font>
               </td>
              </tr>
              <tr height="100">
               <td align="center" valign="top">
                <table border="0" cellspacing="0" cellpadding="0">
                 <tr>
                 <!-- 新規登録 -->
                  <td>
                   売上伝票を<input type="button" value="　新規登録　" onclick="location.href='UA001001_RegisterAction'">&nbsp;&nbsp;&nbsp;
                  </td>
                  <td>
                   <table border="1" cellspacing="0" cellpadding="1" bordercolor="#888888">
                    <tr>
                     <td>
                     <!-- 検索系の最小ボックス -->
                      <table border="0" cellspacing="0" cellpadding="5" width="540" height="150">
                       <!-- 検索用フォーム -->
                       <form method="get" action="UA001001_SearchAction">
                       <!-- 空白 -->
                        <tr height="0">
                         <td width="20"></td>
                         <td width="80"></td>
                         <td width="5"></td>
                         <td width="300"></td>
                        </tr>
                        <!-- 伝票番号 -->
                        <tr>
                         <td></td>
                         <td align="right">伝票番号</td>
                         <td></td>
                         <td>
                          <c:choose>
                           <c:when test="${ not empty requestScope.denno_min }">
                            <input type="text" size="20" name="denno_min" value="${ requestScope.denno_min }"/>
                           </c:when>
                           <c:when test="${ empty requestScope.denno_min }">
                            <input type="text" size="20" name="denno_min" value=""/>
                           </c:when>
                          </c:choose>
                          &nbsp;～&nbsp;
                          <c:choose>
                           <c:when test="${ not empty requestScope.denno_max }">
                            <input type="text" size="20" name="denno_max" value="${ requestScope.denno_max }"/>
                           </c:when>
                           <c:when test="${ empty requestScope.denno_max }">
                            <input type="text" size="20" name="denno_max" value=""/>
                           </c:when>
                          </c:choose>
                         </td>
                        </tr>
                        <!-- 売上日 -->
                        <tr>
                         <td></td>
                         <td align="right">売上日</td>
                         <td></td>
                         <td>
                          <c:choose>
                           <c:when test="${ not empty requestScope.uridate_min }">
                            <input type="text" size="20" name="uridate_min" value="${ requestScope.uridate_min }"/>
                           </c:when>
                           <c:when test="${ empty requestScope.uridate_min }">
                            <input type="text" size="20" name="uridate_min" value=""/>
                           </c:when>
                          </c:choose>
                          &nbsp;～&nbsp;
                          <c:choose>
                           <c:when test="${ not empty requestScope.uridate_max }">
                            <input type="text" size="20" name="uridate_max" value="${ requestScope.uridate_max }"/>
                           </c:when>
                           <c:when test="${ empty requestScope.uridate_max }">
                            <input type="text" size="20" name="uridate_max" value=""/>
                           </c:when>
                          </c:choose>
                         </td>
                        </tr>
                        <!-- ボタン -->
                        <tr height="50">
                         <td align="center" colspan="3"></td>
                         <td>
                          <input type="submit" value="　検索　">
                          <input type="button" value="　クリア　" onclick="location.href='UA001001_ClearAction'"/>
                         </td>
                        </tr>
                       </form>
                       <!-- 検索用フォーム ここまで -->
                      </table>
                     </td>
                    </tr>
                   </table>
                  </td>
                 </tr>
                </table>
               </td>
              </tr>
              <!-- 空行 -->
              <tr height="30">
               <td></td>
              </tr>
              <!-- 検索結果一覧 -->
              <tr height="">
               <td align="center" valign="top">

                <!-- エラーメッセージ -->

                <!-- halfWidthDigitErr -->
                <c:if test="${ not empty halfWidthDigitErr }">
                 <h3>
                  <c:out value="${ halfWidthDigitErr }"></c:out>
                 </h3>
                </c:if>

                <!-- dennoLengthErr -->
                <c:if test="${ not empty dennoLengthErr }">
                 <h3>
                  <c:out value="${ dennoLengthErr }"></c:out>
                 </h3>
                </c:if>

                <!-- uridateLengthErr -->
                <c:if test="${ not empty uridateLengthErr }">
                 <h3>
                  <c:out value="${ uridateLengthErr }"></c:out>
                 </h3>
                </c:if>

                <!-- dennoRangeErr -->
                <c:if test="${ not empty dennoRangeErr }">
                 <h3>
                  <c:out value="${ dennoRangeErr }"></c:out>
                 </h3>
                </c:if>

                <!-- dennoCorrelationErr -->
                <c:if test="${ not empty dennoCorrelationErr }">
                 <h3>
                  <c:out value="${ dennoCorrelationErr }"></c:out>
                 </h3>
                </c:if>

                <!-- uridateCorrelationErr -->
                <c:if test="${ not empty uridateCorrelationErr }">
                 <h3>
                  <c:out value="${ uridateCorrelationErr }"></c:out>
                 </h3>
                </c:if>

                <!-- uridateInvalidErr -->
                <c:if test="${ not empty noRecordErr }">
                 <h3>
                  <c:out value="${ noRecordErr }"></c:out>
                 </h3>
                </c:if>
                <!-- エラーメッセージ ここまで -->

                <table border="0" cellspacing="0" cellpadding="0">
                 <c:if test="${ not empty list }">
                 <fmt:formatNumber var="lastPage" value="${ (size div 10) + ((size div 10) % 1 == 0 ? 0 : 0.5) }" type="number" pattern="#" />
                  <table border="0" cellspacing="0" cellpadding="0">
                  <!-- ページ切り替えリンク -->
                  <tr height="30">
                   <td>
                    <c:choose>
                     <c:when test="${ currentPage eq 1 }">
                      &lt;&lt;前へ
                      <a href="${ pageContext.request.contextPath }/UA001001_PageChangeAction?denno_min=${ requestScope.denno_min }&denno_max=${ requestScope.denno_max }&uridate_min=${ requestScope.uridate_min }&uridate_max=${ requestScope.denno_max }&page_num=${ currentPage + 1 }">次へ&gt;&gt;</a> |
                      <c:out value="${ size }"></c:out>件中
                      <c:out value="1"></c:out>
                      ～
	                  <c:choose>
	                   <c:when test="${ size < currentPage * 10 }">
                	    <c:out value="${ ((currentPage - 1) * 10) + (size % 10) }"></c:out>
	                   </c:when>
                       <c:otherwise>
                        <c:out value="${ currentPage * 10 }"></c:out>
                       </c:otherwise>
                      </c:choose> 件
                 	 </c:when>
                 	 <c:when test="${ currentPage eq lastPage }">
                      <a href="${ pageContext.request.contextPath }/UA001001_PageChangeAction?denno_min=${ requestScope.denno_min }&denno_max=${ requestScope.denno_max }&uridate_min=${ requestScope.uridate_min }&uridate_max=${ requestScope.denno_max }&page_num=${ currentPage - 1 }">&lt;&lt;前へ</a> |
	                   次へ&gt;&gt; |
	                   <c:out value="${ size }"></c:out>件中
	                   <c:out value="${ (currentPage - 1) * 10 + 1 }"></c:out>
	                   ～
                       <c:out value="${ size }"></c:out> 件
                 	  </c:when>
                 	  <c:otherwise>
                 	   <a href="${ pageContext.request.contextPath }/UA001001_PageChangeAction?denno_min=${ requestScope.denno_min }&denno_max=${ requestScope.denno_max }&uridate_min=${ requestScope.uridate_min }&uridate_max=${ requestScope.denno_max }&page_num=${ currentPage - 1 }">&lt;&lt;前へ</a> |
                 	   <a href="${ pageContext.request.contextPath }/UA001001_PageChangeAction?denno_min=${ requestScope.denno_min }&denno_max=${ requestScope.denno_max }&uridate_min=${ requestScope.uridate_min }&uridate_max=${ requestScope.denno_max }&page_num=${ currentPage + 1 }">次へ&gt;&gt;</a> |
                  	   <c:out value="${ size }"></c:out>件中
                  	   <c:out value="${ (currentPage - 1) * 10 + 1 }"></c:out>
                 	   ～
                 	   <c:choose>
                 	    <c:when test="${ size < currentPage * 10 }">
                 	     <c:out value="${ ((currentPage - 1) * 10) + (size % 10) }"></c:out>
                 	    </c:when>
                 	    <c:otherwise>
                 	     <c:out value="${ currentPage * 10 }"></c:out>
                 	    </c:otherwise>
                 	   </c:choose> 件
                	  </c:otherwise>
                 	 </c:choose>

                     <c:if test="${ size le 50 }">
                      <c:forEach begin = "1" end = "${ lastPage }" varStatus="loop">
                	   <c:choose>
                	    <c:when test="${ loop.index eq currentPage }">
                	     <c:out value="${ loop.index }"></c:out>
                	    </c:when>
                	    <c:otherwise>
                         <a href="${ pageContext.request.contextPath }/UA001001_PageChangeAction?denno_min=${ requestScope.denno_min }&denno_max=${ requestScope.denno_max }&uridate_min=${ requestScope.uridate_min }&uridate_max=${ requestScope.denno_max }&page_num=${ loop.index }"><c:out value="${ loop.index }"></c:out></a>
                        </c:otherwise>
                       </c:choose>
                      </c:forEach>
                     </c:if>
                     <c:if test="${size gt 50 }">
                      <c:if test="${ currentPage lt 4 }">
                       <c:forEach begin = "1" end = "6" varStatus="loop">
                        <c:choose>
                         <c:when test="${ loop.index eq currentPage }">
                          <c:out value="${ loop.index }"></c:out>
                         </c:when>
                         <c:when test="${ loop.index eq 6 }">
                          <c:out value="…"></c:out>
                         </c:when>
                         <c:otherwise>
                          <a href="${ pageContext.request.contextPath }/UA001001_PageChangeAction?denno_min=${ requestScope.denno_min }&denno_max=${ requestScope.denno_max }&uridate_min=${ requestScope.uridate_min }&uridate_max=${ requestScope.denno_max }&page_num=${ loop.index }"><c:out value="${ loop.index }"></c:out></a>
                         </c:otherwise>
                        </c:choose>
                       </c:forEach>
                      </c:if>
                      <c:if test="${ currentPage ge 4 }">
                       <c:forEach begin = "${ currentPage - 3}" end = "${ lastPage }" varStatus="loop">
                        <c:choose>
                         <c:when test="${ loop.index eq currentPage }">
                          <c:out value="${ loop.index }"></c:out>
                         </c:when>
                         <c:when test="${ loop.index eq (currentPage - 3) && loop.index lt 8 }">
                          <c:out value="…"></c:out>
                         </c:when>
                         <c:when test="${ loop.index eq (currentPage + 3) }">
                          <c:out value="…"></c:out>
                         </c:when>
                         <c:when test="${ loop.index gt (currentPage + 3) }">
                         </c:when>
                         <c:otherwise>
                          <a href="${ pageContext.request.contextPath }/UA001001_PageChangeAction?denno_min=${ requestScope.denno_min }&denno_max=${ requestScope.denno_max }&uridate_min=${ requestScope.uridate_min }&uridate_max=${ requestScope.denno_max }&denno_min=${ requestScope.denno_min }&denno_max=${ requestScope.denno_max }&uridate_min=${ requestScope.uridate_min }&uridate_max=${ requestScope.denno_max }&page_num=${ loop.index }"><c:out value="${ loop.index }"></c:out></a>
                         </c:otherwise>
                        </c:choose>
                       </c:forEach>
                      </c:if>
                     </c:if>
                    </td>
                   </tr>
                  </table>
                 </c:if>
                 <!-- ページ切り替えリンク　ここまで -->

                 <!-- 明細テーブル -->
                 <tr>
                  <td>
                   <table border="1" cellspacing="0" cellpadding="1" bordercolor="#888888">

                    <!-- 見出し -->
                    <tr bgcolor="#AAAAAA">
                     <th>操作</th>
                     <th width="120">伝票番号</th>
                     <th width="100">売上日</th>
                     <th width="300">得意先</th>
                     <th width="150">担当者</th>
                     <th width="100">商品数量</th>
                     <th width="150">金額</th>
                    </tr>

                    <!-- 明細 -->
                    <c:forEach items="${ list }" var="u" varStatus="s">
                     <tr>
                      <td>
                       <input type="button" value="　照会　" onclick="location.href='UA001001_ShowAction?denNo=${ u.denNo }'"/>
                       <input type="button" value="　編集　" onclick="location.href='UA001001_EditAction?denNo=${ u.denNo }'"/>
                       <input type="button" value="　削除　" onclick="location.href='UA001001_DeleteAction?denNo=${ u.denNo }'" />
                      </td>
                      <td>${ u.denNo }</td>
                      <td>${ u.uriDate }</td>
                      <td>${ u.tokuiName }</td>
                      <td>${ u.tantouName }</td>
                      <td align="right"><c:out value="${ u.suryo }" /></td>
                      <td align="right"><c:out value="${ u.goukei }" /></td>
                     </tr>
                    </c:forEach>

                   </table>
                  </td>
                 </tr>
                </table>
               </td>
              </tr>
              <!-- 空行 -->
              <tr height="20">
               <td></td>
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
