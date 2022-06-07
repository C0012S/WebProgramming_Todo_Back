package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder //builder()와 속성에 해당하는 메소드가 만들어진다다
@NoArgsConstructor //인자 없는 생성자가 만들어진다.
@AllArgsConstructor //속성 4 개를 인자로 받는 생성자가 하나 만들어진다.
@Data //getter, setter 메소드가 자동으로 만들어진다. //equals, toString, 비교하는 것까지 자동으로 만들어진다. //인자 없는 생성자까지 자동으로 만들어진다.
@Entity //테이블과 mapping되는 걸 알려 준다.
@Table(name = "Todo")
public class TodoEntity {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String id; //Primary Key
	private String userId; //할일을 누가 만들었는지
	private String title; //할일이 무엇인지
	private boolean done; //할일을 했는지, 안 했는지
	
}
