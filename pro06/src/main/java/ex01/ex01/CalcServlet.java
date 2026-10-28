package ex01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class CalcServlet extends HttpServlet { 

    // ✅ 수정 6: 환율 상수 정의 (2026년 5월 기준 근사값, 1원 기준)
    private static final float USD_RATE = 1492.90f;  // 1달러 = 1492.90원
    private static final float JPY_RATE = 9.46f;     // 1엔 = 9.46원  (157.94 JPY/USD 기준)
    private static final float CNY_RATE = 219.87f;   // 1위안 = 219.87원 (6.79 CNY/USD 기준)
    private static final float GBP_RATE = 2017.43f;  // 1파운드 = 2017.43원 (0.74 GBP/USD 기준)
    private static final float EUR_RATE = 1756.35f;  // 1유로 = 1756.35원 (0.85 EUR/USD 기준)

    @Override
    public void init() throws ServletException { 
        System.out.println("init 메서드 호출");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw = response.getWriter();

        String command  = request.getParameter("command");
        String won      = request.getParameter("won");
        String operator = request.getParameter("operator");

        if (command != null && command.equals("calculate")) {
            String result = calculate(Float.parseFloat(won), operator);
      
            pw.print("<html><body>");
            pw.print("<font size=10>변환 결과</font><br>");
            pw.print("<font size=10>" + result + "</font><br>");
            pw.print("<a href='/pro06/calc'>환율 계산기</a>");
            pw.print("</body></html>");
            return;
        }

        pw.print("<html><head><title>환율 계산기</title></head><body>");
        pw.print("<font size=5>환율 계산기</font><br>");
        pw.print("<form name='frmCalc' method='get' action='/pro06/calc'>");  // ✅ 자체닫힘 /> 제거
        pw.print("원화: <input type='text' name='won' size=10 />");
        pw.print("<select name='operator'>");
        pw.print("<option value='dollar'>달러</option>");
        pw.print("<option value='en'>엔화</option>");
        pw.print("<option value='wian'>위안</option>");
        pw.print("<option value='pound'>파운드</option>");
        pw.print("<option value='euro'>유로</option>");
        pw.print("</select>");
        pw.print("<input type='hidden' name='command' value='calculate' />");
        pw.println("<input type='submit' value='변환' />");
        pw.println("</form>");
        pw.print("</body></html>");
        pw.close();
    }

    private static String calculate(float won, String operator) {
        String result = null;
        if (operator.equals("dollar")) {
            result = String.format("%.6f 달러", won / USD_RATE);
        } else if (operator.equals("en")) {          
            result = String.format("%.6f 엔", won / JPY_RATE);
        } else if (operator.equals("wian")) {
            result = String.format("%.6f 위안", won / CNY_RATE);
        } else if (operator.equals("pound")) {        
            result = String.format("%.6f 파운드", won / GBP_RATE);
        } else if (operator.equals("euro")) {         // ✅ 수정 5: else if로 수정
            result = String.format("%.6f 유로", won / EUR_RATE);
        }
        return result;
    }

    @Override
    public void destroy() {
        System.out.println("destroy 메서드 호출");
    }
}