package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Employee;
import com.example.demo.repository.EmployeeReposatory;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeReposatory employeeRepository;
	
	@PostMapping("/employees")
	public String createNewEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return "employee created in database";
	}
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee>emplist =new ArrayList<>();
		employeeRepository.findAll().forEach(emplist::add);
	return new ResponseEntity<List<Employee>>(emplist,HttpStatus.OK);
	}
	
	@GetMapping("/employees/{empid}")
	public ResponseEntity<Employee>getEmployeeById(@PathVariable Integer empid)
	{
		Optional<Employee>emp = employeeRepository.findById(empid);
		if(emp.isPresent()) {
			return new ResponseEntity<Employee>(emp.get(),HttpStatus.FOUND);
		}else {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	@PutMapping("employees/{empid}")
	public String updateEmployeeById(@PathVariable Integer empid, @RequestBody  Employee employee)
	{
	Optional<Employee>emp = employeeRepository.findById(empid);
	if(emp.isPresent()) {
		Employee exsistEmp  = emp.get();
		exsistEmp.setEmp_age(employee.getEmp_age());
		exsistEmp.setEmp_city(employee.getEmp_city());
		exsistEmp.setEmp_name(employee.getEmp_name());
		exsistEmp.setEmp_salary(employee.getEmp_salary());
		employeeRepository.save(exsistEmp);
		return "Employee Details Against  " +empid + " updated";
		
		
	}
	else {
		return "Employee Details Doesnot exsist for " +empid ;
	}
	
}
	@DeleteMapping("employees/{empid}")
	public String deleteByEmployeeId(@PathVariable Integer empid) {
		employeeRepository.deleteById(empid);
		return "Employee Deleted Succesfully";
		
	}
}