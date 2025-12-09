package com.springboot.CRUDMVC.service;

import com.springboot.CRUDMVC.dao.EmployeeRepository;
import com.springboot.CRUDMVC.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    // binding service to dao in constructor

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    // making a custom findAll()
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int id) {

        Optional<Employee> result = employeeRepository.findById(id);

        Employee employee = null;

        if(result.isPresent()) employee = result.get();

        else throw new RuntimeException("Did not find Employee Id: "+ id);

        return employee;

    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
