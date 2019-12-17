package com.shresthagaurav.esoftwarica.model;

public class EmployeeCUD {
    private int id;
    private String name;
    private float salary;
    private int age;
    private String profile_image;

    public EmployeeCUD( String name, float salary, int age, String profile_image) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.profile_image = profile_image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getProfile_image() {
        return profile_image;
    }
}
