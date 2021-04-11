package com.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.MemberDAO;
import com.dto.MemberDTO;

public class MemberService {

	public int memberAdd(MemberDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		System.out.println(session);
		int n = 0;
		try {
			MemberDAO dao = new MemberDAO();
			n = dao.memberAdd(session, dto);
			session.commit();	
		} finally {
			session.close();
		}
		return n;
	}//end memberAdd

	public int idCheck(String userid) {
		SqlSession session = MySqlSessionFactory.getSession();
		int count=0;
		try {
			MemberDAO dao = new MemberDAO();
			count = dao.idCheck(session, userid);
		} finally {
			session.close();
		}
		return count;
	}

	public MemberDTO login(HashMap<String, String> map) {
		MemberDTO dto = new MemberDTO();
		SqlSession session = MySqlSessionFactory.getSession();
		try {
			MemberDAO dao = new MemberDAO();
			dto = dao.login(session, map);
		} finally {
			session.close();
		}
		return dto;
	}

	public MemberDTO mypage(String userid) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDTO dto = new MemberDTO();
		try {
			MemberDAO dao = new MemberDAO();
			dto = dao.mypage(session, userid);
		} finally {
			session.close();
		}
		return dto;
	}//end mypage

	public int memberUpdate(MemberDTO dto2) {
		SqlSession session = MySqlSessionFactory.getSession();
		int num = 0;
		try {
			MemberDAO dao = new MemberDAO();
			num = dao.memberUpdate(session, dto2);
			session.commit();
		} finally {
			session.close();
		}
		return num;
	}

	public String idSearch(MemberDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		String userid = null;
		try {
			MemberDAO dao = new MemberDAO();
			userid = dao.idSearch(session, dto);
		} finally {
			session.close();
		}
		return userid;
	}
	
	
}//end class
