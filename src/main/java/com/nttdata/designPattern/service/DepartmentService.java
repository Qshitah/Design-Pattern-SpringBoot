package com.nttdata.designPattern.service;


import com.nttdata.designPattern.entity.Department;
import com.nttdata.designPattern.repository.DepartmentRepository;
import com.nttdata.designPattern.specification.DepartmentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Service
public class DepartmentService extends AbstractService<Department, Long> {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    protected JpaRepository<Department, Long> getRepository() {
        return departmentRepository;
    }

    // Flexible query using the Specification pattern
    public List<Department> searchDepartments(String name, String description) {
        Specification<Department> specification = Specification.where(DepartmentSpecification.hasName(name))
                .and(DepartmentSpecification.hasDescription(description));

        return departmentRepository.findAll(specification);
    }
}

