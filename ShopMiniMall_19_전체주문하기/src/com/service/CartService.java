package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.CartDAO;
import com.dto.CartDTO;
import com.dto.OrderDTO;

public class CartService {

	public int cartAdd(CartDTO xx) {
		SqlSession session = MySqlSessionFactory.getSession();
		int num = 0;
		try {
			CartDAO dao = new CartDAO();
			num = dao.cartAdd(session, xx);
			session.commit();
		} finally {
			session.close();
		}
		return num;
	}

	public List<CartDTO> cartList(String userid) {
		SqlSession session = MySqlSessionFactory.getSession();
		List<CartDTO> list = null;
		try {
			CartDAO dao = new CartDAO();
			list = dao.cartList(session, userid);
		} finally {
			session.close();
		}
		return list;
	}

	public int cartDel(int num) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n = 0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.cartDel(session, num);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}

	public int cartUpdate(HashMap<String, Integer> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n=0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.cartUpdate(session, map);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}

	public int cartAllDel(List<String> list) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n =0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.cartAllDel(session, list);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}

	public CartDTO cartbyNum(int num) {
		SqlSession session = MySqlSessionFactory.getSession();
		CartDTO cDTO = new CartDTO();
		try {
			CartDAO dao = new CartDAO();
			cDTO = dao.cartbyNum(session, num);
		} finally {
			session.close();
		}
		return cDTO;
	}

	public int orderDone(OrderDTO dto2, String orderNum) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n = 0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.orderDone(session, dto2);
			n = dao.cartDel(session, Integer.parseInt(orderNum));
			session.commit();
		} catch (Exception e) {
			session.rollback();//tx처리
			System.out.println("rollback됨====");
		} finally {
			session.close();
		}
		return n;
	}//end orderDone

	public List<CartDTO> orderAllConfirm(List<String> x) {
		SqlSession session = MySqlSessionFactory.getSession();
		List<CartDTO> list = null;
		try {
			CartDAO dao = new CartDAO();
			list = dao.orderAllConfirm(session, x);
		} finally {
			session.close();
		}
		return list;
	}

	public int orderAllDone(List<OrderDTO> x, List<String> nums) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n = 0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.orderAllDone(session, x);
			n = dao.cartAllDel(session, nums);
			session.commit();
		}catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		return n;
	}

	
}//end cartService
