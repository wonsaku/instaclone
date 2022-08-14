package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final SubscribeRepository subscribeRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional(readOnly=true)
	public UserProfileDto 회원프로필(int pageUserId, int principalId) {
		UserProfileDto userProfileDto = new UserProfileDto();
		// SELECT * FROM image WHERE userId = :userId;
		User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
			throw new CustomException("Id를 찾을 수 없습니다.");
		});
		userProfileDto.setImageCount(userEntity.getImages().size());
		userProfileDto.setUser(userEntity);
		userProfileDto.setPageOwnerState(pageUserId == principalId);
		
		int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
		int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
		
		userProfileDto.setSubscribeCount(subscribeCount);
		userProfileDto.setSubscribeState(subscribeState == 1);
		
		return 	userProfileDto;
	}
	@Transactional
	public User 회원수정(int id, User user) {
		//1.영속화
		User userEntity = userRepository.findById(id).orElseThrow(()->{
				return new CustomValidationApiException("찾을 수 없는 Id 입니다.");
		}); //null일 수가 있어서 자바에서는 Optional이라는 wrapping class 제공하고 여기서 제공하는 게 3개 무조건 찾았다. get(), 못찾았어 exception 발동 orElseThrow() 
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		//2.영속화된 오브젝트 수정 - 더티체킹(업데이트 완료)
		userEntity.setName(user.getName());
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		
		return userEntity;
	}

}
