package org.employeemanagement.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="employee")
public class Employee {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String name;
    private String aadhar;
    private Integer age;
    private String dept;
    private String city;
    private LocalDate dob;

    public Employee(String name, String aadhar, Integer age, String dept, String city, LocalDate dob) {
        this.name = name;
        this.aadhar = aadhar;
        this.age = age;
        this.dept = dept;
        this.city = city;
        this.dob = dob;
    }

    public Employee() {

    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
