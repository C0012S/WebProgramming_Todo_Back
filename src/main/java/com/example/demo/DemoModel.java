package com.example.demo;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder //DemoModelBuilder가 자동으로 만들어진다.(Window - Show View - Outline으로 확인)
@Setter //setId가 자동으로 만들어진다.(Window - Show View - Outline으로 확인)
@RequiredArgsConstructor //DemoModel 생성자가 이 어노테이션 때문에 생긴다.(Window - Show View - Outline으로 확인) //생성자를 자동으로 만드는 게 RequiredArgsConstructor이다.
public class DemoModel {
	
	@NonNull //롬복 어노테이션 //id 값이 Null이 되면 안 된다는 것을 추가한 것이다.
	private String id; //속성 추가

}
