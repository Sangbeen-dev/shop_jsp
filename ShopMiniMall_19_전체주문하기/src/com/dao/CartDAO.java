package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.CartDTO;
import com.dto.OrderDTO;

public class CartDAO {

	public int cartAdd(SqlSession session, CartDTO xx) {
		int num = session.insert("CartMapper.cartAdd", xx);
		return num;
	}

	public List<CartDTO> cartList(SqlSession session, String userid) {
		List<CartDTO> list = session.selectList("CartMapper.cartList", userid);
		return list;
	}

	public int cartDel(SqlSession session, int num) {
		int n = session.delete("CartMapper.cartDel", num);
		return n;
	}

	public int cartUpdate(SqlSession session, HashMap<String, Integer> map) {
		int n = session.update("CartMapper.cartUpdate", map);
		return n;
	}

	public int cartAllDel(SqlSession session, List<String> list) {
		int n = session.delete("CartMapper.cartAllDel", list);
		return n;
	}

	public CartDTO cartbyNum(SqlSession session, int num) {
		CartDTO cDTO = session.selectOne("CartMapper.cartbyNum", num);
		return cDTO;
	}

	public int orderDone(SqlSession session, OrderDTO dto2) {
		int n = session.insert("CartMapper.orderDone", dto2);
		return n;
	}

	public List<CartDTO> orderAllConfirm(SqlSession session, List<String> x) {
		List<CartDTO> list = session.selectList("CartMapper.orderAllConfirm", x);
		return list;
	}

	public int orderAllDone(SqlSession session, List<OrderDTO> x) {
		int n = session.insert("CartMapper.orderAllDone", x);
		return n;
	}

}//end cartDAO
