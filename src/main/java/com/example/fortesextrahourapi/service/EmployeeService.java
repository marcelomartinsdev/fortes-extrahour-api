package com.example.fortesextrahourapi.service;

import com.example.fortesextrahourapi.domain.Address;
import com.example.fortesextrahourapi.domain.Employee;
import com.example.fortesextrahourapi.repositories.AddressRepository;
import com.example.fortesextrahourapi.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

//    @Autowired
//    private AddressRepository addressRepository;

    public Employee createEmployee(Employee employee) {
//        List<Address> enderecos = employee.getAddresses();
//        addressRepository.saveAll(enderecos);


        employee.setPassword(cryptoPassword(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    private String cryptoPassword(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }


}
