package com.example.fortesextrahourapi.repositories;

import com.example.fortesextrahourapi.domain.employee.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String> {

}
