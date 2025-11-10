package com.example.app.controller;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Member;
import com.example.app.service.MemberServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class AuthController {

	private final MemberServiceImpl memberServiceImpl;

	@GetMapping("/")
	public String showLoginForm(Member member,
			Model model) {
		model.addAttribute("inputForm", member);
		System.out.println("showLoginForm.inputForm　　:" + model.getAttribute("inputForm"));
		return "login";
	}

	@PostMapping("/")
	public String loginCheck(@Valid @ModelAttribute("inputForm") Member inputForm,
			BindingResult result,
			HttpSession session,
			RedirectAttributes ra) {

		System.out.println("loginCheck　　:" + inputForm.getLoginId() + inputForm.getLoginPass());

		if (result.getFieldError("loginId") != null) {
			return "login";
		}
		if (result.getFieldError("loginPass") != null) {
			return "login";
		}

		System.out.println("loginCheck.imputForm　　:" + inputForm);
		System.out.println(inputForm.getLoginId() + inputForm.getLoginPass());

		Member userInfo = memberServiceImpl.getAuthenticatedMember(inputForm.getLoginId(), inputForm.getLoginPass());

		if (userInfo != null) {
			session.setAttribute("userInfo", userInfo.getName());
			return "redirect:/items";
		}else {
			ra.addFlashAttribute("failed", "ログインIDまたはパスワードが不正です");
			System.out.println(ra.getFlashAttributes());
			return "redirect:/";
		}
		
		
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userInfo");
		return "redirect:/";
	}
	
	
	@GetMapping("/items/test")
	@ResponseBody
	public String httpServletTest(HttpServletRequest request, HttpServletResponse response) {

	    StringBuilder sb = new StringBuilder();

	    sb.append("=== Request Info ===\n");
	    sb.append("Method: ").append(request.getMethod()).append("\n");
	    sb.append("Request URI: ").append(request.getRequestURI()).append("\n");
	    sb.append("Protocol: ").append(request.getProtocol()).append("\n");
	    sb.append("Remote Addr: ").append(request.getRemoteAddr()).append("\n");
	    sb.append("Remote Host: ").append(request.getRemoteHost()).append("\n");
	    sb.append("Query String: ").append(request.getQueryString()).append("\n\n");

	    sb.append("=== Headers ===\n");
	    Enumeration<String> headerNames = request.getHeaderNames();
	    while (headerNames.hasMoreElements()) {
	        String name = headerNames.nextElement();
	        String value = request.getHeader(name);
	        sb.append(name).append(": ").append(value).append("\n");
	    }

	    sb.append("\n=== Parameters ===\n");
	    Map<String, String[]> paramMap = request.getParameterMap();
	    for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
	        sb.append(entry.getKey()).append(": ")
	          .append(Arrays.toString(entry.getValue())).append("\n");
	    }

	    sb.append("\n=== Attributes ===\n");
	    Enumeration<String> attrNames = request.getAttributeNames();
	    while (attrNames.hasMoreElements()) {
	        String name = attrNames.nextElement();
	        Object value = request.getAttribute(name);
	        sb.append(name).append(": ").append(value).append("\n");
	    }

	    return "<pre>" + sb.toString() + "</pre>";
	}

}
