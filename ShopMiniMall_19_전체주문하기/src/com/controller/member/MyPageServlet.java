package com.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 세션에서 로그인 정보가져오기
		//2. 로그인 정보가 있는 경우 사용자 id를 꺼내서 service.mypage(id)이용 DB에서 데이터를 다시 가져오기
		//   가져온 정보를 다시 세션에 login 이라는 키로 저장 후 webcontent/mypage.jsp로 이동
		//   => include/ top.jsp, menu.jsp, member/mypage.jsp(session의 login 정보 콘솔출력) 로 이동
		//3. 로그인 정보가 없는 경우는 request 에 'mesg''로그인 필요한 작업입니다.' 저장 후 
		//   로그인 페이지로 이동(LoginUIServlet)
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		//System.out.println(dto); 
		String nextPage =null;
		if(dto != null) { //로그인 정보가 있는경우
			nextPage = "mypage.jsp";
			MemberService service = new MemberService();
			dto = service.mypage(dto.getUserid());
			//System.out.println("변경된 dto = "+dto);
			session.setAttribute("login", dto);
		}else {
			nextPage= "LoginUIServlet";
			session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		}
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
