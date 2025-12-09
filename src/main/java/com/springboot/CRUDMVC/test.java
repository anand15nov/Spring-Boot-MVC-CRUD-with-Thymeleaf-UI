package com.springboot.CRUDMVC;

import com.springboot.CRUDMVC.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class test {
    @GetMapping("/employees")
    public String test1(){
        return new Employee().toString();
    }

}
