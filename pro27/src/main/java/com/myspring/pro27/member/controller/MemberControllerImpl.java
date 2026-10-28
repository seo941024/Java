package com.myspring.pro27.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.pro27.member.service.MemberService;
import com.myspring.pro27.member.vo.MemberVO;

@Controller("memberController")
@RequestMapping("/member")
public class MemberControllerImpl {
	private static final Logger logger = LogManager.getLogger(MemberControllerImpl.class);

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberVO memberVO;

	@RequestMapping(value = "/listMembers.do", method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		logger.info("info레벨 : viewName = " + viewName);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}

	@RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		memberService.addMember(new MemberVO(id, pwd, name, email));
		return new ModelAndView("redirect:/member/listMembers.do");
	}

	@RequestMapping(value = "/removeMember.do", method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		memberService.removeMember(id);
		return new ModelAndView("redirect:/member/listMembers.do");
	}

	// 27.12 : 로그인 처리 (member에 전송된 id/pwd를 MemberVO에 자동 바인딩)
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		MemberVO loginVO = memberService.login(member);
		if (loginVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", loginVO);
			session.setAttribute("isLogOn", true);
			mav.setViewName("redirect:/member/listMembers.do");
		} else {
			mav.setViewName("redirect:/member/loginForm.do?result=loginFailed");
		}
		return mav;
	}

	// 27.12 : 로그아웃 처리
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		return new ModelAndView("redirect:/member/listMembers.do");
	}

	// /member/xxxForm.do 요청을 폼 화면으로 연결
	@RequestMapping(value = "/*Form.do", method = RequestMethod.GET)
	private ModelAndView form(@RequestParam(value = "result", required = false) String result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		return mav;
	}

	// 요청 URI에서 뷰 이름(예: /member/listMembers)을 추출하는 메서드
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		int begin = contextPath.length();
		int end = uri.indexOf(".do");
		if (end == -1) {
			end = uri.length();
		}
		return uri.substring(begin, end);
	}
}
