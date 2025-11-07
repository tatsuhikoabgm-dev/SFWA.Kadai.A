package com.example.app.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.app.domain.Member;
import com.example.app.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	
   private final	MemberMapper memberMapper;
	
	@Override
	public Member getAuthenticatedMember(String loginId,
			String loginPass) {
		
		Member userInfo = memberMapper.selectByLoginId(loginId);
		System.out.println(userInfo);
		
		System.out.println("getAuthenticatedMember　　:" + loginId + loginPass);
		if( userInfo == null) {
			return null;
		}
		
		if(BCrypt.checkpw(loginPass, userInfo.getLoginPass())){
			return userInfo ;
		}
		
		return null ;
	}
	
}
