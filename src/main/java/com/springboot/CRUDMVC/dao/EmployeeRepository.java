package com.springboot.CRUDMVC.dao;

import com.springboot.CRUDMVC.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// extending JpaRepository<Class,Key> for getting free sql save() delete() update() fetch() ....
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    // sort by lastname, no need to implement this method
    public List<Employee> findAllByOrderByLastNameAsc();
}
