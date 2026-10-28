package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login5")
public class LoginServlet extends HttpServlet {
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter();

	    // 1. 로그인창에서 전송된 ID와 비밀번호를 가져옵니다.
	    String user_id = request.getParameter("user_id");
	    String user_pwd = request.getParameter("user_pwd");

	    // 2. MemberVO 객체를 생성하고 속성에 ID와 비밀번호를 설정합니다.
	    MemberVO memberVO = new MemberVO();
	    memberVO.setId(user_id);
	    memberVO.setPwd(user_pwd);

	    // 3. MemberDAO의 isExisted() 메서드를 호출하면서 memberVO를 전달합니다.
	    MemberDAO dao = new MemberDAO();
	    boolean result = dao.isExisted(memberVO);

	    // 4. 조회 결과에 따른 처리
	    if (result) {
	        // 조인 결과가 true이면 세션에 정보를 저장합니다.
	        HttpSession session = request.getSession();
	        session.setAttribute("isLogon", true);
	        session.setAttribute("login.id", user_id);
	        session.setAttribute("login.pwd", user_pwd);

	        out.print("<html><body>");
	        out.print("안녕하세요 " + user_id + "님!!!<br>");
	        out.print("<a href='show'>회원정보 보기</a>");
	        out.print("</body></html>");
	    } else {
	        // 로그인 실패 시 처리
	        out.print("<html><body><center>회원 아이디가 틀립니다.");
	        out.print("<a href='login3.html'> 다시 로그인하기</a>");
	        out.print("</center></body></html>");
	    }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doHandle(request, response);
	}

}
