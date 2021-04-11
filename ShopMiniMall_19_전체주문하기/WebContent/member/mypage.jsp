<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	//form 서브밋
//id, 패스워드공백확인
//비번확인
//passwd2, passwd일치확인	
//이메일 선택
	//아이디 비번 필수 입력 검사
		$("form").on("submit", function() {
			var userid = $("#userid").val();
			var passwd = $("#passwd").val();
				if(userid.length == 0){
					alert("userid 필수");
					$("#userid").focus();
					event.preventDefault();
				}else if(passwd.length == 0){
					alert("passwd 필수");
					$("#passwd").focus();
					event.preventDefault();
				}//end else if
		});//end submit
		//비밀번호 확인
		$("#passwd2").on("keyup", function() {
			var passwd = $("#passwd").val();
			var passwd2 = $("#passwd2").val();
			var mesg = "비번 불일치";
			if(passwd == passwd2){
				mesg = "비번 일치";
			}
			$("#result2").text(mesg);
		})//end keyup
		//이메일
		$("#emailSelect").on("change", function() {
			var email = $(this).val();
			$("#email2").val(email);
		});//end change
		//아이디 중복 검사
		$("#userid").on("keyup", function() {
			var id = $("#userid").val();
			console.log(id);
			$.ajax({
				type:"get",
				url: "MemberIdCheckServlet",
				dataType: "text",
				data: {
					userid : $("#userid").val() //키는 userid: 입력 id
					},
				success: function(data, status, xhr) {
					console.log("success===", status);
					$("#result1").text(data);
					console.log(mesg);
				},
				error: function(xhr, status, error) {
					console.log("error", error);
					console.log("status", status);
				}
			});//end ajax
		});//end keyup
		
 });
</script>    
<%
  //session에서 "login"으로 데이터 뽑기
  MemberDTO dto = (MemberDTO)session.getAttribute("login");
  //System.out.print(dto);
%>
<form action="MemberUpdateServlet" method="post">
<input type="hidden" value="<%= dto.getUserid() %>" name="userid">
*아이디: <%= dto.getUserid() %><br>
<br> 
*이름:<%= dto.getUsername() %>
<br> 
<input type="text" value="<%= dto.getPost() %>" name="post" id="sample4_postcode"  placeholder="우편번호">
<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
<input type="text" value="<%= dto.getAddr1() %>" name="addr1" id="sample4_roadAddress" placeholder="도로명주소">
<input type="text" value="<%= dto.getAddr2() %>" name="addr2" id="sample4_jibunAddress" placeholder="지번주소">
<span id="guide" style="color:#999"></span>
<br>
전화번호:<select name="phone1">
  <option value="017" <% if("017".equals(dto.getPhone1())){ %> selected<%} %>>017</option>
  <option value="011" <% if("011".equals(dto.getPhone1())){ %> selected<%} %>>011</option>
  <option value="010" <% if("010".equals(dto.getPhone1())){ %> selected<%} %>>010</option>

</select>-
<input type="text" value="<%= dto.getPhone2() %>" name="phone2" >
-<input type="text" value="<%= dto.getPhone3() %>" name="phone3" >
<br>
이메일:<input type="text" value="<%= dto.getEmail1() %>" name="email1" id="email1">@
       <input type="text" value="<%= dto.getEmail2() %>" name="email2" id="email2" placeholder="직접입력">
       <select  id="emailSelect">
        <option value="daum.net">daum.net</option>
        <option value="naver.com">naver.com</option>
       </select>
<br>
<input type="submit" value="수정">
<input type="reset" value="취소">
</form>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 도로명 조합형 주소 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                if(fullRoadAddr !== ''){
                    fullRoadAddr += extraRoadAddr;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('sample4_roadAddress').value = fullRoadAddr;
                document.getElementById('sample4_jibunAddress').value = data.jibunAddress;

                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    //예상되는 도로명 주소에 조합형 주소를 추가한다.
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

                } else {
                    document.getElementById('guide').innerHTML = '';
                }
            }
        }).open();
    }
</script>