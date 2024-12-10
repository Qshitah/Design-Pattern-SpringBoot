package com.nttdata.designPattern.specification;

import com.nttdata.designPattern.entity.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class EmployeeSpecification {

    public static Specification<Employee> hasName(String name) {
        return (root, query, builder) -> {
            if (StringUtils.hasText(name)) {
                return builder.like(root.get("name"), "%" + name + "%");
            }
            return builder.conjunction(); // no filter if name is empty
        };
    }

    public static Specification<Employee> hasEmail(String email) {
        return (root, query, builder) -> {
            if (StringUtils.hasText(email)) {
                return builder.like(root.get("email"), "%" + email + "%");
            }
            return builder.conjunction(); // no filter if email is empty
        };
    }

    public static Specification<Employee> hasDepartment(String departmentName) {
        return (root, query, builder) -> {
            if (StringUtils.hasText(departmentName)) {
                return builder.like(root.get("department").get("name"), "%" + departmentName + "%");
            }
            return builder.conjunction(); // no filter if department is empty
        };
    }

    // Add other specifications like hasSkills, etc.
}