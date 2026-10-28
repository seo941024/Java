package com.spring.ex04;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.spring.ex01.MemberVO;

public class MemberDAO {
	public static SqlSessionFactory sqlMapper = null;

	private static SqlSessionFactory getInstance() {
		if (sqlMapper == null) {
			try {
				String resource = "mybatis/SqlMapConfig.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}

	public List<MemberVO> selectAllMemberList() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		try {
			return session.selectList("mapper.member.selectAllMemberList");
		} finally {
			session.close();
		}
	}

	// 23.4.4 : MemberVO 로 회원 추가
	public int insertMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		try {
			int result = session.insert("mapper.member.insertMember", memberVO);
			session.commit();
			return result;
		} finally {
			session.close();
		}
	}

	// 23.4.5 : HashMap 으로 회원 추가
	public int insertMember2(Map<String, String> memberMap) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		try {
			int result = session.insert("mapper.member.insertMember2", memberMap);
			session.commit();
			return result;
		} finally {
			session.close();
		}
	}

	// 23.4.6 : 회원 정보 수정
	public int updateMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		try {
			int result = session.update("mapper.member.updateMember", memberVO);
			session.commit();
			return result;
		} finally {
			session.close();
		}
	}

	// 23.4.7 : 회원 정보 삭제
	public int deleteMember(String id) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		try {
			int result = session.delete("mapper.member.deleteMember", id);
			session.commit();
			return result;
		} finally {
			session.close();
		}
	}
}
