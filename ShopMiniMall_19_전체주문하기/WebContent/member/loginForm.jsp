<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- id, 패스워드 입력 검사 후 진행하도록  작성  -->
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//id,pw 입력 검사
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
	})//end ready
	
</script>    
<form action="LoginServlet" method="get">
아이디:<input type="text" name="userid" id="userid"><br>
비밀번호:<input type="text" name="passwd" id="passwd"><br> 
<input type="submit" value="로그인">
<input type="reset" value="취소">
<a href="MemberIdSearchUIServlet">아이디 찾기</a>
</form>
