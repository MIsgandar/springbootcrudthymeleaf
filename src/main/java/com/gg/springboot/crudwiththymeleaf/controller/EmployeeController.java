package com.gg.springboot.crudwiththymeleaf.controller;


import com.gg.springboot.crudwiththymeleaf.entity.Employee;
import com.gg.springboot.crudwiththymeleaf.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees (Model model) {

        // Get the employees from database
        List<Employee> theEmployee = employeeService.findAll();

        // Add to the spring model
        model.addAttribute("employees",theEmployee);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

        // Get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        // Set employee in the model to prepopulate the form
        theModel.addAttribute(theEmployee);

        // Send over to our form
        return "employees/employee-form";

    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {


        // Save the employee
        employeeService.save(theEmployee);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {

        // Delete the employee
        employeeService.deleteById(theId);

        // Redirect to theEmployee/list
        return "redirect:/employees/list";
    }

}
