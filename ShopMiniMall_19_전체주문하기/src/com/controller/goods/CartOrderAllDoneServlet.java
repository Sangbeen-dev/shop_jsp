package com.controller.goods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.service.CartService;

/**
 * Servlet implementation class CartOrderAllDoneServlet
 */
@WebServlet("/CartOrderAllDoneServlet")
public class CartOrderAllDoneServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String nextPage = null;
		if(dto!=null) {
			String userid = dto.getUserid();
			String [] nums = request.getParameterValues("ordernum");
			//System.out.println("alldoneNum"+nums);
			List<String> list = Arrays.asList(nums);
			String orderName = request.getParameter("orderName");
			String post = request.getParameter("post");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2");
			String phone = request.getParameter("phone");
			String payMethod =request.getParameter("payMethod");
			
			CartService cService = new CartService();
			List<CartDTO> cList = cService.orderAllConfirm(list);
			List<OrderDTO> oList = new ArrayList<OrderDTO>();
			for (CartDTO cDTO : cList) {
				OrderDTO oDTO = new OrderDTO();
				oDTO.setUserid(userid);
				oDTO.setNum(cDTO.getNum());
				oDTO.setgCode(cDTO.getgCode());
				oDTO.setgName(cDTO.getgName());
				oDTO.setgPrice(cDTO.getgPrice());
				oDTO.setgAmount(cDTO.getgAmount());
				oDTO.setgSize(cDTO.getgSize());
				oDTO.setgColor(cDTO.getgColor());
				oDTO.setgImage(cDTO.getgImage());
				oDTO.setOrderName(orderName);
				oDTO.setPost(post);
				oDTO.setAddr1(addr1);
				oDTO.setAddr2(addr2);
				oDTO.setPhone(phone);
				oDTO.setPayMethod(payMethod);
				//System.out.println("oDTO: "+ oDTO); //데이터확인
				oList.add(oDTO);
			}//end for
			int n = cService.orderAllDone(oList, list);
			System.out.println(n);
			request.setAttribute("orderAllDone", oList);
			nextPage = "orderAllDone.jsp";
		}else {
			nextPage = "LoginUISevlet";
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
