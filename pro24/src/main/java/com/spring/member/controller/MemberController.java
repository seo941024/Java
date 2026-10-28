package com.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface MemberController {
	ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
