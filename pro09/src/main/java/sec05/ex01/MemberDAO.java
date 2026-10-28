package sec05.ex01;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.naming.Context;

public class MemberDAO {
	private DataSource dataFactory;

	public MemberDAO() {
	    try {
	        Context ctx = new InitialContext();
	        Context envContext = (Context) ctx.lookup("java:/comp/env");
	        dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public boolean isExisted(MemberVO memberVO) {
	    boolean result = false;
	    String id = memberVO.getId();
	    String pwd = memberVO.getPwd();
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        con = dataFactory.getConnection();
	        
	        // 오라클 DECODE 함수를 사용해 존재 여부를 'true'/'false' 문자열로 반환
	        String query = "select decode(count(*), 1, 'true', 'false') as result from t_member";
	        query += " where id=? and pwd=?";
	        
	        pstmt = con.prepareStatement(query);
	        pstmt.setString(1, id);
	        pstmt.setString(2, pwd);
	        
	        rs = pstmt.executeQuery();
	        
	        // 커서를 첫 번째 레코드로 이동 후 결과값 가져오기
	        rs.next();
	        result = Boolean.parseBoolean(rs.getString("result"));
	        
	        System.out.println("result=" + result);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // 리소스 해제 (생략된 경우 꼭 추가하시는 것을 권장합니다)
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
	        try { if (con != null) con.close(); } catch (Exception e) {}
	    }
	    return result;
	}
}