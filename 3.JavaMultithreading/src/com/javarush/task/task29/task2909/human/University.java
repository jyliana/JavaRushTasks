package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private int age;
    private List<Student> students = new ArrayList();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        Student studentWithAverageGrade = null;
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade) {
                studentWithAverageGrade = student;
                break;
            }
        }

        return studentWithAverageGrade;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student studentWithMaxAverageGrade = null;
        for (int i = 0; i < students.size() - 1; i++) {
            if (students.get(i).getAverageGrade() < students.get(i + 1).getAverageGrade())
                studentWithMaxAverageGrade = students.get(i + 1);
        }
        return studentWithMaxAverageGrade;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        Student studentWithMinAverageGrade = students.get(0);
        for (int i = 0; i < students.size() - 1; i++) {
            if (studentWithMinAverageGrade.getAverageGrade() > students.get(i).getAverageGrade())
                studentWithMinAverageGrade = students.get(i);
        }
        return studentWithMinAverageGrade;
    }

    public void expel(Student student) {
        if (students.contains(student)) {
            students.remove(student);
        }
    }
}