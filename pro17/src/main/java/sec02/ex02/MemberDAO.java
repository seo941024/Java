package sec02.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
    private DataSource dataFactory;
    private Connection conn;
    private PreparedStatement pstmt;
    
    public MemberDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();    
        }
    }
    
    // 1. 회원 목록 조회 메서드
    public List<MemberVO> listMembers(){
        List<MemberVO> membersList = new ArrayList<MemberVO>();
        ResultSet rs = null;
        try {
            conn = dataFactory.getConnection();
            String query = "select * from t_member order by joinDate desc";
            System.out.println("query : " + query);
            
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String id = rs.getString("id");
                String pwd = rs.getString("pwd");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date joinDate = rs.getDate("joinDate");
                
                MemberVO memberVO = new MemberVO(id, pwd, name, email, joinDate);
                membersList.add(memberVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return membersList;
    }

    // 2. 회원 추가 메서드
    public void addMember(MemberVO memberVO) {
        try {
            conn = dataFactory.getConnection();
            
            String id = memberVO.getId();
            String pwd = memberVO.getPwd();
            String name = memberVO.getName();
            String email = memberVO.getEmail();
            
            String query = "insert into t_member (id, pwd, name, email) values (?, ?, ?, ?)";
            System.out.println("query : " + query);
            
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            pstmt.setString(3, name);
            pstmt.setString(4, email);
            
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    // 3. 🌟 [이미지 상단/중단 내용] 수정창용 특정 회원 조회 메서드
    public MemberVO findMember(String _id) {
        MemberVO memInfo = null;
        try 
        {
            conn = dataFactory.getConnection();
            String query = "select * from  t_member where id=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, _id);
            
            System.out.println(query);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            
            String id = rs.getString("id");
            String pwd = rs.getString("pwd");
            String name = rs.getString("name");
            String email = rs.getString("email");
            Date joinDate = rs.getDate("joinDate");
            
            memInfo = new MemberVO(id, pwd, name, email, joinDate);
            pstmt.close();
            conn.close();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return memInfo;
    }

    // 4. 🌟 [이미지 하단 내용] 회원 정보 수정 실행 메서드
    public void modMember(MemberVO memberVO)
    {
        String id = memberVO.getId();
        String pwd = memberVO.getPwd();
        String name = memberVO.getName();
        String email = memberVO.getEmail();
        try
        {
            conn = dataFactory.getConnection();
            String query = "update t_member set pwd=?,name=?,email=? where id=?";
            System.out.println(query);
            
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, pwd);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, id);
            
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 5. 회원 삭제 실행 메서드
    public void delMember(String id) {
        try {
            conn = dataFactory.getConnection();
            String query = "delete from t_member where id=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}