package com.greatlearning.EmployeeManagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.EmployeeManagement.service.EmployeeService;
import com.greatlearning.EmployeeManagement.entity.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/add")
	public String add(Model model) {

		// create model attribute to bind form data
		Employee employee = new Employee();

		model.addAttribute("Employee", employee);

		return "update-employee";
	}

	@RequestMapping("/list")
	public String listEmployees(Model model) {

		// get Employees from DB
		List<Employee> employees = employeeService.findAll();

		// add to the spring model
		model.addAttribute("Employees", employees);

		return "list-employees";
	}

	@PostMapping("/save")
	public String saveEmployee(@RequestParam("id") int id,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email) {

		System.out.println(id);
		Employee employee = new Employee(firstName, lastName, email);
		// save the Employee
		employeeService.save(employee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";

	}

	@RequestMapping("/403")
	public ModelAndView accessDenied(Principal user) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (user != null) {
			modelAndView.addObject("msg", "Hi " + user.getName() + ", you don't have permission to access this page!");
		} else {
			modelAndView.addObject("msg", "You don't have permission to access this page!");
		}
		modelAndView.setViewName("403");
		return modelAndView;
	}
}
