package com.example.fortesextrahourapi.repositories;

import com.example.fortesextrahourapi.domain.Employee;
import com.example.fortesextrahourapi.domain.RoleEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String> {

    Employee findByUsername(String username);
    Boolean existsEmployeeByUsername(String username);
    Boolean existsEmployeeByEmail(String email);
    List<Employee> findAllByRole(RoleEnum employeeRole);
}
