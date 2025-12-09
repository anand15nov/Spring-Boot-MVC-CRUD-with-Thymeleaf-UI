package com.springboot.CRUDMVC.controller;

import com.springboot.CRUDMVC.entity.Employee;
import com.springboot.CRUDMVC.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    // browser - controller - service - dao - db
    // binding controller to service in constructor
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    // mapping for list of employees
    @GetMapping("/list")
    public String listEmployees(Model model){
        // asking db for employees(all rows from table)
        List<Employee> employees = employeeService.findAll(); // these will ask dao method, is in asc form wrt lastName
        // adding this data to model
        model.addAttribute("employees",employees);
        return "employees/list-employees"; // folder/webpage.html
    }

    @PostMapping("/save") // to save we use PostMapping()
    public String saveEmployee(@ModelAttribute("employee") Employee employee){ // needs a body which is Employee obj or row in table, for body it needs @ModelAttribute
        employeeService.save(employee);//con - ser - dao
        return "redirect:/employees/list"; // prevents saving the same employee again and cuts the POST request after saving
    }

    @PostMapping("/delete") // needs a integer id to delete so it uses PostMapping()
    public String delete(@RequestParam("employeeId") int id){ // for param it requires @RequestParam
        //delete employee
        employeeService.deleteById(id); // con - ser - dao
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        // bind Employee to model
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "employees/employee-form";//redirects to add/update page
    }



    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model){ // to prepopulate the fields firstName lastName email while updating
        // get employee from service
        Employee employee = employeeService.findById(id);
        // stick employee to model to prepopulate the form
        model.addAttribute("employee",employee);
        return "employees/employee-form";//redirects to update/add page
    }

}
