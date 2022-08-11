package com.cos.photogramstart.web.dto.user;



import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {
	@NotBlank
	private String name;
	@NotBlank
	private String password;
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	// 필수값이랑 아닌게 있어서 이렇게 빌더패턴으로 받기가 조금 그럼
	// 만약 패스워드를 기재 안했다면 ? --> db에 공백 ? 그래서 validation check
	// 필수값들은 그래서 validation check 해야 한다.
	public User userEntity() {
		return User.builder()
				.name(name)
				.password(password)
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	}
	
}
