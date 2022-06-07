package com.example.demo;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder //DemoModelBuilder�� �ڵ����� ���������.(Window - Show View - Outline���� Ȯ��)
@Setter //setId�� �ڵ����� ���������.(Window - Show View - Outline���� Ȯ��)
@RequiredArgsConstructor //DemoModel �����ڰ� �� ������̼� ������ �����.(Window - Show View - Outline���� Ȯ��) //�����ڸ� �ڵ����� ����� �� RequiredArgsConstructor�̴�.
public class DemoModel {
	
	@NonNull //�Һ� ������̼� //id ���� Null�� �Ǹ� �� �ȴٴ� ���� �߰��� ���̴�.
	private String id; //�Ӽ� �߰�

}
