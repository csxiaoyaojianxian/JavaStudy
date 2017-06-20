package com.csxiaoyao.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class TestAction implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		modelAndView.addObject("message",username);
//		modelAndView.setViewName("/jsp/success.jsp");
		modelAndView.setViewName("success");
		return modelAndView;
	}

}
