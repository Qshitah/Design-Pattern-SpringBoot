package com.nttdata.designPattern.service;


import com.nttdata.designPattern.entity.Employee;
import com.nttdata.designPattern.repository.EmployeeRepository;
import com.nttdata.designPattern.specification.EmployeeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Service
public class EmployeeService extends AbstractService<Employee, Long> {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    protected JpaRepository<Employee, Long> getRepository() {
        return employeeRepository;
    }

    // Flexible query using the Specification pattern
    public List<Employee> searchEmployees(String name, String email, String departmentName) {
        Specification<Employee> specification = Specification.where(EmployeeSpecification.hasName(name))
                .and(EmployeeSpecification.hasEmail(email))
                .and(EmployeeSpecification.hasDepartment(departmentName));

        return employeeRepository.findAll(specification);
    }
}
