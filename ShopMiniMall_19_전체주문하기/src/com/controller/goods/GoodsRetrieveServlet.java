package com.controller.goods;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.GoodsDTO;
import com.service.GoodsService;

/**
 * Servlet implementation class GoodsRetrieveServlet
 */
@WebServlet("/GoodsRetrieveServlet")
public class GoodsRetrieveServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gCode = request.getParameter("gCode");
		System.out.println(gCode);
		
		GoodsService service = new GoodsService();
		GoodsDTO dto = new GoodsDTO();
		dto = service.goodsRetrieve(gCode);
		//System.out.println(dto);
		request.setAttribute("goodsRetrieve", dto);
		
		RequestDispatcher dis = request.getRequestDispatcher("goodsRetrieve.jsp");
		//3개 jsp include, goods/goodsRetrieve.jsp
		dis.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
