package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Employee;

public interface EmployeeReposatory extends JpaRepository<Employee , Integer > {

	

}
