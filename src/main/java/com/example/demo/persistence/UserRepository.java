package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> { //UserEntity를 담는 UserRepository
	UserEntity findByEmail(String email); //이메일 찾기
	Boolean existsByEmail(String email); //이메일이 존재하는지
	UserEntity findByEmailAndPassword(String email, String password); //이메일과 비밀번호로 찾기
}
