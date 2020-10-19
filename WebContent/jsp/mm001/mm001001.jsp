<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="sdcj.nsk.pj001.dto.AccountTableDto" %>
    <%
	AccountTableDto loginUser = (AccountTableDto)session.getAttribute("loginUser");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>商品マスタ一覧画面</title>
<script type="text/javascript">

        function delConfirm(itemNo,time) {
        	console.log(itemNo);
            msg = "商品コード[";
            msg = msg + itemNo;
            msg = msg + "]の商品マスタを削除します。";
            msg = msg + "\r\n";
            msg = msg + "削除処理を続けてよろしいですか？";
            var result=confirm(msg);
            if(result==true){
            	location.href='<%=request.getContextPath() %>/MM001001_DeleteAction?shohinCode='+itemNo+'&updateTime='+time;
            }else{
            	// キャンセルならアラートボックスを表示
                alert("削除をキャンセルしました。");
            }
        }
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
               <td align="left"><b>商品マスタ 一覧画面</b></td>
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


		<c:set var= "startcode" value ="${requestScope.STARTCODE }"/>
		<c:set var= "endcode" value ="${requestScope.ENDCODE }"/>
		<c:set var= "shohinname" value ="${requestScope.SHOHINNAME }"/>

             <!-- メッセージ -->
             <table width="100%">
              <tr height="20">
               <td align="left" valign="middle">
                <font color="#000000">
                <b>
                 商品マスタ 一覧画面<br>
                </b>
                </font>
               </td>
              </tr>
              <tr height="100">
               <td align="center" valign="top">
                <table border="0" cellspacing="0" cellpadding="0">
                 <tr>
                  <td>
                   商品マスタを<input type="button" value="　新規登録　" onclick="location.href='<%=request.getContextPath() %>/MM001001_RegisterAction'" />&nbsp;&nbsp;&nbsp;
                  </td>
                  <td>
                   <table border="1" cellspacing="0" cellpadding="1" bordercolor="#888888">
                    <tr>
                     <td>
                      <form action="<%=request.getContextPath()%>/MM001001_SearchAction" method="POST">
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
                         <td align="right">商品コード</td>
                         <td></td>
                         <td>
                          <input type="text" name="startCode" size="20" maxlength="20" value="${ startcode}">
                          &nbsp;～&nbsp;
                          <input type="text" name="endCode" size="20" maxlength="20" value="${ endcode}">
                         </td>
                        </tr>
                        <!-- 商品名 -->
                        <tr>
                         <td></td>
                         <td align="right">商品名</td>
                         <td></td>
                         <td>
                          <input name="shohinName" type="text" size="40" value="${ shohinname}">
                         </td>
                        </tr>
                        <!-- ボタン -->
                        <tr height="50">
                         <td align="center" colspan="3"></td>
                         <td>
                          <input type="submit" value="　検索　" />
                          <input type="button" value="　クリア　" onclick="location.href='<%=request.getContextPath()%>/MM001001_ClearAction'"/>
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

                <c:set var="size" value="${requestScope.LISTSIZE}" />     <!-- 商品マスタの該当レコードの件数取得 -->
                <c:set var="list" value="${requestScope.RESULTLIST}" />   <!-- 商品マスタの該当レコードを昇順で表示 -->
                <c:set var="currentPage" value ="${requestScope.PAGE }"/> <!-- 現在のページ -->
                <c:set var ="shohinLengthErr" value ="${requestScope.NAMEENCODEERR }"/>



                <!-- 商品コード大小エラー -->

                <c:set var ="compareErr" value ="${requestScope.CHECKCOMPAREERR }"/>

                <c:if test="${ not empty compareErr}">
                 <h3 style="color:red">
                  <c:out value="${ compareErr}"></c:out>
                 </h3>
                </c:if>


                <!-- 半角チェック -->

                <c:set var="halfWidthErrStart" value ="${requestScope.STARTHALFWIDTHERR }" />
                <c:set var="halfWidthErrEnd" value ="${requestScope.ENDHALFWIDTHERR }" />

                <!-- halfWidthErrStartが空文字またはNULLでない場合 -->
                <c:if test="${ not empty halfWidthErrStart}">
                 <h3 style="color:red">
                  <c:out value="${ halfWidthErrStart}"></c:out>
                 </h3>
                </c:if>

                <!-- halfWidthErrEndが空文字またはNULLでない場合 -->
                <c:if test="${ not empty halfWidthErrEnd}">
                 <h3 style="color:red">
                  <c:out value="${ halfWidthErrEnd}"></c:out>
                 </h3>
                </c:if>


                <!-- 最大長チェック -->

                <c:set var= "codeLengthErrStart" value ="${requestScope.CHECKSTARTLENGTH }"/>
                <c:set var= "codeLengthErrEnd" value ="${requestScope.CHECKENDLENGTH }"/>
                <c:set var= "codeLengthErrName" value ="${requestScope.CHECKNAMELENGTH }"/>

                <!-- codeLengthErrStartが空文字またはNULLでない場合 -->
                <c:if test="${ not empty codeLengthErrStart}">
                 <h3 style="color:red">
                  <c:out value="${ codeLengthErrStart}"></c:out>
                 </h3>
                </c:if>

                <!-- codeLengthErrEndが空文字またはNULLでない場合 -->
                <c:if test="${ not empty codeLengthErrEnd}">
                 <h3 style="color:red">
                  <c:out value="${ codeLengthErrEnd}"></c:out>
                 </h3>
                </c:if>

                <!-- codeLengthErrNameが空文字またはNULLでない場合 -->
                <c:if test="${ not empty codeLengthErrName}">
                 <h3 style="color:red">
                  <c:out value="${ codeLengthErrName}"></c:out>
                 </h3>
                </c:if>


				<!-- 削除チェック -->

				<c:set var ="emptyCode" value ="${requestScope.EMPTYCODE }"/>
				<c:set var ="differTime" value ="${requestScope.DIFFERTIME }"/>
				<c:set var ="missDelete" value ="${requestScope.MISSDELETE }"/>

                <!-- noResultが空文字またはNULLでない場合 -->
                <c:if test="${ not empty emptyCode}">
              	 <h3 style="color:red">
              	  <c:out value="${ emptyCode}"></c:out>
              	 </h3>
                </c:if>

                <!-- differTimeが空文字またはNULLでない場合 -->
                <c:if test="${ not empty differTime}">
              	 <h3 style="color:red">
              	  <c:out value="${ differTime}"></c:out>
              	 </h3>
                </c:if>

                <!-- noResultが空文字またはNULLでない場合 -->
                <c:if test="${ not empty missDelete}">
              	 <h3 style="color:red">
              	  <c:out value="${ missDelete}"></c:out>
              	 </h3>
                </c:if>



                <!-- listが空文字またはNULLでない場合 -->
                <c:if test="${not empty list}">
                 <fmt:formatNumber var="lastPage" value="${(size div 10) + ((size div 10) % 1 == 0 ? 0 : 0.5) }"  type="number" pattern="#" /> <%-- pattern属性を直接指定してフォーマット変換する。(数値。ゼロの場合は、非表示。) --%>
                 <table border="0" cellspacing="0" cellpadding="0">
                  <!-- ページ切り替えリンク -->
                  <tr height="10">
                 	<td>

                 		<c:choose>

                 			<%-- 検索件数が1件以上10件以下の時 --%>
                 			<c:when test="${lastPage eq 1 }">
                 				&lt;&lt;前へ | 次へ&gt;&gt; | <c:out value="${size }"></c:out>件中
                 				<c:out value="1"></c:out>～<c:out value="${size }"></c:out> 件
                 			</c:when>

                 			<%-- 1ページ目の時 --%>
                 			<c:when test="${currentPage eq 1 }">
                 				&lt;&lt;前へ |
                 				<a href="<%=request.getContextPath() %>/MM001001_PageChangeAction?page=${currentPage +1 }&startCode=${requestScope.STARTCODE }&endCode=${requestScope.ENDCODE }&shohinName=${requestScope.NAMEENCODE }">次へ&gt;&gt;</a> |
	                 				<c:out value="${size }"></c:out>件中
	                 		 	<c:out value="1"></c:out>～<c:out value="10"></c:out> 件
                 			</c:when>

                 			<%-- 最終ページの時 --%>
                 			<c:when test="${currentPage eq lastPage }">
								<a href="<%=request.getContextPath() %>/MM001001_PageChangeAction?page=${currentPage -1 }&startCode=${requestScope.STARTCODE }&endCode=${requestScope.ENDCODE }&shohinName=${requestScope.NAMEENCODE }">&lt;&lt;前へ</a> |
	                 			次へ&gt;&gt; |
	                 				<c:out value="${size }"></c:out>件中
	                 			 <c:out value="${requestScope.NUMBERFIRST }"></c:out>～<c:out value="${requestScope.NUMBERLAST }"></c:out> 件
                 			</c:when>

                 			<%-- 2ページ目から最終-1ページまで --%>
                 			<c:otherwise>
                 				<a href="<%=request.getContextPath() %>/MM001001_PageChangeAction?page=${currentPage -1 }&startCode=${requestScope.STARTCODE }&endCode=${requestScope.ENDCODE }&shohinName=${requestScope.NAMEENCODE }">&lt;&lt;前へ</a> |
	                 			<a href="<%=request.getContextPath() %>/MM001001_PageChangeAction?page=${currentPage +1 }&startCode=${requestScope.STARTCODE }&endCode=${requestScope.ENDCODE }&shohinName=${requestScope.NAMEENCODE }">次へ&gt;&gt;</a> |
	                 				<c:out value="${size }"></c:out>件中
	                 			 <c:out value="${requestScope.NUMBERFIRST }"></c:out>～<c:out value="${requestScope.NUMBERLAST }"></c:out> 件
                 			</c:otherwise>

                 		</c:choose>

						<%-- sizeが50以下の時 --%>
                 		<c:if test="${size le 50 }">
							<c:forEach begin = "1" end = "${lastPage }" varStatus="loop">
                				<c:choose>

                					<%-- loop.indexがcurrentPageと等しいとき --%>
                					<c:when test="${loop.index eq currentPage }">
                						<c:out value="${loop.index }"></c:out>
                					</c:when>

                					<%-- loop.indexがcurrentPageと等しくないとき --%>
                					<c:otherwise>
										<a href="<%=request.getContextPath() %>/MM001001_PageChangeAction?page=${loop.index }&startCode=${requestScope.STARTCODE }
										&endCode=${requestScope.ENDCODE }&shohinName=${requestScope.NAMEENCODE }"><c:out value="${loop.index }"></c:out></a>
									</c:otherwise>

								</c:choose>
							</c:forEach>
						</c:if>

						<%-- sizeが51以上の時 --%>
						<c:if test="${size ge 51 }">

							<%-- currentPageが4未満の時 --%>
							<c:if test="${currentPage lt 4 }">
									<c:forEach begin = "1" end = "6" varStatus="loop">
										<c:choose>

											<%-- loop.indexがcurrentPageと等しいとき --%>
		                					<c:when test="${loop.index eq currentPage }">
		                						<c:out value="${loop.index }"></c:out>
		                					</c:when>

		                					<%-- loop.indexが6の時 --%>
		                					<c:when test="${loop.index eq 6 }">
		                						<c:out value="…"></c:out>
		                					</c:when>

		                					<%-- それ以外 --%>
		                					<c:otherwise>
												<a href="<%=request.getContextPath() %>/MM001001_PageChangeAction?page=${loop.index }&startCode=${requestScope.STARTCODE }&endCode=${requestScope.ENDCODE }&shohinName=${requestScope.NAMEENCODE }"><c:out value="${loop.index }"></c:out></a>
											</c:otherwise>

										</c:choose>
									</c:forEach>
							</c:if>

							<%-- currentPageが4以上の時 --%>
							<c:if test="${currentPage ge 4}">
								<c:forEach begin = "${currentPage -3}" end = "${lastPage}" varStatus="loop">
									<c:choose>

										<%-- loop.indexとcurrentPageが等しいとき --%>
	                					<c:when test="${loop.index eq currentPage }">
	                						<c:out value="${loop.index }"></c:out>
	                					</c:when>

	                					<%-- loop.indexが(currentPage-3)と等しいかつloop.indexが8より小さいとき --%>
	                					<c:when test="${loop.index eq (currentPage -3) && loop.index lt 8 }">
	                						<c:out value="…"></c:out>
	                					</c:when>

	                					<%-- loop.indexが(currentPage+3)より小さいとき --%>
	                					<c:when test="${loop.index eq (currentPage +3) }">
	                						<c:out value="…"></c:out>
	                					</c:when>

	                					<%-- loop.indexが(currentPage+3)より大きいとき --%>
										<c:when test="${loop.index gt (currentPage +3) }">

	                					</c:when>

	                					<%-- それ以外 --%>
	                					<c:otherwise>
											<a href="<%=request.getContextPath() %>/MM001001_PageChangeAction?page=${loop.index }&startCode=${requestScope.STARTCODE }&endCode=${requestScope.ENDCODE }&shohinName=${requestScope.NAMEENCODE }"><c:out value="${loop.index }"></c:out></a>
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
                      <th width="200">商品コード</th>
                      <th width="400">商品名</th>
                      <th width="150">単価</th>
                     </tr>
                     <!-- 明細 -->

                     <%-- 繰り返し処理 --%>
                     <%-- 商品マスタテーブルから取り出した該当レコードを1行ずつrowsに格納 --%>
                     <c:forEach var="rows" items="${list}" varStatus="loop">
                      <tr>
                       <td>
                        <input type="button" value="　編集　" onclick="location.href='<%=request.getContextPath() %>/MM001001_EditAction?shohinCode=${rows.shohinCode}&updateTime=${rows.updateTime}'" />
                        <input type="button" value="　削除　" onclick="delConfirm('${rows.shohinCode }','${rows.updateTime }')" />
                       </td>
                       <td>${rows.shohinCode }</td>
                       <td>${rows.shohinName }</td>
                       <td align="right">${rows.tanka }</td>
                      </tr>
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