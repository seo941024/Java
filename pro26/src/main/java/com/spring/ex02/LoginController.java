package com.spring.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("loginController")
public class LoginController {

	// 26.3 : 로그인창 (loginForm.do, loginForm2.do 모두 loginForm 뷰로 이동)
	@RequestMapping(value = { "/test/loginForm.do", "/test/loginForm2.do" }, method = { RequestMethod.GET })
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("test/loginForm");
		return mav;
	}

	// 26.3 : getParameter()로 직접 값을 얻는 기본 방식
	@RequestMapping(value = "/test/login.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("test/result");

		String userID = request.getParameter("userID");
		String userName = request.getParameter("userName");
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);
		return mav;
	}

	// 26.3.1 / 26.3.2 : @RequestParam 이용해 매개변수 값 얻기
	@RequestMapping(value = "/test/login2.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login2(@RequestParam("userID") String userID,
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "email", required = false) String email, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("test/result");

		System.out.println("userID: " + userID);
		System.out.println("userName: " + userName);
		System.out.println("email: " + email);

		mav.addObject("userID", userID);
		mav.addObject("userName", userName);
		return mav;
	}

	// 26.3.3 : @RequestParam 이용해 Map에 매개변수 값 설정하기
	@RequestMapping(value = "/test/login3.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login3(@RequestParam Map<String, String> info, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();

		String userID = info.get("userID");
		String userName = info.get("userName");
		System.out.println("userID: " + userID);
		System.out.println("userName: " + userName);

		mav.addObject("info", info);
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);
		mav.setViewName("test/result");
		return mav;
	}

	// 26.3.4 : @ModelAttribute 이용해 VO에 매개변수 값 설정하기
	@RequestMapping(value = "/test/login4.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login4(@ModelAttribute("info") LoginVO loginVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();

		System.out.println("userID: " + loginVO.getUserID());
		System.out.println("userName: " + loginVO.getUserName());

		mav.addObject("userID", loginVO.getUserID());
		mav.addObject("userName", loginVO.getUserName());
		mav.setViewName("test/result");
		return mav;
	}

	// 26.3.5 : Model 클래스 이용해 값 전달하기
	@RequestMapping(value = "/test/login5.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String login5(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		model.addAttribute("userID", "hong");
		model.addAttribute("userName", "홍길동");
		return "test/result";
	}
}
