package com.example.hospital.controllers.v1;

import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping
public class MainController {

    @GetMapping
    public String hello(){
        return "Start Application From Docker!!! Hello Docker.";
    }

    @PostMapping
    public String getHello(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age
    ){
        System.out.println("Получил имя " + name);
        System.out.println("Получил возраст " + age);
        return "Пришло на сервер: " + (Objects.isNull(name) ||
                name.isEmpty()? "Пустое имя": "Имя " + name) + " "+ ((Objects.isNull(age) ||
                (age <= 0&& age > 100)) ? "Недопустимый или пустой возраст" : "Возраст: " + age);
    }
}
