package com.spring.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UserController extends MultiActionController {

	// 로그인 처리 메서드
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userID = "";
		String passwd = "";

		System.out.println("0. request : " + request.toString());
		String viewName = getViewName(request);

		ModelAndView mav = new ModelAndView();

		request.setCharacterEncoding("utf-8");
		userID = request.getParameter("userID");
		passwd = request.getParameter("passwd");

		mav.addObject("userID", userID);
		mav.addObject("passwd", passwd);
		mav.setViewName(viewName);
		
		return mav;
	}

	// 회원가입 폼 이동 메서드
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = getViewName(request);
		mav.setViewName(viewName);
		return mav;
	}

	// 회원정보 결과 처리 메서드
	public ModelAndView memberinfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		ModelAndView mav = new ModelAndView();
		mav.addObject("id", id);
		mav.addObject("pwd", pwd);
		mav.addObject("name", name);
		mav.addObject("email", email);

		String viewName = getViewName(request);
		mav.setViewName(viewName);
		return mav;
	}

	// 뷰 이름 추출 메서드 (교수님 콘솔 로그 완벽 일치)
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		System.out.println("1. contextPath : " + contextPath);
		
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		System.out.println("2. uri : " + uri);

		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
			System.out.println("2-1. uri : " + uri);
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}
		System.out.println("3. begin : " + begin);

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}
		System.out.println("4. end : " + end);

		String fileName = uri.substring(begin, end);
		System.out.println("5. fileName : " + fileName);

		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
			System.out.println("6. _fileName : " + fileName);
		}

		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
			System.out.println("7. _fileName : " + fileName);
		}

		System.out.println("8. fileName : " + fileName);
		return fileName;
	}
}