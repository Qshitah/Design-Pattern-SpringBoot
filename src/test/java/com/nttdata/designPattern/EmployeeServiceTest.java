package com.nttdata.designPattern;

import com.nttdata.designPattern.entity.Department;
import com.nttdata.designPattern.entity.Employee;
import com.nttdata.designPattern.repository.EmployeeRepository;
import com.nttdata.designPattern.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void testCreateEmployee() {
        Department department = new Department.Builder().id(1L).name("IT").build();
        Employee employee = new Employee.Builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .phone("123456789")
                .designation("Developer")
                .salary(60000)
                .department(department)
                .build();

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee created = employeeService.create(employee);

        assertNotNull(created);
        assertEquals("John Doe", created.getName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testFindById() {
        Department department = new Department.Builder().id(1L).name("IT").build();
        Employee employee = new Employee.Builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .department(department)
                .build();

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> found = employeeService.findById(1L);

        assertTrue(found.isPresent());
        assertEquals("John Doe", found.get().getName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testSearchEmployees() {
        Department department = new Department.Builder().id(1L).name("HR").build();
        Employee employee1 = new Employee.Builder().id(1L).name("Alice").email("alice@example.com").department(department).build();
        Employee employee2 = new Employee.Builder().id(2L).name("Bob").email("bob@example.com").department(department).build();

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll(any(Specification.class))).thenReturn(employees);

        List<Employee> result = employeeService.searchEmployees("Alice", null, "HR");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        verify(employeeRepository, times(1)).findAll(any(Specification.class));
    }

    @Test
    void testUpdateEmployee() {
        Department department = new Department.Builder().id(1L).name("IT").build();
        Employee employee = new Employee.Builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .department(department)
                .build();

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee updated = employeeService.update(employee);

        assertNotNull(updated);
        assertEquals("John Doe", updated.getName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testDeleteById() {
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteById(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }
}
