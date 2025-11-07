package com.example.app.service;

import com.example.app.domain.Member;

public interface MemberService {

	public Member getAuthenticatedMember(String loginId,
																String loginPass);
}
