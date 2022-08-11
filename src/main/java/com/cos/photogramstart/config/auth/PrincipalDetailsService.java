package com.cos.photogramstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//System.out.println("나 실행되니 ? ");
		
		User userEntity = userRepository.findByUsername(username);
		if(userEntity == null) {
			System.out.println("userEntity null이라고 !!!!!!");
			return null;
		}else {
			System.out.println("userEntity null아니라고 !!!!!!");
			return new PrincipalDetails(userEntity);
		}
	}

}
