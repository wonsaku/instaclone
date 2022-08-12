package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length=20, unique=true, nullable=false)
	private String username;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private String name;
	private String website;
	private String bio;
	@Column(nullable=false)
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl;
	private String role;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY) // db에서 만들지 마, 나는 연관관계의 주인이 아니다. user를 셀렉트할 때 해당 유저 아이디로 등록된 이미지 다 가져와 lazy = user를 셀렉트할 때 해당 user id로 등록된 image들을 가져오지마. 대신 getImages()하면 가져와 eager로 하면 셀렉트할 때 해당 userid로 등록된 image들을 전부 join해서 가져와
	@JsonIgnoreProperties({"user"})
	private List<Image> images;
	
	private LocalDateTime createDate;
	
	@PrePersist
	public void CreateDate() {
		this.createDate = LocalDateTime.now();
	}
	
}
