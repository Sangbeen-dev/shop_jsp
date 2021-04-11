package com.controller.goods;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.service.CartService;

/**
 * Servlet implementation class CartOrderDoneServlet
 */
@WebServlet("/CartOrderDoneServlet")
public class CartOrderDoneServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String nextPage=null;
		if(dto!=null) {
			String userid = dto.getUserid();
			String gCode = request.getParameter("gcode");
			String gName = request.getParameter("gname");
			int gPrice = Integer.parseInt(request.getParameter("gprice"));
			String gSize = request.getParameter("gsize");
			String gColor = request.getParameter("gcolor");
			int gAmount = Integer.parseInt(request.getParameter("gamount"));
			String gImage = request.getParameter("gimage");
			String orderNum = request.getParameter("ordernum");
			String orderName = request.getParameter("orderName");
			String post = request.getParameter("post");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2");
			String phone = request.getParameter("phone");
			String payMethod = request.getParameter("payMethod");
			
			OrderDTO dto2 = new OrderDTO(0, userid, gCode, gName, gPrice, gSize, gColor, gAmount,
					gImage, orderName, post, addr1, addr2, phone, payMethod, null);
			System.out.println("cartorderDoneServlet.dto2==" + dto2);
			System.out.println("cartorderDoneServlet.orderNum==" + orderNum);
			CartService service = new CartService();
			int n = service.orderDone(dto2, orderNum);
			System.out.println(n);
			
			request.setAttribute("orderDTO", dto2);//주문완료 확인용
			nextPage = "orderDone.jsp";
		} else {
			nextPage = "LoginUIServlet";
			session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		}
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
		
	}//end doget

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
