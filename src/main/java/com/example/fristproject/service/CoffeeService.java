package com.example.fristproject.service;

import com.example.fristproject.dto.ArticleForm;
import com.example.fristproject.dto.CoffeeForm;
import com.example.fristproject.entity.Article;
import com.example.fristproject.entity.Coffee;
import com.example.fristproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service

public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeForm dto) {
        Coffee coffee = dto.toEntity();
        if (coffee.getId() != null)
            return null;
        return coffeeRepository.save(coffee);
    }

    public Coffee update(Long id, CoffeeForm dto) {
        Coffee coffee = dto.toEntity();
        log.info("id : {}, coffee: {}", id, coffee.toString());
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null || id != target.getId()) {
            log.info("잘못된 요청 ! id : {}, coffee: {}", id, coffee.toString());
            return null;
        }
        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return updated;
    }

    public Coffee delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null)
            return null;
        coffeeRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Coffee> createArticles(List<CoffeeForm> dtos) {
        List<Coffee> coffeeList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        coffeeList.stream()
                .forEach(coffee -> coffeeRepository.save(coffee));
        coffeeRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!"));
        return coffeeList;
    }
}
