package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
//	@Query("select * from Todo t where t.userId = ?1") //?1 : 아래 있는 매개 변수 중 첫 번째 매개 변수  // 2022.03.28.월 과제 수행 시 오류 발생해서 주석 처리
	List<TodoEntity> findByUserId(String userId);
}
