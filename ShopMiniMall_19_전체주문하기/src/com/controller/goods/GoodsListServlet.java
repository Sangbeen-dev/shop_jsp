package com.controller.goods;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.GoodsDTO;
import com.service.GoodsService;



/**
 * Servlet implementation class GoodsListServlet
 */
@WebServlet("/GoodsListServlet")
public class GoodsListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//시작점 goodsListServlet => 상품정보를 담음(category=top) =>
		// main.jsp - include: goods/goodsList.jsp(top상품정보를 출력)
		//1. category 파싱 . 없을경우 String category = "top"로 설정
		//2. service.goodsList(카테고리값) : List<GoodsDTO>  mapper id = "GoodsMapper.goodsList"
		//3. 확인 출력
		String gCategory = request.getParameter("gCategory");
		if(gCategory==null) {
			gCategory ="top";
		}
		
		System.out.println(gCategory);
		GoodsService service = new GoodsService(); 
		List<GoodsDTO> list = service.goodsList(gCategory);
		System.out.println(list);
		
		//4. 얻어온 상품 list를 request에 설정 키값 "goodsList"
		request.setAttribute("goodsList", list);
		//5. main.jsp 로 forward
		RequestDispatcher dis = request.getRequestDispatcher("main.jsp");
		dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
