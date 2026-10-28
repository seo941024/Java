package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.vo.MemberVO;

public interface MemberService {
	List listMembers() throws DataAccessException;

	int addMember(MemberVO memberVO) throws DataAccessException;
}
