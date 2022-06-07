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

@Builder //builder()�� �Ӽ��� �ش��ϴ� �޼ҵ尡 ��������ٴ�
@NoArgsConstructor //���� ���� �����ڰ� ���������.
@AllArgsConstructor //�Ӽ� 4 ���� ���ڷ� �޴� �����ڰ� �ϳ� ���������.
@Data //getter, setter �޼ҵ尡 �ڵ����� ���������. //equals, toString, ���ϴ� �ͱ��� �ڵ����� ���������. //���� ���� �����ڱ��� �ڵ����� ���������.
@Entity //���̺�� mapping�Ǵ� �� �˷� �ش�.
@Table(name = "Todo")
public class TodoEntity {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String id; //Primary Key
	private String userId; //������ ���� ���������
	private String title; //������ ��������
	private boolean done; //������ �ߴ���, �� �ߴ���
	
}
