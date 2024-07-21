package com.example.fristproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
}

/*
* /api/pizzas | post : 생성
* /api/pizzas | get: 목록조회
* /api/pizzas/{id} | get : 단건조회
* /api/pizzas/{id} | patch : 수정
* /api/pizzas/{id} | delete : 삭제
* */