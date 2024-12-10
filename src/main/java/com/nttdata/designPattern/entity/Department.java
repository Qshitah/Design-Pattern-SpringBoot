package com.nttdata.designPattern.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();

    // No-args constructor
    public Department() {
    }

    // All-args constructor
    public Department(Long id, String name, String description, Set<Employee> employees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.employees = employees;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    // Builder Pattern
    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private Set<Employee> employees = new HashSet<>();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder employees(Set<Employee> employees) {
            this.employees = employees;
            return this;
        }

        public Department build() {
            return new Department(id, name, description, employees);
        }
    }
}
