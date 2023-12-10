package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;
    private transient double GPA;

    public Student() {
    }

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", GPA=" + GPA +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
@JsonIgnore
    public double getGPA() {
        return GPA;
    }
}
