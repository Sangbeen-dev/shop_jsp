<%@page import="com.dto.GoodsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	
	String mesg = (String)session.getAttribute("mesg");
	if(mesg!=null){
%>
	<script>
	alert('<%= mesg %>');
	</script>
<%
	}
	session.removeAttribute("mesg");
%>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#up").on("click", function() {
			var count = $("#gAmount").val();
			$("#gAmount").val(parseInt(count)+1);
			console.log(count);
		});//end up click
		
		$("#down").on("click", function() {
			var count = $("#gAmount").val();
			if(count != 1){
			$("#gAmount").val(parseInt(count)-1);
			}
		});//end down click
		
		$("#cart").on("click", function() {
			
			if($("#gSize").val()=="사이즈선택"){
				alert("사이즈선택하세요");
				return false;
				//event.preventDefault();
			}
			if($("#gColor").val()=="색상선택"){
				alert("색상을 선택하세요");
				return false;
				//event.preventDefault();
			}
			$("form").attr("action", "GoodsCartServlet");
			
		})//end cart
		
		
	})//end ready
</script>

<%
GoodsDTO dto = (GoodsDTO)request.getAttribute("goodsRetrieve");
%>

<form name="goodRetrieveForm" method="GET" action="#"><!--action은 위 jquery에서 설정  -->
	   <input type="hidden" name="gImage" value="<%= dto.getgImage() %>">
	   <input type="hidden" name="gCode" value="<%= dto.getgCode() %>">
	   <input type="hidden" name="gName" value="<%= dto.getgName() %>">
	   <input type="hidden" name="gPrice" value="<%= dto.getgPrice() %>">

	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30">
		</tr>
		<tr>
			<td>
				<table align="center" width="710" cellspacing="0" cellpadding="0"
					border="0" style='margin-left: 30px'>
					<tr>
						<td class="td_default"><font size="5"><b>- 상품 정보 -</b></font>
							&nbsp;</td>
					</tr>
					<tr>
						<td height="5"></td>
					</tr>
					<tr>
						<td height="1" colspan="8" bgcolor="CECECE"></td>
					</tr>
					<tr>
						<td height="10"></td>
					</tr>

					<tr>
						<td rowspan="7"><img src="images/items/<%= dto.getgImage() %>.gif"
							border="0" align="center" width="300" /></td>
						<td class="td_title">제품코드</td>
						<td class="td_default" colspan="2" style='padding-left: 30px'>
						 <%= dto.getgCode() %>
						</td>
					</tr>
					<tr>
						<td class="td_title">상품명</td>
						<td class="td_default" colspan="2" style='padding-left: 30px'><%= dto.getgName() %></td>
					</tr>
					<tr>
						<td class="td_title">가격</td>
							
						<td class="td_red" colspan="2" style='padding-left: 30px'>
						<%= dto.getgPrice() %>
						</td>
					</tr>
					<tr>
						<td class="td_title">배송비</td>
						<td colspan="2"><font color="#2e56a9" size="2"
							style='padding-left: 30px'><b> 무료배송</b> </font> <font size="2">(도서
								산간지역 별도 배송비 추가)</font></td>
					</tr>
					<tr>
						<td class="td_title" rowspan="2">상품옵션</td>
						<td colspan="2" style='padding-left: 30px'><select
							class="select_change" size="1" name="gSize" id="gSize">
								<option selected value="사이즈선택">사이즈선택</option>
								<option value="L">L</option>
								<option value="M">M</option>
								<option value="S">S</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" style='padding-left: 30px'><select
							class="select_change" size="1" name="gColor"
							id="gColor">
								<option selected value="색상선택">색상선택</option>
								<option value="navy">navy</option>
								<option value="black">black</option>
								<option value="ivory">ivory</option>
								<option value="white">white</option>
								<option value="gray">gray</option>
						</select></td>
					</tr>

					<tr>
						<td class="td_title">주문수량</td>
						<td style="padding-left: 30px"><input type="text"
							name="gAmount" value="1" id="gAmount"
							style="text-align: right; height: 18px"> <img
							src="images/up.PNG" id="up"> <img src="images/down.PNG"
							id="down"></td>
					</tr>
				</table>

			</td>
		</tr>
	</table>

	<br> <button>구매</button>
	&nbsp;&nbsp;
	<button id="cart" >장바구니</button>
</form>
    