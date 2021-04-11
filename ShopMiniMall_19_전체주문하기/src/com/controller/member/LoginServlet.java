package com.controller.member;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. id,pass이용 map생성 //userid, passwd 키
		//2. 키이용 사용자 정보를 service.login(map); 사용
		//dao.login => mapper id = "login" select //해당 사용자 정보를 모두 꺼냄
		//3. MemberDTO로 리턴
		//리턴값 출력
		//4. db에 아이디 패스가 존재하면 session 에 memberDTO저장키("login")
		//4-1. main.jsp로 이동, 없는 경우 LoginUIServlet으로 요청
		//4-2. dto 가 없는 경우 LoginUIServlet으로 요청 => LoginForm.jsp로 이동
		//5. doget에 마지막 라인에서 response.sendRedirect(정해진페이지로)
		//6. top.jsp session 로그인 정보를 가져와서 가져온 경우 => 로그아웃 , mypage 메뉴 보이기
		// 없는경우 기존 메뉴 그대로 가져오기
		
		String userid = request.getParameter("userid");	
		String passwd = request.getParameter("passwd");	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		
		MemberService service = new MemberService();
		MemberDTO dto = service.login(map); //로그인 인증
		//System.out.println(dto); 
		
		String nextPage=null; //이동 페이지 저장
		if(dto!=null) { //회원인 경우
			nextPage = "main"; //MainServlet 요청 데이터 가져와서 출력
			HttpSession session = request.getSession();
			session.setAttribute("login", dto); //로그인 정보 저장
			
		}else { //dto==null 회원이 아닌 경우
			nextPage = "LoginUIServlet";
		}
		response.sendRedirect(nextPage); //페이지 두개 중 하나로 이동
		
	
	}
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
