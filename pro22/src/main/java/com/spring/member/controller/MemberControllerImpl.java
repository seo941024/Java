package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVO;

public class MemberControllerImpl extends MultiActionController implements MemberController {
	private MemberService memberService;

	// action-servlet.xml에서 memberService 빈을 주입하기 위한 setter
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	// /member/listMembers.do 요청 시 호출
	@Override
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}

	// /member/memberForm.do 요청 시 호출 (회원 가입 폼으로 이동)
	@Override
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		return new ModelAndView(viewName);
	}

	// /member/addMember.do 요청 시 호출 (회원 등록 후 목록으로 리다이렉트)
	@Override
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		MemberVO memberVO = new MemberVO(id, pwd, name, email);
		memberService.addMember(memberVO);

		return new ModelAndView("redirect:/member/listMembers.do");
	}

	// /member/memberDetail.do 요청 시 호출 (회원 상세 화면 - 필요 시 구현)
	@Override
	public ModelAndView memberDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		return new ModelAndView(viewName);
	}

	// 요청 URI에서 뷰 이름을 추출하는 메서드
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();

		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String fileName = uri.substring(begin, end);

		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}

		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
		}

		return fileName;
	}
}
