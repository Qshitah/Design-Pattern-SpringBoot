package com.nttdata.designPattern.specification;


import com.nttdata.designPattern.entity.Department;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class DepartmentSpecification {

    public static Specification<Department> hasName(String name) {
        return (root, query, builder) -> {
            if (StringUtils.hasText(name)) {
                return builder.like(root.get("name"), "%" + name + "%");
            }
            return builder.conjunction(); // no filter if name is empty
        };
    }

    public static Specification<Department> hasDescription(String description) {
        return (root, query, builder) -> {
            if (StringUtils.hasText(description)) {
                return builder.like(root.get("description"), "%" + description + "%");
            }
            return builder.conjunction(); // no filter if description is empty
        };
    }
}
