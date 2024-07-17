package com.example.fristproject.api;


import com.example.fristproject.dto.CoffeeForm;
import com.example.fristproject.entity.Coffee;
import com.example.fristproject.repository.CoffeeRepository;
import com.example.fristproject.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoffeeApiController {
    @Autowired
    CoffeeService coffeeService;

    //GET
    @GetMapping("/api/coffees")
    public List<Coffee> index() {
        return coffeeService.index();
    }

    @GetMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id) {
        Coffee coffee = coffeeService.show(id);
        return coffee != null ? ResponseEntity.ok(coffee) : ResponseEntity.notFound().build();
    }

    //POST
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm coffeeForm) {
        Coffee coffee = coffeeService.create(coffeeForm);
        return coffee != null ? ResponseEntity.ok(coffee) : ResponseEntity.notFound().build();
    }

    //PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm coffeeForm) {
        Coffee coffee = coffeeService.update(id, coffeeForm);
        return coffee != null ? ResponseEntity.ok(coffee) : ResponseEntity.notFound().build();
    }

    //DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee coffee = coffeeService.delete(id);
        return coffee != null ? ResponseEntity.ok(coffee) : ResponseEntity.badRequest().build();
    }
}
