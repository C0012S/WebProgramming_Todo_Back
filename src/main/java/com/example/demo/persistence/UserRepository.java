package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> { //UserEntity�� ��� UserRepository
	UserEntity findByEmail(String email); //�̸��� ã��
	Boolean existsByEmail(String email); //�̸����� �����ϴ���
	UserEntity findByEmailAndPassword(String email, String password); //�̸��ϰ� ��й�ȣ�� ã��
}
