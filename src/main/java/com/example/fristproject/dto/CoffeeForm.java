package com.example.fristproject.dto;

import com.example.fristproject.entity.Article;
import com.example.fristproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class CoffeeForm {
    private Long id;
    private String name;
    private String price;

    public Coffee toEntity() {
        return new Coffee(id, name, price);
    }
}
