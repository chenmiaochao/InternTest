<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>担当者選択 補助検索画面</title>
    <script type="text/javascript">
    <!--
    // -->
    </script>
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
<!--
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
               <td width="80"></td>
               <td width="10"></td>
               <td width="50"></td>
               <td width="10"></td>
              </tr>
              <tr>
               <td></td>
               <td align="center">ようこそ&nbsp;<b>hogehoge</b>&nbsp;さん</td>
               <td></td>
               <td><a href="./CM004001.html">パスワード変更</a></td>
               <td></td>
               <td><input type="button" value=" ログアウト "></td>
               <td></td>
              </tr>
             </table>

            </td>
           </tr>
          </table>
         </td>
        </tr>
-->
        <!-- 共通(ユーザ名・パスワード変更・ログアウトボタン) ここまで -->

        <!-- 共通(パンくず) ここから -->
<!--
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
               <td align="right"><a href="./CM002001.html">メニュー</a></td>
               <td align="center">&gt;&gt;</td>
               <td align="left"><b>商品選択 補助検索画面</b></td>
               <td></td>
              </tr>
             </table>

            </td>
           </tr>
          </table>
         </td>
        </tr>
-->
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
                 担当者選択 補助検索画面<br>
                </b>
                </font>
               </td>
              </tr>
              <tr height="100">
               <td align="center" valign="top">
                <table border="0" cellspacing="0" cellpadding="0">
                 <tr>
                  <td>
                   <table border="1" cellspacing="0" cellpadding="1" bordercolor="#888888">
                    <tr>
                     <td>
                     <form action="<%=request.getContextPath()%>/HK003001_SearchAction" method="POST">
                      <table border="0" cellspacing="0" cellpadding="5" width="540" height="150">
                       <tr height="0">
                        <td width="20"></td>
                        <td width="80"></td>
                        <td width="5"></td>
                        <td width="300"></td>
                       </tr>
                       <!-- 商品コード -->
                       <tr>
                        <td></td>
                        <td align="right">担当者コード</td>
                        <td></td>
                        <td>
                         <input name="startCode" type="text" size="20" value="${requestScope.STARTCODE }">
                         &nbsp;～&nbsp;
                         <input name = "endCode" type="text" size="20" value="${requestScope.ENDCODE }">
                        </td>
                       </tr>
                       <!-- 商品名 -->
                       <tr>
                        <td></td>
                        <td align="right">担当者名</td>
                        <td></td>
                        <td>
                         <input name="tantouName" type="text" size="40" value="${requestScope.TANTOUNAME }">
                        </td>
                       </tr>
                       <!-- ボタン -->
                       <tr height="50">
                        <td align="center" colspan="3"></td>
                        <td>
                         <input type="submit" value="　検索　"/>
                         <input type="button" value="　クリア　" onclick="location.href='<%=request.getContextPath()%>/HK003001_ClearAction'"/>
                         <input type="button" value="　閉じる　" onclick="location.href='<%=request.getContextPath()%>/HK003001_CloseAction'"/>

                        </td>
                       </tr>
                      </table>
					</form>
                     </td>
                    </tr>
                   </table>
                  </td>
                 </tr>
                </table>
               </td>
              </tr>
              <!-- 空行 -->
              <tr height="30"><td></td></tr>
              <!-- 検索結果一覧 -->
              <tr height="150">
               <td align="center" valign="top">
              <c:set var="size" value="${requestScope.LISTSIZE}" />
              <c:set var="list" value="${requestScope.RESULTLIST}" />
              <c:set var="currentPage" value ="${requestScope.PAGE }"/>
              <c:set var= "codeLengthErr" value ="${requestScope.CODELENGTHERR }"/>
              <c:set var="halfWidthErr" value ="${requestScope.HALFWIDTHERR }" />
              <c:set var ="tantouLengthErr" value ="${requestScope.NAMEENCODEERR }"/>
              <c:set var ="checkEmpty" value ="${requestScope.CHECKEMPTY }"/>
              <c:set var ="noResult" value ="${requestScope.NORESULT }"/>
              <c:if test="${ not empty checkEmpty}">
              		<h3><c:out value="${ checkEmpty}"></c:out> </h3>
              </c:if>
              <c:if test="${ not empty codeLengthErr}">
              		<h3><c:out value="${ codeLengthErr}"></c:out> </h3>
              </c:if>
              <c:if test="${ not empty halfWidthErr}">
              		<h3><c:out value="${ halfWidthErr}"></c:out> </h3>
              </c:if>
              <c:if test="${ not empty tantouLengthErr}">
              		<h3><c:out value="${ shohinLengthErr}"></c:out> </h3>
              </c:if>
              <c:if test="${ not empty noResult}">
              		<h3><c:out value="${ noResult}"></c:out> </h3>
              </c:if>
              <c:if test="${not empty list}">
			  <fmt:formatNumber var="lastPage" value="${(size div 10) + ((size div 10) % 1 == 0 ? 0 : 0.5) }"  type="number" pattern="#" />
                <table border="0" cellspacing="0" cellpadding="0">
                 <!-- ページ切り替えリンク -->
                 <tr height="10">
                 	<td>
                 		<c:choose>
                 			<c:when test="${lastPage eq 1 }">
                 				&lt;&lt;前へ | 次へ&gt;&gt; | <c:out value="${size }"></c:out>件中
                 				<c:out value="1"></c:out>～<c:out value="${size }"></c:out> 件
                 			</c:when>
                 			<c:when test="${currentPage eq 1 }">
                 				&lt;&lt;前へ |
                 				<a href="<%=request.getContextPath() %>/HK003001_PageChangeAction?page=${currentPage +1 }&startCode=${requestScope.STARTCODE }&endCode=${requestScope.ENDCODE }&tantouName=${requestScope.TANTOUENCODE }">次へ&gt;&gt;</a> |
	                 				<c:out value="${size }"></c:out>件中
	                 		 	<c:out value="1"></c:out>～<c:out value="10"></c:out> 件
                 			</c:when>
                 			<c:when test="${currentPage eq lastPage }">
								<a href="<%=request.getContextPath() %>/HK003001_PageChangeAction?page=<c:out value="${currentPage -1 }"/>&startCode=${requestScope.STARTCODE }&endCode=${requestScope.ENDCODE }&shohinName="${requestScope.NAMEENCODE}">&lt;&lt;前へ</a> |
	                 			次へ&gt;&gt; |
	                 				<c:out value="${size }"></c:out>件中
	                 			 <c:out value="${requestScope.NUMBERFIRST }"></c:out>～<c:out value="${requestScope.NUMBERLAST }"></c:out> 件
                 			</c:when>
                 			<c:otherwise>
                 				<a href="<%=request.getContextPath() %>/HK003001_PageChangeAction?page=${currentPage -1 }&startCode=${requestScope.STARTCODE }&endCode=${requestScope.ENDCODE }&tantouName=${requestScope.TANTOUENCODE }">&lt;&lt;前へ</a> |
	                 			<a href="<%=request.getContextPath() %>/HK003001_PageChangeAction?page=${currentPage +1 }&startCode=${requestScope.STARTCODE }&endCode=${requestScope.ENDCODE }&tantouName=${requestScope.TANTOUENCODE }">次へ&gt;&gt;</a> |
	                 				<c:out value="${size }"></c:out>件中
	                 			 <c:out value="${requestScope.NUMBERFIRST }"></c:out>～<c:out value="${requestScope.NUMBERLAST }"></c:out> 件
                 			</c:otherwise>
                 		</c:choose>

						<c:if test="${size le 50 }">
							<c:forEach begin = "1" end = "${lastPage }" varStatus="loop">
                				<c:choose>
                					<c:when test="${loop.index eq currentPage }">
                						<c:out value="${loop.index }"></c:out>
                					</c:when>
                					<c:otherwise>
										<a href="<%=request.getContextPath() %>/HK003001_PageChangeAction?page=${loop.index }&startCode=${requestScope.STARTCODE }
										&endCode=${requestScope.ENDCODE }&tantouName=${requestScope.TANTOUENCODE }"><c:out value="${loop.index }"></c:out></a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
						<c:if test="${size ge 51 }">
							<c:if test="${currentPage lt 4 }">
									<c:forEach begin = "1" end = "6" varStatus="loop">
										<c:choose>
		                					<c:when test="${loop.index eq currentPage }">
		                						<c:out value="${loop.index }"></c:out>
		                					</c:when>
		                					<c:when test="${loop.index eq 6 }">
		                						<c:out value="…"></c:out>
		                					</c:when>
		                					<c:otherwise>
												<a href="<%=request.getContextPath() %>/HK003001_PageChangeAction?page=${loop.index }&startCode=${requestScope.STARTCODE }&endCode=${requestScope.ENDCODE }&tantouName=${requestScope.TANTOUENCODE }"><c:out value="${loop.index }"></c:out></a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							</c:if>
							<c:if test="${currentPage ge 4}">
								<c:forEach begin = "${currentPage -3}" end = "${lastPage}" varStatus="loop">
									<c:choose>
	                					<c:when test="${loop.index eq currentPage }">
	                						<c:out value="${loop.index }"></c:out>
	                					</c:when>
	                					<c:when test="${loop.index eq (currentPage -3) && loop.index lt 8 }">
	                						<c:out value="…"></c:out>
	                					</c:when>
	                					<c:when test="${loop.index eq (currentPage +3) }">
	                						<c:out value="…"></c:out>
	                					</c:when>
										<c:when test="${loop.index gt (currentPage +3) }">

	                					</c:when>
	                					<c:otherwise>
											<a href="<%=request.getContextPath() %>/HK003001_PageChangeAction?page=${loop.index }&startCode=${requestScope.STARTCODE }&endCode=${requestScope.ENDCODE }&tantouName=${requestScope.TANTOUENCODE }"><c:out value="${loop.index }"></c:out></a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
						</c:if>
                  	</td>
                 </tr>
                 <tr>
                  <td>

                   <table border="1" cellspacing="0" cellpadding="1" bordercolor="#888888">
                    <!-- 見出し -->
                    <tr bgcolor="#AAAAAA">
                     <th>操作</th>
                     <th width="220">担当者コード</th>
                     <th width="550">担当者名</th>
                    </tr>
                    <!-- 明細 -->
                    <c:forEach var="rows" items="${list}">
                    <form action="<%=request.getContextPath() %>/HK003001_SelectAction" method="post">
                    <tr>

                    <input type="hidden" name="tantouCode" value ="${rows.tantouCode }">
                    <input type="hidden" name="tantouName" value ="${rows.tantouName }">
                     <td><input type="submit" value="　選択　" /></td>
                     <td>${rows.tantouCode }</td>
                     <td>${rows.tantouName }</td>

                   </tr>
                   </form>
                   </c:forEach>

                   </table>

                  </td>
                 </tr>
                </table>
              </c:if>
               </td>
              </tr>
              <!-- 空行 -->
              <tr height="20"><td></td></tr>
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