package com.controller.goods;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.GoodsService;

/**
 * Servlet implementation class GoodsCartServlet
 */
@WebServlet("/GoodsCartServlet")
public class GoodsCartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String nextPage=null;
		
		if(dto !=null) {
		String gImage = request.getParameter("gImage");
		String gCode = request.getParameter("gCode");
		String gName = request.getParameter("gName");
		int gPrice = Integer.parseInt(request.getParameter("gPrice"));
		String gSize = request.getParameter("gSize");
		String gColor = request.getParameter("gColor");
		int gAmount = Integer.parseInt(request.getParameter("gAmount"));
		String userid= dto.getUserid();//세션에 저장된 데이터 사용
		
		
		CartDTO xx = new CartDTO();
		xx.setUserid(userid);
		xx.setgCode(gCode);
		xx.setgName(gName);
		xx.setgPrice(gPrice);
		xx.setgSize(gSize);
		xx.setgColor(gColor);
		xx.setgAmount(gAmount);
		xx.setgImage(gImage);
		System.out.println("cartDTO = "+xx);
		CartService sevice = new CartService();
		int num = sevice.cartAdd(xx);
		System.out.println(num);
		
		nextPage = "GoodsRetrieveServlet?gCode="+gCode; //주의
		session.setAttribute("mesg", gCode+" Cart저장 되었습니다.");
		
		}else {
			nextPage = "LoginUIServlet";
			session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		}
		
		response.sendRedirect(nextPage);//리다이렉트시 url?key=value 데이터 전송이 가능
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
