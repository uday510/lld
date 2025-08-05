package com.app.principles;

class Employee {
    private String name;
    private String position;

    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}

class EmployeeRepository {
    public void save(Employee employee) {
        System.out.println("Saving " + employee.getName() + " to the database...");
    }
}

class EmployeeReport {
    public void printReport(Employee employee) {
        System.out.println("Employee Report");
        System.out.println("---------------");
        System.out.println("Name: " + employee.getName());
        System.out.println("Position: " + employee.getPosition());
    }
}

public class SingleResponsibility {

    public static void main(String[] args) {
        Employee employee = new Employee("John Doe", "Fake God");

        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.save(employee);

        EmployeeReport employeeReport = new EmployeeReport();
        employeeReport.printReport(employee);
    }

}
