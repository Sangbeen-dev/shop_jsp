package com.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.MemberDTO;
import com.service.MemberService;

/**
 * Servlet implementation class MemberIdSearchServlet
 */
@WebServlet("/MemberIdSearchServlet")
public class MemberIdSearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터 파싱
		//service.idSearch(MemberDTO) 이용
		//전화번호, 사용자 이름 이치 여부 검사 (mapperid = "idSearch") 후 사용자 id 만 select
		//사용자 id 리턴
		//id가 없는 경우 MemberIdSearchUIServlet으로 이동
		String username = request.getParameter("username").trim();
		String phone1 =request.getParameter("phone1").trim();
		String phone2 = request.getParameter("phone2").trim();
		String phone3 = request.getParameter("phone3").trim();
		String email1 = request.getParameter("email1").trim();
		String email2 = request.getParameter("email2").trim();
		
		MemberDTO dto = new MemberDTO();
		dto.setUsername(username);
		dto.setPhone1(phone1);
		dto.setPhone2(phone2);
		dto.setPhone3(phone3);
		
		MemberService service = new MemberService();
		String userid = service.idSearch(dto);
		System.out.println(userid);
		
		String nextPage = null;
		if(userid==null) {
			nextPage ="MemberIdSearchUIServlet"; //idSwarch.jsp로 이동
			request.setAttribute("mesg", "이름 또는 핸드폰이 등록되지 않은 정보");
			
		} else { //일치하는 경우 메일전송
			nextPage = "SendMailServlet";
			request.setAttribute("mailTo", email1+"@"+email2);//받는 사람의 메일 주소
			request.setAttribute("userid", userid);
		}
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
