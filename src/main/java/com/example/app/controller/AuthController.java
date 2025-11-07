package com.example.app.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
		model.addAttribute("inputForm",member);
		System.out.println("showLoginForm.inputForm　　:" + model.getAttribute("inputForm"));
		return "login";
	}
	
	
	
	@PostMapping("/")
	public String loginCheck(@Valid @ModelAttribute("inputForm") Member inputForm,
													BindingResult result,
													HttpSession session,
													RedirectAttributes ra) {
		
		System.out.println("loginCheck　　:" + inputForm.getLoginId() + inputForm.getLoginPass());
		
		if(result.getFieldError("loginId") != null) {return "login";}
		if( result.getFieldError("loginPass") != null) {return "login";}
		
		System.out.println("loginCheck.imputForm　　:" + inputForm);
		System.out.println(inputForm.getLoginId() + inputForm.getLoginPass());
		
		if(memberServiceImpl.getAuthenticatedMember(inputForm.getLoginId(), inputForm.getLoginPass()) == null) {
			ra.addFlashAttribute("failed","ログインIDまたはパスワードが不正です");
			System.out.println(ra.getFlashAttributes());
			return "redirect:/";
		}
		
		return "redirect:/items";
	}
	
	@GetMapping("/logout")
	public String logout() {
		
		return "redirect:/";
	}
	
	
}
