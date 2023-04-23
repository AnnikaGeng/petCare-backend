package com.yi.petCare.schedule;

import com.yi.petCare.pet.Pet;
import com.yi.petCare.user.Employee;
import com.yi.petCare.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "schedule", catalog = "critter")
public class Schedule {
    @Id
    @GeneratedValue
    Long id;

    @ManyToMany(targetEntity = Employee.class, fetch = FetchType.LAZY)
    private List<Employee> employees;

    @ManyToMany(targetEntity = Pet.class, fetch = FetchType.LAZY)
    private List<Pet> pets;

    private LocalDateTime date;

    @Column
    @ElementCollection(targetClass = EmployeeSkill.class)
    private Set<EmployeeSkill> skills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }
}
