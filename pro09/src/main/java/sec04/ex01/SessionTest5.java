package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class SessionTest5 extends HttpServlet {

    // 브라우저에서 접속할 때 실행되도록 doGet 메서드를 추가합니다!
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        String user_id = request.getParameter("user_id");
        String user_pw = request.getParameter("user_pw");
        
        // 1. 처음 접속하여 세션이 새로 만들어진 경우
        if (session.isNew()) {
            if(user_id != null) { // ID값이 파라미터로 넘어왔는지 확인
                session.setAttribute("user_id", user_id);
                String url=response.encodeURL("login");
                out.println("<a href='+url+'>로그인 상태 확인</a>");
            } else {
                out.print("<a href='login.html'>다시 로그인 하세요</a>");
                session.invalidate();
            }
        } 
        // 2. 이미 세션이 있는 경우 (else if를 쓰지 않고 else로 묶습니다)
        else {
            user_id = (String) session.getAttribute("user_id");
            
            // 오류 수정 1: user_id = null 이 아니라 user_id != null 입니다.
            if (user_id != null && user_id.length() != 0) {
                out.print("안녕하세요 " + user_id + "님");
            } else { 
                // 오류 수정 2: 따옴표 위치를 올바르게 수정했습니다.
                out.print("<a href='login2.html'>다시 로그인 하세요</a>");
                session.invalidate();
            }
        }
    }
}