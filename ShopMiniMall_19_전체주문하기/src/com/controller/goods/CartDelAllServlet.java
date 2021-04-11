package com.controller.goods;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.CartService;

/**
 * Servlet implementation class CartDelAllServlet
 */
@WebServlet("/CartDelAllServlet")
public class CartDelAllServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String nextPage = null;
		if (dto != null) {
			// 데이터를 배열로 저장했기 때문에 getParameter()로 받아도됨
			String data = request.getParameter("data");
			String [] x = data.split(",");
			List<String> list = Arrays.asList(x);
			//getParameterValues() 사용 
			//String[] data = request.getParameterValues("data");
			//List<String> list = Arrays.asList(data);
			
			CartService service = new CartService();
			int n = service.cartAllDel(list);
			System.out.println(n);
			nextPage = "CartListServlet";
		}else {
			nextPage = "LoginUIServlet";
			session.setAttribute("mesg", "로그인이 필요한 작업입니다.");
		}
		response.sendRedirect(nextPage);
		
		
		
		
	}//end doget

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
